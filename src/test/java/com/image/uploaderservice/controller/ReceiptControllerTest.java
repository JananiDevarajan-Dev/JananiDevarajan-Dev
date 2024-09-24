package com.image.uploaderservice.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import com.image.uploaderservice.service.ReceiptService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReceiptController.class) // Testing only the controller layer
public class ReceiptControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReceiptService receiptService;

	private MockMultipartFile validImage;
	private MockMultipartFile emptyFile;
	private MockMultipartFile invalidFormatFile;

	@BeforeEach
	public void setup() {
		// Creating mock files for testing
		validImage = new MockMultipartFile("file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE,
				"image content".getBytes());
		emptyFile = new MockMultipartFile("file", "receipt.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[0]);
		invalidFormatFile = new MockMultipartFile("file", "receipt.txt", MediaType.TEXT_PLAIN_VALUE,
				"not an image".getBytes());
	}

	@Test
	public void testUploadValidImage() throws Exception {
		// Mock the service behavior for valid image upload
		mockMvc.perform(multipart("/api/receipts/upload").file(validImage)).andExpect(status().isOk()); // Expecting
	}

	@Test
	public void testUploadEmptyFile() throws Exception {
		// Mock the service behavior when uploading an empty file
		mockMvc.perform(multipart("/api/receipts/upload").file(emptyFile)).andExpect(status().isBadRequest()) // Expecting
																												// 400
																												// Bad
																												// Request
				.andExpect(jsonPath("$.message").value("File is empty.")); // Expecting error message about empty file
	}

	@Test
	public void testUploadUnsupportedFormat() throws Exception {
		// Mock the service behavior for unsupported file format
		// Expecting an unsupported format error message
		mockMvc.perform(multipart("/api/receipts/upload").file(invalidFormatFile)).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message")
						.value("Unsupported image format. Only jpg, jpeg, png, and gif are allowed."));
	}

}