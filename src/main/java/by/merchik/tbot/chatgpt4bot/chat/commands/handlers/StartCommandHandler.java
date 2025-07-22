package by.merchik.tbot.chatgpt4bot.chat.commands.handlers;

import by.merchik.tbot.chatgpt4bot.chat.commands.TelegramCommands;
import by.merchik.tbot.chatgpt4bot.chat.commands.TelegramCommandsHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class StartCommandHandler implements TelegramCommandsHandler {
    private final String HELLO_MESSAGE = """
            Привет, %s,
            Этим ботом ты можешь пользоваться для общения с ИИ-моделью
            Бот поддерживает контекст диалога. Очистить контекст можно с помощью команды /clear
            """;

    @Override
    public BotApiMethod<?> processCommand(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(HELLO_MESSAGE.formatted(update.getMessage().getChat().getFirstName()))
                .build();
    }

    @Override
    public TelegramCommands getSupportedCommand() {
        return TelegramCommands.START_COMMAND;
    }
}
