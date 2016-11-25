package UI;

import DTO.DTO_Message;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;

@ManagedBean
public class MenuView {

    private int myId;

    public MenuView() {
        myId = -1;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getNavigationMenu() {
        if(myId == -1) {
            return "Please log in.";
        }
        else {
            StringBuilder sb = new StringBuilder();
            //Mypage
            sb.append("<form action=\"mypage.xhtml\" method=\"get\"><input type=\"submit\" name=\"mypage\" value=\"My Page\" /></form>");
            //Read messages
            sb.append("<form action=\"read_message.xhtml\" method=\"get\"><input type=\"submit\" name=\"readmessages\" value=\"Read Messages\" /></form>");
            //Write message
            sb.append("<form action=\"write_message.xhtml\" method=\"get\"><input type=\"submit\" name=\"writemessages\" value=\"Write Message\" /></form>");
            //My Log
            sb.append("<form action=\"mylog.xhtml\" method=\"get\"><input type=\"submit\" name=\"mylog\" value=\"My Log\" /></form>");
            //Search for users
            sb.append("<form action=\"search.xhtml\" method=\"get\"><input type=\"submit\" name=\"search\" value=\"Search\" /></form>");
            return sb.toString();
        }

    }
}
