package by.merchik.tbot.chatgpt4bot.config;

import by.merchik.tbot.chatgpt4bot.TelegramBot.TelegramBot;
import by.merchik.tbot.chatgpt4bot.chat.client.AbstractLLMClient;
import by.merchik.tbot.chatgpt4bot.chat.service.LLMService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class TelegramBotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
        var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramBot);
        return telegramBotsApi;
    }
}
