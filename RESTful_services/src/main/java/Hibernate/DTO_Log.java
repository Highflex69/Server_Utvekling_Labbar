package Hibernate;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_Log implements Serializable {
    private ArrayList<DTO_Post> postList;

    public DTO_Log()
    {
        postList = new ArrayList<DTO_Post>();
    }

    public void addPost(DTO_Post post)
    {
        postList.add(post);
    }

    public ArrayList<DTO_Post> getPostList()
    {
        return postList;
    }

}
