package by.merchik.tbot.chatgpt4bot.config;

import by.merchik.tbot.chatgpt4bot.chat.client.OpenRouterClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenRouterConfig {

    @Bean
    public OpenRouterClient openRouterClient(
            @Value("${openrouter.token}") String token,
            RestTemplateBuilder builder
    ) {
        return new OpenRouterClient(token, builder.build());
    }
}
