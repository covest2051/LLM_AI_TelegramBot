package by.merchik.tbot.chatgpt4bot.chat.client;

import by.merchik.tbot.chatgpt4bot.chat.api.ChatCompletionRequest;
import by.merchik.tbot.chatgpt4bot.chat.api.ChatCompletionResponse;

public interface LLMClient {
    ChatCompletionResponse createChatCompletion(ChatCompletionRequest chatCompletionRequest);
}

