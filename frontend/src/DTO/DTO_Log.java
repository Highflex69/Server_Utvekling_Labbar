package DTO;

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

    public ArrayList<DTO_Post> getPostList()
    {
        return postList;
    }

    public void addPost(DTO_Post post)
    {
        postList.add(post);
    }

}
