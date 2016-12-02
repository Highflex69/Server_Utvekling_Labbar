package UI;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean
public class LogBean {
    private int userId;
    private String content;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int serId) {
        this.userId = serId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
