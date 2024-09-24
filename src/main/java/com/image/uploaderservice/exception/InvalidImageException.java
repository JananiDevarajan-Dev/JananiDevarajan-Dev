package com.image.uploaderservice.exception;

import org.springframework.stereotype.Component;

@Component
public class InvalidImageException extends RuntimeException {

	public InvalidImageException() {

	}

	public InvalidImageException(String message) {
		super(message);
	}

}
