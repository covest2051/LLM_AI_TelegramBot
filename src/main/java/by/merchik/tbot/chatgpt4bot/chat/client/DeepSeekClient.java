package by.merchik.tbot.chatgpt4bot.chat.client;

import org.springframework.web.client.RestTemplate;

public class DeepSeekClient extends AbstractLLMClient {

    private final String DEEPSEEK_URL = "https://api.deepseek.com/chat/completions";

    public DeepSeekClient(String token, RestTemplate restTemplate) {
        super(token, restTemplate);
    }

    @Override
    public String getModel() {
        return "deepseek-chat";
    }

    @Override
    protected String getSystemMessage() {
        return "You are a helpful assistant.";
    }

    @Override
    protected String getUrl() {
        return DEEPSEEK_URL;
    }
}
