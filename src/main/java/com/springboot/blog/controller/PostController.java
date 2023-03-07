package com.springboot.blog.controller;

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

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
	/*
	@PostMapping
	public ResponseEntity<DataResponse> createPost(@RequestBody PostDto postDto) throws Exception{
		PostDto savedPost = postService.createPost(postDto);
		return new ResponseEntity<DataResponse>(PostResDto.post(savedPost), HttpStatus.CREATED);
	}
	*/
	
	@GetMapping
	public PostResponse getAllPosts(
			  @RequestParam(value = "pageNo",   defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,    required=false) int pageNo
			, @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,      required=false) int pageSize
			, @RequestParam(value = "sortBy",   defaultValue = AppConstants.DEFAULT_SORT_BY,        required=false) String sortBy
			, @RequestParam(value = "sortDir",  defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required=false) String sortDir
			){
		return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}
	
	//	PathVariable => MethodArgumentTypeMismatchException 
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
		return ResponseEntity.ok(postService.getPostById(id));
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable(name="id") long id, @Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.updatePost(id, postDto), HttpStatus.OK);
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id) {
		postService.deletePostById(id);
		return new ResponseEntity<String>("Post delete id : " + id, HttpStatus.OK);
	}
	
}
