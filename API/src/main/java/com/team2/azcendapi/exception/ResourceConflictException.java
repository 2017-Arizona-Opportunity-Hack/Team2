package com.team2.azcendapi.exception;

public class ResourceConflictException extends RuntimeException {

    private final String customUserMessage;


    public ResourceConflictException(String s) {
        super(s);
        this.customUserMessage = "";
    }

    public ResourceConflictException(String s, String customUserMessage) {
        super(s);
        this.customUserMessage = customUserMessage;
    }

    public String getCustomUserMessage() {
        return customUserMessage;
    }
}
