package com.springboot.blog.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import com.springboot.blog.entity.Post;


@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
public class PostRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
		
    @Autowired
    private PostRepository postRepository;
  	
	@Test
	@DisplayName("POST 저장 - 성공")
	@DirtiesContext
	public void saveTest() {
		//	given
		Post post = Post.builder()
				.title("안녕하세요!")
				.content("내용입니다!")
				.description("상세 설명입니다 !!!!")
				.build();
		
		logger.info("post : {}", post);
		
		//	when
		Post savedPost = postRepository.save(post);
		
		logger.info("saved post : {}", post);
		
		//	then
		assertEquals(post.getTitle(), savedPost.getTitle());
		assertEquals(post.getContent(), savedPost.getContent());
		assertEquals(post.getDescription(), savedPost.getDescription());
	}
	
	@Test
	@DisplayName("POST 조회 - 성공")
	@DirtiesContext
	public void selectTest() {
		
	}
	
	
}
