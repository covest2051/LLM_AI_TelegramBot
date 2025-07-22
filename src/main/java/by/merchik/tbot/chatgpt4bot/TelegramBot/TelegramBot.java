package by.merchik.tbot.chatgpt4bot.TelegramBot;

import by.merchik.tbot.chatgpt4bot.chat.commands.TelegramCommandsDispatcher;
import by.merchik.tbot.chatgpt4bot.chat.service.LLMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final LLMService llmService;
    private final TelegramCommandsDispatcher telegramCommandsDispatcher;

    public TelegramBot(@Value("${bot.token}") String botToken, LLMService llmService, TelegramCommandsDispatcher telegramCommandsDispatcher) {
        super(new DefaultBotOptions(), botToken);
        this.llmService = llmService;
        this.telegramCommandsDispatcher = telegramCommandsDispatcher;
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            var methods = processUpdate(update);
            methods.forEach(method -> {
                try {
                    sendApiMethod(method);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while processing update", e);
            try {
                sendUserErrorMessage(update.getMessage().getChatId());
            } catch (TelegramApiException ex) {
                throw new RuntimeException(ex);
            }
        }


    }

    private List<BotApiMethod<?>> processUpdate(Update update) {
        if (telegramCommandsDispatcher.isCommand(update)) {
            return List.of(telegramCommandsDispatcher.processCommand(update));
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            var text = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            var generatedText = llmService.getResponseChatForUser(chatId, text);

            SendMessage sendMessage = new SendMessage(chatId.toString(), generatedText);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
            return List.of(sendMessage);
        }
        return List.of();
    }

    private void sendUserErrorMessage(Long userId) throws TelegramApiException {
        sendApiMethod(SendMessage.builder()
                .chatId(userId)
                .text("Произошла ошибка, попробуйте позже")
                .build());
    }

    @Override
    public String getBotUsername() {
        return "ChatGTP4Bot";
    }
}
