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
@ApiModel(description = "Sign Up Model Information")
@Data
public class SignUpDTO {
	
	@ApiModelProperty(value = "Blog Sign Up name")
	private String name;
	
	@ApiModelProperty(value = "Blog Sign Up username")
	private String username;
	
	@ApiModelProperty(value = "Blog Sign Up email")
	private String email;
	
	@ApiModelProperty(value = "Blog Sign Up password")
	private String password;

}
