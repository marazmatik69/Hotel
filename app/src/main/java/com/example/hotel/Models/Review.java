package com.example.hotel.Models;

import java.util.Map;

public class Review {
    private String clientId;
    private String clientName;
    private String clientEmail;
    private String typeMessage;
    private String textMessage;

    public Review() {
    }

    public Review(String clientId, String clientName, String clientEmail, String typeMessage, String textMessage) { this.clientId = clientId;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.typeMessage = typeMessage;
        this.textMessage = textMessage;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(String typeMessage) {
        this.typeMessage = typeMessage;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
