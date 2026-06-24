package com.dhruba.spring_ai.chat;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    @Value("${spring.ai.google.genai.api-key}")
    private String key;
    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping("/chat")
    public String chat() {
        System.out.println(key);
        return chatClient.prompt()
                .user("Tell me a joke about java")
                .call()
                .content();
    }

    @GetMapping(value = "/stream")
    public Flux<String> stream(){
        return chatClient.prompt()
                .user("I want to visit Mumbai, guide me what are the places I must visit")
                .stream()
                .content();

    }
}
