package DTO;

import java.io.Serializable;

public class DTO_User implements Serializable{
    int id;
    String name;
    String username;
    int noOfUnreadMessages;

    public DTO_User(int id, String name, String username, int noOfUnreadMessages) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.noOfUnreadMessages = noOfUnreadMessages;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getNoOfUnreadMessages() {
        return noOfUnreadMessages;
    }
}
