package Hibernate;

import java.io.Serializable;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_Message implements Serializable {

    private int id;
    private String title;
    private String content;
    private String toUsername;
    private int fromId;
    private boolean isRead;

    public DTO_Message(int id, String title, String content, String toUsername, int fromId, boolean isRead) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.toUsername = toUsername;
        this.fromId = fromId;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getToUsername() {
        return toUsername;
    }

    public int getFromId() {
        return fromId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead() {
        isRead = true;
    }
}
