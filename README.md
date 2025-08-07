# ChatGTP4Bot

ChatGTP4Bot is a Telegram bot on Spring Boot that allows you to communicate with various language models (DeepSeek, OpenAI, OpenRouter and HuggingFace) without too much fuss.

## Main features

It is enough to send a message to the bot, and it will respond through the LLM service of your choice. The history of correspondence is automatically saved, and the '/clear` command clears the context with one click. At the start, the bot greets you and tells you briefly about its work.

## How to get started

1. Clone the repository and navigate to the project folder.
2. In `src/main/resources/application.properties', specify the tokens for Telegram and LLM services, and select the model using the `llm.model` parameter (deepseek, openai, or openrouter).
3. Build the application with the command `mvn clean package` and run it: `java -jar target/ChatGTP4Bot-<version>.jar`.

After launching, the bot becomes available in Telegram — just start the dialogue.

## Project structure

The code is divided into logical blocks:

* **API** (`chat.api') describes LLM requests and responses.
* **Client** (`chat.client') implements abstractions for different providers.
* **Models** (`chat.models`) store messages and the history of the conversation.
* **Commands** (`chat.commands') process `/start` and `/clear'.
* **Service** (`chat.service') links everything together by sending requests and saving responses.
* **TelegramBot** launches the bot and responds to updates.

## Configuration

In `LLMConfig`, the desired implementation is selected via the `AbstractLLMClient` bean based on the `llm.model` property. If an unknown value is specified, the application will issue an error.

## Bot screenshot:

<img width="1889" height="1487" alt="Снимок экрана 2025-08-07 143244" src="https://github.com/user-attachments/assets/d62da938-1b2c-48b7-87d6-66d8c4747528" />
