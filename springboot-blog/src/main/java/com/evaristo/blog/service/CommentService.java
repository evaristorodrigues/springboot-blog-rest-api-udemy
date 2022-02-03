/**
 * 
 */
package com.evaristo.blog.service;

import java.util.List;

import com.evaristo.blog.payload.CommentDTO;

/**
 * @author evari
 *
 */

public interface CommentService {
	
	CommentDTO createComment(Long postId, CommentDTO commentDTO);
	List<CommentDTO> getCommentByPostId(Long postId);
	CommentDTO getCommentById(Long postId, Long commentID);

}
