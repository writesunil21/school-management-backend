package com.example.demo.service;

import com.example.demo.model.OcrResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;

@Service
public class OcrService {

    private final RestTemplate restTemplate;

    public OcrService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String sendImageToOcrApi(File imageFile) throws IOException {
        //String apiUrl = "https://ocr-api.onrender.com/ocr"; // Replace with your actual URL
        String apiUrl = "http://localhost:5002/ocr";


        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new FileSystemResource(imageFile));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<OcrResponse> response = restTemplate.postForEntity(apiUrl, requestEntity, OcrResponse.class);

        return response.getBody().getText();
    }
}

