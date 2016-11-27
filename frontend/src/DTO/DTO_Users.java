package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class DTO_Users implements Serializable {
    ArrayList<String> userList;

    public DTO_Users(){ userList = new ArrayList<>();}

    public void addUser(String username) {
        userList.add(username);
    }

    public ArrayList<String> getUserList() {
        return userList;
    }
}
