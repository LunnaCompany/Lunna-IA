package com.testelunnaAI.testeAI.config;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LunnaAIConfig {
    @Bean
    public VertexAI vertexAI(){
        return new VertexAI("lunna-423119", "southamerica-east1");
    }

    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI){
        return new GenerativeModel("gemini-pro-vision", vertexAI);
    }
}
