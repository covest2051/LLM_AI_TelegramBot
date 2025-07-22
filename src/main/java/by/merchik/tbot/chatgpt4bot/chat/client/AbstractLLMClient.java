package by.merchik.tbot.chatgpt4bot.chat.client;

import by.merchik.tbot.chatgpt4bot.chat.api.ChatCompletionRequest;
import by.merchik.tbot.chatgpt4bot.chat.api.ChatCompletionResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractLLMClient implements LLMClient {
    private final String token;

    private final RestTemplate restTemplate;

    protected AbstractLLMClient(String token, RestTemplate restTemplate) {
        this.token = token;
        this.restTemplate = restTemplate;
    }

    public abstract String getModel();

    protected abstract String getSystemMessage();

    protected abstract String getUrl();

    @Override
    public ChatCompletionResponse createChatCompletion(ChatCompletionRequest chatCompletionRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + token);
        httpHeaders.set("Content-Type", "application/json");

        String body = String.format("""
            {
                "model": "%s",
                "messages": [
                    {"role": "system", "content": "%s"},
                    {"role": "user", "content": "%s"}
                ],
                "stream": false
            }
        """, getModel(), getSystemMessage(), chatCompletionRequest.getMessages().get(0).getContent());

        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<ChatCompletionResponse> response = restTemplate.exchange(
                getUrl(), HttpMethod.POST, request, ChatCompletionResponse.class);

        return response.getBody();
    }
}
