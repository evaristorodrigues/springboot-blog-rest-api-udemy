/**
 * 
 */
package com.evaristo.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evaristo.blog.entity.Comment;
import com.evaristo.blog.payload.CommentDTO;

/**
 * @author evari
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	List<Comment> findByPostId(Long postId);

}
