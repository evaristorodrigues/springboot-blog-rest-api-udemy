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
public class PostDTO {
	private long id;
	private String title;
	private String description;
	private String content;
}
