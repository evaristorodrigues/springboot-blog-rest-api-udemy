/**
 * 
 */
package com.evaristo.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evaristo.blog.payload.PostDTO;
import com.evaristo.blog.payload.PostDTOV2;
import com.evaristo.blog.payload.PostResponse;
import com.evaristo.blog.service.PostService;
import com.evaristo.blog.utils.AppConstants;

/**
 * @author evari
 *
 */
@RestController
@RequestMapping("/api")
public class PostController {
	
	private PostService postService;

	//We dont need de @Autowired annotation because this class is a bean and it has only one constructor
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	//Create blog post
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/v1/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
		return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
	}
	
	//get all post rest api
	
	@GetMapping("/v1/posts")
	public PostResponse getAllPosts(@RequestParam(value="pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			                        @RequestParam(value="pageSize", defaultValue= AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			                        @RequestParam(value="sortBy", defaultValue=AppConstants.DEFAULT_SORT_BY, required=false) String sortBy,
			                        @RequestParam(value="sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
		
		return postService.getAllPosts(pageNo, pageSize,sortBy, sortDir);
	}
	
	@GetMapping("/v1/posts/{id}")
	public ResponseEntity<PostDTO> gePostByIdV1(@PathVariable(name="id") Long id){
		return  ResponseEntity.ok(postService.getPostsById(id));
	}
	
	@GetMapping("/v2/posts/{id}")
	public ResponseEntity<PostDTOV2> gePostByIdV2(@PathVariable(name="id") Long id){
		
		PostDTO postDTO = postService.getPostsById(id);
		PostDTOV2 postDTOV2 = new PostDTOV2();
		postDTOV2.setId(postDTO.getId());
		postDTOV2.setTitle(postDTO.getTitle());
		postDTOV2.setDescription(postDTO.getDescription());
		postDTOV2.setContent(postDTO.getContent());
		postDTOV2.setComments(postDTO.getComments());
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		tags.add("AWS");
		
		postDTOV2.setTags(tags);
		
		return  ResponseEntity.ok(postDTOV2);
	}
	
	@GetMapping(value="/posts/{id}", params = "version=1")
	public ResponseEntity<PostDTO> gePostByIdParamV1(@PathVariable(name="id") Long id){
		return  ResponseEntity.ok(postService.getPostsById(id));
	}
	
	@GetMapping(value="/posts/{id}", params = "version=2")
	public ResponseEntity<PostDTOV2> gePostByIdParamV2(@PathVariable(name="id") Long id){
		
		PostDTO postDTO = postService.getPostsById(id);
		PostDTOV2 postDTOV2 = new PostDTOV2();
		postDTOV2.setId(postDTO.getId());
		postDTOV2.setTitle(postDTO.getTitle());
		postDTOV2.setDescription(postDTO.getDescription());
		postDTOV2.setContent(postDTO.getContent());
		postDTOV2.setComments(postDTO.getComments());
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		tags.add("AWS");
		
		postDTOV2.setTags(tags);
		
		return  ResponseEntity.ok(postDTOV2);
	}
	
	@GetMapping(value="/posts/{id}", headers = "X-API-VERSION=1" )
	public ResponseEntity<PostDTO> gePostByIdHeaderV1(@PathVariable(name="id") Long id){
		return  ResponseEntity.ok(postService.getPostsById(id));
	}
	
	@GetMapping(value="/posts/{id}", headers = "X-API-VERSION=2")
	public ResponseEntity<PostDTOV2> gePostByIdHeaderV2(@PathVariable(name="id") Long id){
		
		PostDTO postDTO = postService.getPostsById(id);
		PostDTOV2 postDTOV2 = new PostDTOV2();
		postDTOV2.setId(postDTO.getId());
		postDTOV2.setTitle(postDTO.getTitle());
		postDTOV2.setDescription(postDTO.getDescription());
		postDTOV2.setContent(postDTO.getContent());
		postDTOV2.setComments(postDTO.getComments());
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		tags.add("AWS");
		
		postDTOV2.setTags(tags);
		
		return  ResponseEntity.ok(postDTOV2);
	}
	
	@GetMapping(value="/posts/{id}", produces = "application/vnd.version.v1+json")
	public ResponseEntity<PostDTO> gePostByIdContentNegociationV1(@PathVariable(name="id") Long id){
		return  ResponseEntity.ok(postService.getPostsById(id));
	}
	
	@GetMapping(value="/posts/{id}", produces = "application/vnd.version.v2+json")
	public ResponseEntity<PostDTOV2> gePostByIdContentNegociationV2(@PathVariable(name="id") Long id){
		
		PostDTO postDTO = postService.getPostsById(id);
		PostDTOV2 postDTOV2 = new PostDTOV2();
		postDTOV2.setId(postDTO.getId());
		postDTOV2.setTitle(postDTO.getTitle());
		postDTOV2.setDescription(postDTO.getDescription());
		postDTOV2.setContent(postDTO.getContent());
		postDTOV2.setComments(postDTO.getComments());
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		tags.add("AWS");
		
		postDTOV2.setTags(tags);
		
		return  ResponseEntity.ok(postDTOV2);
	}
	
	//update by id rest api
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/v1/posts/{id}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable(name="id") long id){
		return new ResponseEntity<>(postService.updatePost(postDTO, id), HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/v1/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name="id") long id){
		
		postService.deletePost(id);
		
		return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
	}
}
