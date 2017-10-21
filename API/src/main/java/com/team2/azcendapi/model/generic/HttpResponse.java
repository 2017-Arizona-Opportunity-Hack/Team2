package com.team2.azcendapi.model.generic;

import java.io.Serializable;

public class HttpResponse implements Serializable{

    private int status;
    private String message;

    public HttpResponse() {
        super();
    }

    public HttpResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
