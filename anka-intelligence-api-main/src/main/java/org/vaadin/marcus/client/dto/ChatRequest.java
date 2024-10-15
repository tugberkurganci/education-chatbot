package org.vaadin.marcus.client.dto;

public class ChatRequest {

    private String walletId;

    private String chatId;

    private String userMessage;

    public ChatRequest() {
    }

    public ChatRequest(String chatId, String userMessage, String walletId) {
        this.walletId = walletId;
        this.chatId = chatId;
        this.userMessage = userMessage;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
