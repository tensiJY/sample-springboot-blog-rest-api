package com.springboot.blog.test;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;

@SpringBootTest
public class StreamTest {
	
	
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private List<Post> posts;
	
	@BeforeEach
	public void beforeEach() {
		posts = List.of(
				  Post.builder().id(1L).title("title 1").build()
				, Post.builder().id(2L).title("title 2").build()
				, Post.builder().id(3L).title("title 3").build()
				, Post.builder().id(4L).title("title 4").build()
				, Post.builder().id(5L).title("title 5").build()
			);
	}
	
	
	@Test
	@DisplayName("테스트 1")
	public void test1() {
		logger.info("posts : {}", posts);
		
		List<PostDto> postDtos = posts.stream().map( (post) -> {
			return mapToDTO(post);
		}).collect(Collectors.toList());
		
		logger.info("postDtos : {}", postDtos);
	}
	
	
	
	//	convert entity into DTO
	private PostDto mapToDTO(Post post) {
		return  PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.description(post.getDescription())
				.build();
	}
	
	//	convert DTO to entity
	private Post mapToEntity(PostDto postDto) {
		return Post.builder()
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.description(postDto.getDescription())
				.build();
	}
	
	
}
