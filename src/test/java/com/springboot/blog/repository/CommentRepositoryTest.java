package com.springboot.blog.repository;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-test.properties")
@SpringBootTest
public class CommentRepositoryTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private CommentRepository commentRepository;
    
    @Test
    public void getCommentsByPostId() {
    	logger.info("{}", commentRepository.findByPostId(1L));
    	logger.info("{}", commentRepository.findAllByPostId(1L));
    }
	
}
