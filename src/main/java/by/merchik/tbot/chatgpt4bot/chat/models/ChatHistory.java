package by.merchik.tbot.chatgpt4bot.chat.models;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class ChatHistory {

    private final Map<Long, MessagesHistory> messagesHistoryMap = new ConcurrentHashMap<>();

    public Optional<MessagesHistory> getUserHistory(Long userId) {
        return Optional.ofNullable(messagesHistoryMap.get(userId));
    }

    public void createChatHistory(Long userId) {
        messagesHistoryMap.put(userId, new MessagesHistory(new ArrayList<>()));
    }

    public void clearChatHistory(Long userId) {
        messagesHistoryMap.remove(userId);
    }

    public MessagesHistory addMessageToHistory(Long userId, Message message) {
        var messagesHistory = messagesHistoryMap.get(userId);
        if(messagesHistory == null) {
            throw new IllegalStateException("History not exist for user =%s".formatted(userId));
        }
        messagesHistory.chatMessages().add(message);
        return messagesHistory;
    }

    public void createChatHistoryIfNotExist(Long userId) {
        if(!messagesHistoryMap.containsKey(userId)) {
            createChatHistory(userId);
        }
    }
}
