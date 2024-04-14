package ru.me.Ors.util;

public class CampusErrorResponse {
    private String message;

    public CampusErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
