/**
 * 
 */
package com.evaristo.blog.service;

import java.util.List;

import com.evaristo.blog.payload.PostDTO;
import com.evaristo.blog.payload.PostResponse;

/**
 * @author evari
 *
 */
public interface PostService {

	PostDTO createPost(PostDTO postDTO);
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String SortDir);
	PostDTO getPostsById(Long id);
	PostDTO updatePost(PostDTO post, Long id);
	void deletePost(Long id);
	
}
