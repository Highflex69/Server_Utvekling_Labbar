package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class DTO_UserSearch implements Serializable{
    ArrayList<String> usernameList;

    public DTO_UserSearch(){ usernameList = new ArrayList<>();}

    public ArrayList<String> getUsernameList() { return usernameList;}

    public void add(String username) {usernameList.add(username);}

}
