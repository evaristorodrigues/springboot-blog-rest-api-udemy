/**
 * 
 */
package com.evaristo.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.evaristo.blog.entity.Post;
import com.evaristo.blog.exception.ResourceNotFoundException;
import com.evaristo.blog.payload.PostDTO;
import com.evaristo.blog.payload.PostResponse;
import com.evaristo.blog.repository.PostRepository;
import com.evaristo.blog.service.PostService;

/**
 * @author evari
 *
 */

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper mapper;
	
	
	//We dont need @Autowired because this class is a bean and it has only one contructore 
	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {
		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {		
		Post post = mapToEntity(postDTO);		
		Post persistedPost = postRepository.save(post);		
		PostDTO postResponse = mapToDTO(persistedPost);		
		return postResponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		
		//creating the sort dynamically
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
				
		//create pageable instance 
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> pagePosts = postRepository.findAll(pageable);
		//get content from page object
		List<Post> posts = pagePosts.getContent();
		
		List<PostDTO> postsDTO = posts.stream().map(p -> mapToDTO(p)).collect(Collectors.toList());
		
		//create PostResponse
		PostResponse postResponse = new PostResponse();		
		postResponse.setContent(postsDTO);
		postResponse.setPageNo(pagePosts.getNumber()); // Page Number
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setLast(pagePosts.isLast());
		
		return postResponse;
	}

	@Override
	public PostDTO getPostsById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts", "id", id));

		return mapToDTO(post);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO,Long id) {
		//get post by id from de database;
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDTO.getTitle());
		post.setDescription(postDTO.getDescription());
		post.setContent(postDTO.getContent());
		post.setId(id);
		
		Post postUpdated = postRepository.save(post);
		return mapToDTO(postUpdated);
	}

	@Override
	public void deletePost(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Posts", "id", id));
		 postRepository.delete(post);
		
	}
	
	//converting entity to DTO
	private PostDTO mapToDTO(Post post) {		
		PostDTO postDTO = mapper.map(post, PostDTO.class);
//		postDTO.setId(post.getId());
//		postDTO.setTitle(post.getTitle());
//		postDTO.setDescription(post.getDescription());
//		postDTO.setContent(post.getContent());		
		return postDTO;
	}
	
	//convert DTO to entity.
	private Post mapToEntity(PostDTO postDTO) {
		Post post = mapper.map(postDTO, Post.class);
//		post.setTitle(postDTO.getTitle());
//		post.setDescription(postDTO.getDescription());
//		post.setContent(postDTO.getContent());		
		return post;
	}
	
}
