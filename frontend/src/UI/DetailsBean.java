package UI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

@ManagedBean
@SessionScoped
/**
 * To be used for displaying view for my details, is destroyed after is viewed
 */
public class DetailsBean {
    int id;
    String name;
    String username;
    int noOfUnreadMessages;

    public DetailsBean() {
        id = -1;
        name = "dummy name";
        username = "dummy username";
        noOfUnreadMessages = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNoOfUnreadMessages() {
        return noOfUnreadMessages;
    }

    public void setNoOfUnreadMessages(int noOfUnreadMessages) {
        this.noOfUnreadMessages = noOfUnreadMessages;
    }

    @Override
    public String toString() {
        return "id:" +id +" name:" +name +" username" +username +" noOfUnreadMessages:"+noOfUnreadMessages;
    }



}
