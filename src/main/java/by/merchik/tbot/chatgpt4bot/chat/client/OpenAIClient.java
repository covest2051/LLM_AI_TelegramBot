package by.merchik.tbot.chatgpt4bot.chat.client;

import org.springframework.web.client.RestTemplate;

public class OpenAIClient extends AbstractLLMClient {

    private final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";

    public OpenAIClient(String token, RestTemplate restTemplate) {
        super(token, restTemplate);
    }

    @Override
    public String getModel() {
        return "gpt-4.1";
    }

    @Override
    protected String getSystemMessage() {
        return "You are a helpful assistant.";
    }

    @Override
    protected String getUrl() {
        return OPENAI_URL;
    }
}
