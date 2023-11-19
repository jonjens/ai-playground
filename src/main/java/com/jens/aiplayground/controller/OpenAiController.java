package com.jens.aiplayground.controller;

import com.jens.aiplayground.service.EnvService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
public class OpenAiController {

    @Autowired
    EnvService envService;

    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/variations";
    private static final String UPLOAD_DIR = "C:\\Users\\jonas\\uploadpath\\";

    @PostMapping("/dalle")
    public ResponseEntity<byte[]> getDALLEResponse(MultipartFile file) {
        String API_KEY = envService.getKey();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(API_ENDPOINT);
        request.setHeader("Authorization", "Bearer " + API_KEY);

        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("image", file.getBytes(), ContentType.APPLICATION_OCTET_STREAM, file.getOriginalFilename());
            builder.addTextBody("n", "1");
            builder.addTextBody("size", "512x512");

            HttpEntity multipart = builder.build();
            request.setEntity(multipart);

            HttpResponse response = httpClient.execute(request);

            // Process the response and return it
            return ResponseEntity.status(response.getStatusLine().getStatusCode())
                    .body(response.getEntity().getContent().readAllBytes());

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build(); // Handle error response
        }
    }
}