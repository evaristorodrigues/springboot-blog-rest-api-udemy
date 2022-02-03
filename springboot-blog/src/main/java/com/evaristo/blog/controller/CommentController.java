/**
 * 
 */
package com.evaristo.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaristo.blog.payload.CommentDTO;
import com.evaristo.blog.service.CommentService;

/**
 * @author evari
 *
 */
@RestController
@RequestMapping("/api")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value="postId") Long postId,@RequestBody CommentDTO commentDTO){
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentByPostId(@PathVariable(value="postId") Long postId){
		return ResponseEntity.ok(commentService.getCommentByPostId(postId));
	}
	
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value="postId") Long postId,
													 @PathVariable(value="id") Long commentId){
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}
	
	

}
