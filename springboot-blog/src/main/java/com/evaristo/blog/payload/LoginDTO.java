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
public class LoginDTO {
	
	private String usernameOrEmail;
	private String password;

}
