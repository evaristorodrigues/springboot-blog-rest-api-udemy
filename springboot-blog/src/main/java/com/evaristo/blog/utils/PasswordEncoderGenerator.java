/**
 * 
 */
package com.evaristo.blog.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author evari
 *
 */
public class PasswordEncoderGenerator {
	
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("123456"));
		System.out.println(passwordEncoder.encode("papine"));

	}

}
