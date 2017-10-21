package com.team2.azcendapi.exception;

public class BadRequestException extends RuntimeException {

    private final String customUserMessage;


    public BadRequestException(String s) {
        super(s);
        this.customUserMessage = "";
    }

    public BadRequestException(String s, String customUserMessage) {
        super(s);
        this.customUserMessage = customUserMessage;
    }

    public String getCustomUserMessage() {
        return customUserMessage;
    }

}
