package by.merchik.tbot.chatgpt4bot.chat.api;

import by.merchik.tbot.chatgpt4bot.chat.models.Message;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
public class ChatCompletionRequest {
    private final String model;
    private final List<Message> messages;

    public ChatCompletionRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }
    public List<Message> getMessages() {
        return messages;
    }
}