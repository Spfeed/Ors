package ru.me.Ors.util;

public class FacultyErrorResponse {

    private String message;

    public FacultyErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
