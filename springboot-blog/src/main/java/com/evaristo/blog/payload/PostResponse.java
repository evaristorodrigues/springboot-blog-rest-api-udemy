/**
 * 
 */
package com.evaristo.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author evari
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
	List<PostDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
