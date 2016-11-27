package Hibernate;

import DB.DB_User;

import java.io.Serializable;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_Post implements Serializable, Comparable<DTO_Post>{
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

    public int getAuthor() {
        return author;
    }

    public int compareTo(DTO_Post dto_post) {
        if(this.id == dto_post.getId())
        {
            return 0;
        }
        else if(this.id < dto_post.getId())
        {
            return 1;
        }

        return -1;

    }
}
