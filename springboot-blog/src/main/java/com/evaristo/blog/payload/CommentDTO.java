/**
 * 
 */
package com.evaristo.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author evari
 *
 */
@ApiModel(description = "Post Comment Model Information")
@Data
public class CommentDTO {
	
	@ApiModelProperty(value = "Blog Post Comment Id")
	private long id;
	
	@ApiModelProperty(value = "Blog Post Comment name")
	@NotEmpty(message="Comment name should not be null or empty")
	private String name;
	
	@ApiModelProperty(value = "Blog Post Comment email")
	@NotEmpty
	@Email(message = "Comment email should be a valid email address")
	private String email;
	
	@ApiModelProperty(value = "Blog Post Comment email")
	@NotEmpty
	@Size(min=2, message="Comment body shoul have at least 10 characters")
	private String body;	

}
