package in.learn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.genai.Client;

@Configuration
public class GeminiConfig {

	@Value("${gemini.api.key}")
	private String apiKey;

	@Bean
	public Client geminiClient() {

		if (apiKey == null || apiKey.isBlank()) {
			throw new IllegalStateException("Gemini API key is missing!");
		}

		return Client.builder().apiKey(apiKey).build();
	}
}
