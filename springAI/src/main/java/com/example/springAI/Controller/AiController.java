package com.example.springAI.Controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import org.springframework.http.ResponseEntity;
import java.util.Map;

import com.example.springAI.Service.AiService;
import com.example.springAI.mxnet_inference.ObjectDetection;

@RestController
public class AiController {

    @Autowired
    private AiService aiService;

    @GetMapping("/ai/chat")
    public String generateMessage(@RequestParam(value = "promptMessage") String promptMessage) {
        String filterResult = aiService.filterQuery(promptMessage);
        if (filterResult != null) {
            return filterResult; // Return rejection message
        }
        return aiService.generateMessage(promptMessage);
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "enquiry") String msg) {
        try {
            ObjectDetection.predict();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return msg + "hello world";
    }

}