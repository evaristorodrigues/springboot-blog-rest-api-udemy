/**
 * 
 */
package com.evaristo.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaristo.blog.entity.Role;
import com.evaristo.blog.entity.User;
import com.evaristo.blog.payload.JWTAuthResponse;
import com.evaristo.blog.payload.LoginDTO;
import com.evaristo.blog.payload.SignUpDTO;
import com.evaristo.blog.repository.RoleRepository;
import com.evaristo.blog.repository.UserRepository;
import com.evaristo.blog.security.JWTTokenProvider;

/**
 * @author evari
 *
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	
	
	@PostMapping("/signin")
	public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody LoginDTO loginDTO){
		Authentication authentication =   authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//get token from token provider
		String token = jwtTokenProvider.generateToken(authentication);
		
		return new ResponseEntity<>(new JWTAuthResponse(token), HttpStatus.OK);
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDTO){
		//FIXME From my point of view this roles should be inside a @Service class
		
		//add check for username exists in a DB
		if(userRepository.existsByUsername(signUpDTO.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		//add check for email exists in a DB
		if(userRepository.existsByEmail(signUpDTO.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		//create user object
		User user = new User();
		user.setName(signUpDTO.getName());
		user.setUsername(signUpDTO.getUsername());
		user.setEmail(signUpDTO.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
		
		Role role = roleRepository.findByName("ROLE_ADMIN").get();
		
		user.setRoles(Collections.singleton(role));
		
		userRepository.save(user);
		
		return new ResponseEntity<>("User registerd successfully", HttpStatus.CREATED);
	}

}
