package by.merchik.tbot.chatgpt4bot.chat.client;

import org.springframework.web.client.RestTemplate;

public class OpenRouterClient extends AbstractLLMClient {

    private final String OPEN_ROUTER_URL = "https://openrouter.ai/api/v1/chat/completions";

    public OpenRouterClient(String token, RestTemplate restTemplate) {
        super(token, restTemplate);
    }

    @Override
    public String getModel() {
        return "deepseek/deepseek-chat-v3-0324:free";
    }

    @Override
    protected String getSystemMessage() {
        return "You are a helpful assistant.";
    }

    @Override
    protected String getUrl() {
        return OPEN_ROUTER_URL;
    }
}
