package com.shoppingbot.api;

import com.shoppingbot.bot.ShoppingBot;

public class BotHandler {
    private static final ShoppingBot BOT = GetInitializedBot();

    private static final String PASSWORD = System.getenv().get("SEND_MESSAGE_PASSWORD");
    private static ShoppingBot GetInitializedBot() {
       return new ShoppingBot(
                System.getenv().get("TELEGRAM_BOT_KEY"),
                "Bot",
                "Path"
        );
    }

    protected ShoppingBot getBot() {
        return BOT;
    }

    protected String getSendMessagePassword() {
        return PASSWORD;
    }
}
