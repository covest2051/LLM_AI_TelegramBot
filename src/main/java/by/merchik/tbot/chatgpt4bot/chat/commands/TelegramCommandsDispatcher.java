package by.merchik.tbot.chatgpt4bot.chat.commands;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
@AllArgsConstructor
public class TelegramCommandsDispatcher {
    private final List<TelegramCommandsHandler> telegramCommandsHandlerList;

    public BotApiMethod<?> processCommand(Update update) {
        var text = update.getMessage().getText();
        if (!isCommand(update)) {
            throw new IllegalArgumentException("Not a command passed");
        }
        var suitedHandler = telegramCommandsHandlerList.stream()
                .filter(handler -> handler.getSupportedCommand().getCommandValue().equals(text))
                .findAny();

        if(suitedHandler.isEmpty()) {
            var chatId = update.getMessage().getChatId();

            return SendMessage.builder()
                    .chatId(chatId)
                    .text("Not supported command: command=%s".formatted(text))
                    .build();
        }
        return suitedHandler.orElseThrow().processCommand(update);
    }

    public boolean isCommand(Update update) {
        var text = update.getMessage().getText();

        return text.startsWith("/");
    }
}
