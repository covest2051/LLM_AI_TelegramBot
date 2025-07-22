package by.merchik.tbot.chatgpt4bot.chat.client;

import org.springframework.web.client.RestTemplate;

public class LLMHuggingFaceClient extends AbstractLLMClient {

    private final String HUGGING_FACE_URL = "https://api-inference.huggingface.co/models/deepseek-ai/DeepSeek-R1-Distill-Qwen-1.5B";

    public LLMHuggingFaceClient(String token, RestTemplate restTemplate) {
        super(token, restTemplate);
    }

    @Override
    public String getModel() {
        return "deepseek-ai/DeepSeek-R1-Distill-Qwen-1.5B";
    }

    @Override
    protected String getSystemMessage() {
        return "You are a helpful assistant.";
    }

    @Override
    protected String getUrl() {
        return HUGGING_FACE_URL;
    }
}