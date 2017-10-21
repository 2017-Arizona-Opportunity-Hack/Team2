package com.team2.azcendapi.model.generic;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ErrorMessage {

    private int status;
    private HttpStatus statusText;
    private String developerMessage;
    private String userMessage;

    public ErrorMessage(int status, HttpStatus statusText, String developerMessage, String userMessage) {
        this.status = status;
        this.statusText = statusText;
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public HttpStatus getStatusText() {
        return statusText;
    }

    public void setStatusText(HttpStatus statusText) {
        this.statusText = statusText;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
