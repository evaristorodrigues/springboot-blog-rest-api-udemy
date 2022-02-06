/**
 * 
 */
package com.evaristo.blog.payload;

import lombok.Data;

/**
 * @author evari
 *
 */
@Data
public class SignUpDTO {
	
	private String name;
	private String username;
	private String email;
	private String password;

}
