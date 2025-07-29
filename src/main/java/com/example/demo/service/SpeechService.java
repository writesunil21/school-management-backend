package com.example.demo.service;

import com.example.demo.model.SpeechResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;

@Service
public class SpeechService {

    private final RestTemplate restTemplate;

    public SpeechService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String sendAudioToSpeechApi(File audioFile) throws IOException {
        //String apiUrl = "https://speech-api.onrender.com/transcribe";  // ✅ Replace with your URL
        String apiUrl = "http://localhost:5001/transcribe"; // local testing

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("audio", new FileSystemResource(audioFile));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<SpeechResponse> response =
                restTemplate.postForEntity(apiUrl, requestEntity, SpeechResponse.class);

        return response.getBody().getText();
    }
}

