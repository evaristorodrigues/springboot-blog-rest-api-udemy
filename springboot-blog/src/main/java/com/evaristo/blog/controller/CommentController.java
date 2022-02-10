/**
 * 
 */
package com.evaristo.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaristo.blog.payload.CommentDTO;
import com.evaristo.blog.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author evari
 *
 */
@Api(value="CRUS REST API for Comment Resource")
@RestController
@RequestMapping("/api/v1")
public class CommentController {
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		super();
		this.commentService = commentService;
	}
	
	@ApiOperation(value="Create comment REST API")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value="postId") Long postId,@Valid @RequestBody CommentDTO commentDTO){
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}
	@ApiOperation(value="Get comment by postId REST API")
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> getCommentByPostId(@PathVariable(value="postId") Long postId){
		return ResponseEntity.ok(commentService.getCommentByPostId(postId));
	}
	
	@ApiOperation(value="Get comment by id REST API")
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value="postId") Long postId,
													 @PathVariable(value="id") Long commentId){
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}
	
	@ApiOperation(value="Update comment by id REST API")
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value="postId") Long postId,
													@PathVariable(value="id") Long commentId,
													@Valid @RequestBody CommentDTO commentDTO){
		
		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);		
	}
	
	@ApiOperation(value="Delete comment by id REST API")
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId") Long postId, 
												@PathVariable(value="id")Long commentId){
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment deleted successfuly", HttpStatus.OK);
	}

}
