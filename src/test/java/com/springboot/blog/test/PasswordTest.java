package com.springboot.blog.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordTest {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public String createPassword(String password) {
		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		return bc.encode(password);
	}
	
	@Test
	public void test() {
		logger.info("park : {}", createPassword("park"));
		logger.info("admin : {}", createPassword("admin"));
	}
	
	

}
