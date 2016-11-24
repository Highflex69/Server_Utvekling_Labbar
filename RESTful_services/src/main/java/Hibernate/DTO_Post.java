package Hibernate;

import DB.DB_User;

import java.io.Serializable;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_Post implements Serializable{
    private int id;
    private String content;
    private DB_User author;

    public DTO_Post(int id, String content, DB_User author)
    {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public DB_User getAuthor() {
        return author;
    }
}
