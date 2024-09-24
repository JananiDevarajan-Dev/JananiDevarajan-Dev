package com.image.uploaderservice.controller;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.image.uploaderservice.exception.InvalidImageException;
import com.image.uploaderservice.service.ReceiptService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/receipts")
public class ReceiptController {
	private static final List<String> SUPPORTED_FILE_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif");
	@Autowired
	private ReceiptService receiptService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadReceipt(@RequestParam("file") MultipartFile file) {

		if (file.isEmpty()) {
			throw new InvalidImageException("File is empty.");
		}
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
		if (!SUPPORTED_FILE_EXTENSIONS.contains(fileExtension)) {
			throw new InvalidImageException("Unsupported image format. Only jpg, jpeg, png, and gif are allowed.");
		}
		try {
			receiptService.uploadReceipt(file);
			return ResponseEntity.ok("File uploaded successfully.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
