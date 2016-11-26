package Hibernate;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Teddy on 2016-11-26.
 */
public class DTO_Users implements Serializable{
    ArrayList<String> userList;
    public DTO_Users(){userList = new ArrayList<String>();}

    public  void addUser(String username)
    {
        userList.add(username);
    }

    public ArrayList<String> getUserList() {
        return userList;
    }
}
