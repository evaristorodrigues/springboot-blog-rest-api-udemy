/**
 * 
 */
package com.evaristo.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.evaristo.blog.entity.Comment;
import com.evaristo.blog.entity.Post;
import com.evaristo.blog.exception.BlogAPIException;
import com.evaristo.blog.exception.ResourceNotFoundException;
import com.evaristo.blog.payload.CommentDTO;
import com.evaristo.blog.repository.CommentRepository;
import com.evaristo.blog.repository.PostRepository;
import com.evaristo.blog.service.CommentService;

/**
 * @author evari
 *
 */
@Service
public class CommentServiceImpl implements CommentService{


	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	
		
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		super();
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}


	@Override
	public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
		Comment comment = mapToEntity(commentDTO);		
		//retieve post entity by id;
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		//set post to comment entity
		comment.setPost(post);
		// Save comment in DB
		Comment commentSaved = commentRepository.save(comment);
		return mapToDTO(commentSaved);
	}
	
	
	private CommentDTO mapToDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();		
		commentDTO.setId(comment.getId());
		commentDTO.setBody(comment.getBody());
		commentDTO.setEmail(comment.getEmail());
		commentDTO.setName(comment.getEmail());
		return commentDTO;
		
	}
	
	private Comment mapToEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();		
		comment.setId(commentDTO.getId());
		comment.setBody(commentDTO.getBody());
		comment.setEmail(commentDTO.getEmail());
		comment.setName(commentDTO.getEmail());
		return comment;
		
	}


	@Override
	public List<CommentDTO> getCommentByPostId(Long postId) {
		//retieve comment by postId
		List<Comment> comments = commentRepository.findByPostId(postId);
		//Convert list of comment entities to list of commentDTO
		return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
	}


	@Override
	public CommentDTO getCommentById(Long postId, Long commentID) {
		//retieve post entity by id;
		Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		//Retrieve a comment by id
		Comment comment = commentRepository.findById(commentID).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentID));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
		}
		return mapToDTO(comment);
	}

}
