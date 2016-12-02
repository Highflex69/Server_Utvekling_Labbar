package BO;

import java.util.Collection;

public class User {
    private String name;
    private String pass;
    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public static Collection findByName(String name) {
        //Return null for now
        return null;
    }
}