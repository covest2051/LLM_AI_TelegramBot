package by.merchik.tbot.chatgpt4bot.chat.commands;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramCommandsHandler {

    BotApiMethod<?> processCommand(Update update);

    TelegramCommands getSupportedCommand();
}
