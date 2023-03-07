package com.springboot.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	private ModelMapper modelMapper;
	
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = mapToEntity(postDto);
		
		Post newPost = postRepository.save(post);
		
		PostDto postReponse = mapToDTO(newPost);
		
		return postReponse;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
		/*
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		*/
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		log.info(Sort.Direction.ASC.name());
				
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Post> posts = postRepository.findAll(pageable);
				
		List<Post> listOfPosts = posts.getContent();
		
		List<PostDto> content =  listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
		
		return PostResponse.builder()
				.content(content)
				.pageNo(posts.getNumber())
				.pageSize(posts.getSize())
				.totalPages(posts.getTotalPages())
				.totalElements(posts.getTotalElements())
				.last(posts.isLast())
				.build();
	}
	
	//	convert entity into DTO
	private PostDto mapToDTO(Post post) {
		/*
		return PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.description(post.getDescription())
				.build();
		*/
		return modelMapper.map(post, PostDto.class);
	}
	
	//	convert DTO to entity
	private Post mapToEntity(PostDto postDto) {
		/*
		return Post.builder()
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.description(postDto.getDescription())
				.build();
		*/
		return modelMapper.map(postDto, Post.class);
	}

	@Override
	public PostDto getPostById(long id)  {
		//	객체에 주어진 id에 존재하지 않으면 orElseThrow() 메서드를 이용하여 예외를 throw 할 수 있다
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		return mapToDTO(post);
	}

	@Override
	public PostDto updatePost(long id, PostDto postDto) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		
		return mapToDTO(updatedPost);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		postRepository.delete(post);
		//postRepository.deleteById(id);
	}

}
