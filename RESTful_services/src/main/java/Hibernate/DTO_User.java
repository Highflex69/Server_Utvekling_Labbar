package Hibernate;

import java.io.Serializable;

/**
 * Created by Teddy on 2016-11-24.
 */
public class DTO_User implements Serializable {

    private int id;

    private String name;

    private String username;

    private int noOfUnreadMessages;

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
