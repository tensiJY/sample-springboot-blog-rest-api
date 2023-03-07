package com.springboot.blog.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
@Getter
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String resourceName;
	
	private String fieldName;
	
	private Long fieldValue;
	
	private LocalDateTime timestamp;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		super(String.format("$s not found with %s : ''%s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.timestamp = LocalDateTime.now();
	}

}
