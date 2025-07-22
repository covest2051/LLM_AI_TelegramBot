package by.merchik.tbot.chatgpt4bot.chat.api;

import by.merchik.tbot.chatgpt4bot.chat.models.Message;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(@JsonProperty("message") Message message) {
}
