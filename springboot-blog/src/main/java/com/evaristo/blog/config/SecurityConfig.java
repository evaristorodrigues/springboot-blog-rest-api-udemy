/**
 * 
 */
package com.evaristo.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.evaristo.blog.security.CustomUserDetailsService;
import com.evaristo.blog.security.JWTAuthenticationEntryPoint;
import com.evaristo.blog.security.JWTAuthenticationFilter;

/**
 * @author evari
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			//TOKEN JWT
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			//Methods gets
			.antMatchers(HttpMethod.GET, "/api/**").permitAll()
			//Authentication
			.antMatchers( "/api/v1/auth/**").permitAll()
			//Swagger
			.antMatchers( "/v2/api-docs/**").permitAll()
			.antMatchers( "/swagger-ui/**").permitAll()
			.antMatchers( "/swagger-resources/**").permitAll()
			.antMatchers( "/swagger-ui.html/**").permitAll()
			.antMatchers( "/webjars/**").permitAll()
			
			.anyRequest()
			.authenticated();
		//desabilitando o BA
/*			.and()
			.httpBasic();*/
		
		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails user1= User.builder().username("evaristo").password(passwordEncoder().encode("123456")).roles("ADMIN").build();
//		UserDetails user2 = User.builder().username("camila").password(passwordEncoder().encode("papine")).roles("USER").build();
//		return new InMemoryUserDetailsManager(user1, user2);
//	}
	


}
