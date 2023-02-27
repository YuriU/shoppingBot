package com.shoppingbot.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMessageRequest {
    @JsonProperty("chat_id")
    private Long chatId;
    @JsonProperty("message")
    private String message;
    @JsonProperty("password")
    private String password;

    public Long getChatId() {
        return this.chatId;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPassword() {
        return this.password;
    }

}
