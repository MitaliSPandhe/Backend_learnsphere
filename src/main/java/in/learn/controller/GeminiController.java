package in.learn.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.learn.service.GeminiService;


@RestController
@RequestMapping("/api/chat")
public class GeminiController {

    private final GeminiService geminiService;


    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @PostMapping("/ask")
    public String askGeminiAPI(@RequestBody String prompt) {
        return geminiService.askGemini(prompt);
    }
}
