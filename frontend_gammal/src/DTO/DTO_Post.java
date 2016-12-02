package DTO;

import java.io.Serializable;

public class DTO_Post implements Serializable{
    private int id;
    private String content;
    private int author;

    public DTO_Post(int id, String content, int author)
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

    public int getAuthor(){ return author;}

}
