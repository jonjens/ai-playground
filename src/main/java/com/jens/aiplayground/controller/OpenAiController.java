package com.jens.aiplayground.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class OpenAiController {

    private static final String API_KEY = "${dall-e}";
    private static final String DALLE_API_ENDPOINT = "https://api.openai.com/v1/images/variations";
    private static final String UPLOAD_DIR = "C:\\Users\\jonas\\uploadpath\\";

    // ... (other parts of the controller)

    @PostMapping(value = "/generate", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] generateImageVariation(@RequestBody MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Uploaded file is empty");
        }

        // Save uploaded file to a temporary location
        String uploadedFileName = file.getOriginalFilename();
        Path uploadPath = Paths.get(UPLOAD_DIR + uploadedFileName);
        Files.copy(file.getInputStream(), uploadPath);

        // Simulate calling DALL-E 2 API for image variation
        byte[] variationImageData = callDALLE2API(uploadPath);

        // Delete the temporary uploaded file
        Files.deleteIfExists(uploadPath);

        return variationImageData;
    }

    // Simulated method for calling DALL-E 2 API (replace with actual API call)
    private byte[] callDALLE2API(Path imagePath) throws IOException {
        // Here, you'd actually make an API call to DALL-E 2 using the image at 'imagePath'
        // Perform necessary processing and return the variation image data

        // This is a placeholder to read and return the image data (not calling DALL-E 2)
        return Files.readAllBytes(imagePath);
    }

}
