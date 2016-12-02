package UI;


import DTO.DTO_ChatMessage;
import DTO.DTO_Log;
import DTO.DTO_Post;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import org.primefaces.context.RequestContext;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@ManagedBean
@ViewScoped
public class ChatBean {
    private final List messageList;
    private long updateTime;
    private DTO_ChatMessage message;
    private String username;
    private String password;

    public ChatBean() {
        messageList = Collections.synchronizedList(new LinkedList<>());
        updateTime = new Date().getTime();
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public DTO_ChatMessage getMessage() {
        return message;
    }

    public void setMessage(DTO_ChatMessage message) {
        this.message = message;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void sendChatMessage(ActionEvent event) {

        System.out.println("In ChatBean.sendChatMessage()");
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            String urlpath = UserBean.urlpath;
            URL url = new URL(urlpath + "/Chat/SendMessage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            Gson gson = new Gson();
            String mess = gson.toJson(message);
            out.write("username=" + username + "&password=" +password +"&gsonMessage=" +mess);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Send message, Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            if(br!=null)try { br.close();}catch (Exception e){}
        }
    }

    public void getUnreadMessage(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println("In ChatBean.getUnreadtMessage()");
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            String urlpath = UserBean.urlpath;
            URL url = new URL(urlpath + "/Chat/GetMessage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password +"&lastupdate=" +updateTime);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Send message, Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    if(line != null) {
                        Gson gson = new Gson();
                        DTO_ChatMessage mess = gson.fromJson(line, DTO_ChatMessage.class);
                        context.addCallbackParam("ok", mess!=null);
                        if(mess == null) {
                            return;
                        }
                        updateTime = mess.getDate();
                        context.addCallbackParam("senduser", mess.getUsername());
                        context.addCallbackParam("messagereceived", mess.getMessage());
                    }
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            if(br!=null)try { br.close();}catch (Exception e){}
        }

    }
}
