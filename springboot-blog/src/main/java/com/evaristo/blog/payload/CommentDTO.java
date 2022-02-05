/**
 * 
 */
package com.evaristo.blog.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author evari
 *
 */
@Data
public class CommentDTO {
	
	private long id;
	
	
	@NotEmpty(message="Comment name should not be null or empty")
	private String name;
	
	@NotEmpty
	@Email(message = "Comment email should be a valid email address")
	private String email;
	
	@NotEmpty
	@Size(min=2, message="Comment body shoul have at least 10 characters")
	private String body;	

}
