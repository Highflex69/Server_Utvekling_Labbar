package UI;

import DTO.DTO_Message;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MessageBean {
    private String title;
    private String content;
    private int idFrom;
    private String to;

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

    public DTO_Message getDTO() {
        System.out.println("title: " +title + " content: " +content + " to: " +to + "idFrom " +idFrom);
        return new DTO_Message(-1 ,title, content, to, idFrom, false);
    }



}
