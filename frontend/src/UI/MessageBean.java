package UI;

import DTO.DTO_Message;
import DTO.DTO_Messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;

@ManagedBean
@SessionScoped
public class MessageBean {
    private String title;
    private String content;
    private int idFrom;
    private String to;
    private ArrayList<DTO_Message> inboxList;

    public MessageBean() {
        inboxList = null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setInboxList(ArrayList<DTO_Message> messageList) {
        inboxList = messageList;
    }

    public DTO_Message getMessage(int id) {
        if(inboxList == null) {return null; }

        for(DTO_Message m: inboxList) {
            if(m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    public DTO_Message getDTO() {
        System.out.println("title: " +title + " content: " +content + " to: " +to + "idFrom " +idFrom);
        return new DTO_Message(-1 ,title, content, to, idFrom, false);
    }

    public String parseListToXhtml(DTO_Messages messageList) {
        if(messageList == null) return "";
        StringBuilder sb = new StringBuilder();
        ArrayList<DTO_Message> messageArray = messageList.getMessagesList();
        boolean isRead = false;

        for(DTO_Message m: messageArray) {
            isRead = m.isRead();
            if(!isRead) {sb.append("<b>");}
            sb.append("<a href=\"display_message.xhtml?id=" +m.getId() +"\"" +">" +m.getTitle() +"</a><br />");
            if(!isRead) {sb.append("</b>");}
        }
        return sb.toString();
    }

    public String parseMessageToXhtml(DTO_Message message) {
        if(message == null) return "Error, no message is passed to parseMessageToXhtml()";
        StringBuilder sb =  new StringBuilder();
        sb.append("To: " +message.getToUsername() +"<br />");
        sb.append("From: "  +message.getFromId() +"<br />");
        sb.append("Title: " +message.getTitle() +"<br />");
        sb.append("Content: " +message.getContent());
        return sb.toString();
    }


}
