package by.merchik.tbot.chatgpt4bot.chat.service;

import by.merchik.tbot.chatgpt4bot.chat.client.AbstractLLMClient;
import by.merchik.tbot.chatgpt4bot.chat.api.ChatCompletionRequest;
import by.merchik.tbot.chatgpt4bot.chat.models.ChatHistory;
import by.merchik.tbot.chatgpt4bot.chat.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {
    private final AbstractLLMClient llmClient;
    private final ChatHistory chatHistory;

    public LLMService(AbstractLLMClient llmClient, ChatHistory chatHistory) {
        this.llmClient = llmClient;
        this.chatHistory = chatHistory;
    }

    public String getResponseChatForUser(
        Long userId,
        String userTextInput
    ) {
        chatHistory.createChatHistoryIfNotExist(userId);

        var history = chatHistory.addMessageToHistory(
                userId,
                new Message("user", userTextInput)
        );

        var request = new ChatCompletionRequest(
                llmClient.getModel(),
                history.chatMessages()
        );

        var response = llmClient.createChatCompletion(request);

        var messageFromChat = response.choices().get(0)
                .message();

        chatHistory.addMessageToHistory(userId, messageFromChat);

        return messageFromChat.getContent();
    }
}
