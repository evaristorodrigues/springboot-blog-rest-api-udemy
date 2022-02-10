package com.evaristo.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.evaristo.blog.entity.Role;
import com.evaristo.blog.repository.RoleRepository;

@SpringBootApplication
public class SpringbootBlogApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBlogApplication.class, args);
	}
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Role roleAdmin = new Role();		
		roleAdmin.setName("ROLE_ADMIN");
		
		
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		
		roleRepository.save(roleAdmin);
		roleRepository.save(roleUser);		
	
	}
	
}
