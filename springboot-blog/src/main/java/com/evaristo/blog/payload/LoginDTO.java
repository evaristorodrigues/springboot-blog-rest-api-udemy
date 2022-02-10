/**
 * 
 */
package com.evaristo.blog.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author evari
 *
 */
@ApiModel(description = "Login Model Information")
@Data
public class LoginDTO {
	
	@ApiModelProperty(value = "Blog Login id")
	private String usernameOrEmail;
	
	@ApiModelProperty(value = "Blog Login password")
	private String password;

}
