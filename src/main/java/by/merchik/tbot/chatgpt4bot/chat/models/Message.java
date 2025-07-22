package by.merchik.tbot.chatgpt4bot.chat.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class Message {
    @JsonProperty("role")
    private final String role;
    @JsonProperty("content")
    private final String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

    public String getRole() {
        return role;
    }
    public String getContent() {
        return content;
    }
}