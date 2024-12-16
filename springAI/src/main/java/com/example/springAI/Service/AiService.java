package com.example.springAI.Service;

// import com.javatechie.dto.LlamaResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
// import org.springframework.ai.chat.messages.Media;

import java.util.List;

@Service
public class AiService {

    @Autowired
    private OllamaChatModel ollamaChatModel;

    public String filterQuery(String query) {
        String[] relevantKeywords = { "car", "tire", "alignment", "repair", "wheel", "automobile" };
        for (String keyword : relevantKeywords) {
            if (query.toLowerCase().contains(keyword)) {
                return null; // Query is valid
            }
        }
        return "Sorry, I can only answer questions related to cars and tires.";
    }

    public String processResponse(String query, String response) {
        if (response.toLowerCase().contains("unrelated") || response.toLowerCase().contains("not about cars")) {
            return "Sorry, I can only answer questions about cars and tires.";
        }
        return response;
    }

    public String generateMessage(String promptMessage) {
        try {
            ChatResponse response = ollamaChatModel.call(
                    new Prompt(
                            List.of(new UserMessage(promptMessage)),
                            OllamaOptions.create().withModel("llama3.2")));
            return processResponse(promptMessage, response.getResult().getOutput().getContent());
            // return response.getResult().getOutput().getContent();
        } catch (Exception e) {
            // Log the exception and return a friendly error message
            e.printStackTrace();
            return "Error generating response: " + e.getMessage();
        }
    }
}
