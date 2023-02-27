package com.shoppingbot.bot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ShoppingBot extends TelegramWebhookBot {
    private static final Logger LOG = Logger.getLogger(ShoppingBot.class);
    private final String token;
    private final String userName;
    private final String botPath;

    public ShoppingBot(String token, String userName, String botPath) {
        this.token = token;
        this.userName = userName;
        this.botPath = botPath;
    }

    @Override
    public String getBotUsername() {
        return userName;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotPath() {
        return botPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        LOG.info("Update received");
        sendMessage(update.getMessage().getChatId(), "Putin Huilo!");
        return null;
    }

    public void sendMessage(Long chatId, String text) {
        try {
            execute(SendMessage
                    .builder()
                    .chatId(chatId)
                    .text(text)
                    .build());

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
