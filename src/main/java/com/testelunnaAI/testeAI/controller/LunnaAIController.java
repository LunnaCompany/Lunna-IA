package com.testelunnaAI.testeAI.controller;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lunna-ia")
@RequiredArgsConstructor
public class LunnaAIController {
    private final GenerativeModel generativeModel;
    @PostMapping
    public String file(@RequestParam String question, @RequestParam("file") MultipartFile file) throws IOException {
        GenerateContentResponse generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromMultiModalData(
                        PartMaker.fromMimeTypeAndData(file.getContentType(), file.getBytes()),
                        question
                )
        );
        return ResponseHandler.getText(generateContentResponse);
    }

    @PostMapping("/chat")
    public Map<String, String> chat(@RequestParam String question) throws IOException {
        GenerateContentResponse generateContentResponse = this.generativeModel.generateContent(
                ContentMaker.fromString(question)
        );
        String responseText = ResponseHandler.getText(generateContentResponse);

        // Retorna um Map contendo o HTML
        Map<String, String> response = new HashMap<>();
        response.put("html", responseText);

        return response;
    }
}
