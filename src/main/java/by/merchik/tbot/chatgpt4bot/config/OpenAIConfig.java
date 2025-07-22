package by.merchik.tbot.chatgpt4bot.config;

import by.merchik.tbot.chatgpt4bot.chat.client.OpenAIClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAIConfig {
    public OpenAIClient openAIClient;

    @Bean
    public OpenAIClient openAIClient(
            @Value("${openai.token}") String openAIToken,
            RestTemplateBuilder restTemplateBuilder
        ) {
        return new OpenAIClient(openAIToken, restTemplateBuilder.build());
    }
}
