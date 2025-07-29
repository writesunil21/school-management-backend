package com.example.demo.controller;

import com.example.demo.service.SpeechService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/speech")
public class SpeechController {

    private final SpeechService speechService;

    public SpeechController(SpeechService speechService) {
        this.speechService = speechService;
    }

    @PostMapping("/transcribe")
    public ResponseEntity<String> transcribe(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("audio-", file.getOriginalFilename());
            file.transferTo(tempFile);

            String transcribedText = speechService.sendAudioToSpeechApi(tempFile);

            tempFile.delete(); // Cleanup
            return ResponseEntity.ok(transcribedText);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

