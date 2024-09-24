package com.image.uploaderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ReceiptService {
	private static final String IMAGE_UPLOAD_DIR = "C://receipts/";

	@Autowired
	private ImageResizerService imageResizerService;

	public void uploadReceipt(MultipartFile file) throws IOException {

		// Generate file paths
		String originalFilePath = IMAGE_UPLOAD_DIR + file.getOriginalFilename();
		String smallFilePath = IMAGE_UPLOAD_DIR + "small_" + file.getOriginalFilename();
		String mediumFilePath = IMAGE_UPLOAD_DIR + "medium_" + file.getOriginalFilename();
		String largeFilePath = IMAGE_UPLOAD_DIR + "large_" + file.getOriginalFilename();

		// Save original file
		Path filePath = Paths.get(originalFilePath);
		file.transferTo(filePath);

		// Resize images
		imageResizerService.resizeImage(originalFilePath, smallFilePath, 200, 200);
		imageResizerService.resizeImage(originalFilePath, mediumFilePath, 500, 500);
		imageResizerService.resizeImage(originalFilePath, largeFilePath, 1000, 1000);

	}

}
