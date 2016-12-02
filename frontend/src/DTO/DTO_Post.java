package DTO;

import java.io.Serializable;

public class DTO_Post implements Serializable, Comparable<DTO_Post>{
    private int id;
    private String content;
    private String author;

    public DTO_Post(int id, String content, String author)
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

    public String getAuthor(){ return author;}

    @Override
    public int compareTo(DTO_Post dto_post) {
        if(this.id == dto_post.getId()){
            return 0;
        }
        else if(this.id < dto_post.getId()) {
            return 1;
        }

        return -1;
    }
}
