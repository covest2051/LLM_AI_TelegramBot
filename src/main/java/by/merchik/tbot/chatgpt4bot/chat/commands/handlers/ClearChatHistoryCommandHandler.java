package by.merchik.tbot.chatgpt4bot.chat.commands.handlers;

import by.merchik.tbot.chatgpt4bot.chat.commands.TelegramCommands;
import by.merchik.tbot.chatgpt4bot.chat.commands.TelegramCommandsHandler;
import by.merchik.tbot.chatgpt4bot.chat.models.ChatHistory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ClearChatHistoryCommandHandler implements TelegramCommandsHandler {
    private final String CLEAR_MESSAGE = """
            Контекст диалога был сброшен
            """;
    private final ChatHistory chatHistory;

    public ClearChatHistoryCommandHandler(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }

    @Override
    public BotApiMethod<?> processCommand(Update update) {
        chatHistory.clearChatHistory(update.getMessage().getChatId());
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(CLEAR_MESSAGE)
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.CLEAR_COMMAND;
    }
}
