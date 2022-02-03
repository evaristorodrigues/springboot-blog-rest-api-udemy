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
public class CommentDTO {
	
	private long id;
	private String name;
	private String email;
	private String body;	

}
