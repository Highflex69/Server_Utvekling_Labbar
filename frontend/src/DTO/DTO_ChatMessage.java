package DTO;

import java.io.Serializable;

public class DTO_ChatMessage implements Serializable {
    private long date;
    private String username;

    public DTO_ChatMessage(long date, String username, String message) {
        this.date = date;
        this.username = username;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String message;
}
