package Controller;

import BO.UserHandler;
import DB.DB_User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by Teddy on 2016-11-21.
 */
@ManagedBean
@SessionScoped
public class UserBean {
    private String username;
    private String password;

    public UserBean()
    {
        this.username = "enter username";
    }

    public String getPassWord()
    {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }

    public String getUserName() {
        return username;
    }

    public boolean doLogin()
    {
        return UserHandler.login(username, password);
    }

    public DB_User getNames()
    {
        return UserHandler.getUsernamesByName(username);
    }
}
