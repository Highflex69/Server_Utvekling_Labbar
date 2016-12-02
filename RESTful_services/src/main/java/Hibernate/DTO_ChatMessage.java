package Hibernate;

/**
 * Created by Teddy on 2016-11-28.
 */
public class DTO_ChatMessage {
    private long date;
    private String user;
    private String message;

    public DTO_ChatMessage(long date, String user, String message) {
        this.date = date;
        this.user = user;
        this.message = message;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
