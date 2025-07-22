package by.merchik.tbot.chatgpt4bot.chat.commands;

public enum TelegramCommands {
    START_COMMAND("/start"),
    CLEAR_COMMAND("/clear");

    private final String commandValue;

    TelegramCommands(String commandValue) {
        this.commandValue = commandValue;
    }

    public String getCommandValue() {
        return commandValue;
    }
}
