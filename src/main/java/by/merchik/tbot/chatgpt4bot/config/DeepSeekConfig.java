package by.merchik.tbot.chatgpt4bot.config;

import by.merchik.tbot.chatgpt4bot.chat.client.DeepSeekClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeepSeekConfig {
    public DeepSeekClient deepSeekClient;

    @Bean
    public DeepSeekClient deepSeekClient(
            @Value("${deepseek.token}") String deepseekToken,
            RestTemplateBuilder restTemplateBuilder
    ) {
        return new DeepSeekClient(deepseekToken, restTemplateBuilder.build());
    }
}