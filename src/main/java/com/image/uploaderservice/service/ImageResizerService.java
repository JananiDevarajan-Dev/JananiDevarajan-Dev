package com.image.uploaderservice.service;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class ImageResizerService {
	public void resizeImage(String inputImagePath, String outputImagePath, int width, int height) throws IOException {
		Thumbnails.of(new File(inputImagePath)).size(width, height).toFile(new File(outputImagePath));
	}
}
