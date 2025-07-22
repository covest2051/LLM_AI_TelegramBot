package by.merchik.tbot.chatgpt4bot.config;

import by.merchik.tbot.chatgpt4bot.chat.client.DeepSeekClient;
import by.merchik.tbot.chatgpt4bot.chat.client.AbstractLLMClient;
import by.merchik.tbot.chatgpt4bot.chat.client.OpenAIClient;
import by.merchik.tbot.chatgpt4bot.chat.client.OpenRouterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfig {

    @Bean
    public AbstractLLMClient llmClient(
            @Value("${llm.model}") String model,
            DeepSeekClient deepSeekClient,
            OpenAIClient openAIClient,
            OpenRouterClient openRouterClient
    ) {
        return switch (model.toLowerCase()) {
            case "deepseek" -> deepSeekClient;
            case "openai" -> openAIClient;
            case "openrouter" -> openRouterClient;
            default -> throw new IllegalArgumentException("Unknown model: " + model);
        };
    }
}