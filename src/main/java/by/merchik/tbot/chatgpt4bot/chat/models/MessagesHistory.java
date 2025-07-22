package by.merchik.tbot.chatgpt4bot.chat.models;

import lombok.Builder;

import java.util.List;

@Builder
public record MessagesHistory(
    List<Message> chatMessages
) {
}
