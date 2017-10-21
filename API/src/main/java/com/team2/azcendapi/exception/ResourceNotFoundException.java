package com.team2.azcendapi.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final String customUserMessage;


    public ResourceNotFoundException(String s) {
        super(s);
        this.customUserMessage = "";
    }

    public ResourceNotFoundException(String s, String customUserMessage) {
        super(s);
        this.customUserMessage = customUserMessage;
    }

    public String getCustomUserMessage() {
        return customUserMessage;
    }


}