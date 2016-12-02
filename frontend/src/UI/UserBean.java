package UI;

import BO.UserHandler;
import DTO.*;
import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {


    public static final String urlpath = "http://172.16.83.82:8080/rest";
    //public static final String urlpath = "http://130.229.136.133:8080";

    private int id;
    @ManagedProperty("#{username}")
    private String username;
    @ManagedProperty("#{password}")
    private String password;
    private String name;
    private DetailsBean detailsBean;
    private LogBean logBean;
    private MenuView menuView;
    private MessageBean messageBean;
    private DTO_Messages inboxList;
    private ArrayList<DTO_Message> allMessages;
    DTO_Users friendsList;

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }



    public LogBean getLogBean() {
        return logBean;
    }

    public void setLogBean(LogBean logBean) {
        this.logBean = logBean;
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


    public void setAllMessages(ArrayList<DTO_Message> allMessages) {
        this.allMessages = allMessages;
    }

    @PostConstruct
    public void Init() {
        System.out.println("UserBean Init()");
        username="enter username";
        detailsBean = new DetailsBean();
        logBean = new LogBean();
        menuView = new MenuView();
        //inboxList = null;
        messageBean = new MessageBean();
        friendsList = new DTO_Users();

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
    /*public boolean doLogin() {
        return UserHandler.login(username, password);
    }*/
    public Collection getNames() {
        return UserHandler.getUserNamesByName(username);
    }

    public String oldLogin() {
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        try {
            URL url = new URL(urlpath +"/Login");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" +username +"&password=" +password);
            out.close();

            int responsecode = httpCon.getResponseCode();

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responsecode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();

                    if(line.equals("success")){

                        redirectValue = "mypage.xhtml";
                    }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        return redirectValue;
    }

    public String register(){
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        try {
            URL url = new URL(urlpath +"/Register");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("name="+name +"&username=" +username +"&password=" +password);
            out.close();

            int responsecode = httpCon.getResponseCode();

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responsecode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();

                    if(line.equals("success")){

                        redirectValue = "login.xhtml";
                    }

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        return redirectValue;
    }

    public String doLogin() {
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        try {
            URL url = new URL(urlpath +"/Login");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" +username +"&password=" +password);
            out.close();

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responseCode) {
                case 200:
                    //System.out.println(responsecode);
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    DTO_User myself = null;
                    System.out.println(line);
                    Gson gson = new Gson();
                    if(line != null) myself = gson.fromJson(line, DTO_User.class);

                    if(myself != null){
                        detailsBean.setId(myself.getId());
                        menuView.setMyId(myself.getId());
                        detailsBean.setName(myself.getName());
                        detailsBean.setUsername(myself.getUsername());
                        detailsBean.setNoOfUnreadMessages(myself.getNoOfUnreadMessages());
                        redirectValue = "mypage.xhtml";
                    }
                    break;
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        System.out.println("Details: " + detailsBean.toString());
        return redirectValue;
    }
    public String getDoLogout(){

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "You have been logged out";
    }

    public String getWelcomeMessage() {
        BufferedReader br = null;
        InputStream inputStream = null;
        String returnMessage = "Login failed.";
        try {
            URL url = new URL(urlpath +"/MyPage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());


            out.write("username=" +username +"&password=" +password);
            out.close();


            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();

                    Gson gson = new Gson();
                    DTO_User user = gson.fromJson(line, DTO_User.class);
                    detailsBean.setNoOfUnreadMessages(user.getNoOfUnreadMessages());
                    returnMessage = "Hello " +detailsBean.getName()  +" You have " +detailsBean.getNoOfUnreadMessages() +" unread messages.";
                    break;
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        return returnMessage;

    }

    public String getMyLog(){
        BufferedReader br = null;
        InputStream inputStream = null;
        String entries = "";
        try {
            URL url = new URL(urlpath +"/MyLogPage/GetLog");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" +username +"&password=" +password);
            out.close();

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();

            br = new BufferedReader(new InputStreamReader(inputStream));
            String gaysonString = br.readLine();
            Gson gayson = new Gson();
            DTO_Log log = gayson.fromJson(gaysonString, DTO_Log.class);
            System.out.println("Printing out content: ");

            StringBuilder sb = new StringBuilder();
            ArrayList<DTO_Post> postList = log.getPostList();
            for(DTO_Post post: postList) {
                sb.append(post.getContent() +"<br>");
            }
            entries = sb.toString();
            System.out.println(entries);

        }catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        return entries;
    }

    public String createLog(){
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        String content = logBean.getContent();
        if(content != null) {
            System.out.println(content);
        }
        try {
            URL url = new URL(urlpath +"/MyLogPage/CreateLog");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" +username +"&password=" +password +"&content=" +logBean.getContent());
            out.close();
            System.out.println(username +" " +password);

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responseCode) {
                case 200:
                    //System.out.println(responsecode);
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();

                    if(line == null) {
                        System.out.println("Line is null123");
                    }
                    else {
                        String[] lineSplit = null;
                        while (line != null){
                            System.out.println(line);
                            lineSplit = line.split(":");
                            System.out.println("lineSplit length " +lineSplit.length);
                            switch(lineSplit[0]){
                                case "id": detailsBean.setId(Integer.parseInt(lineSplit[1]));break;
                                case "name": detailsBean.setName(lineSplit[1]);
                                    System.out.println("name is: " +lineSplit[1]);break;
                                case "username": detailsBean.setUsername(lineSplit[1]); break;
                                case "noOfUnreadMessages": detailsBean.setNoOfUnreadMessages(Integer.parseInt(lineSplit[1])); break;
                            }
                            line = br.readLine();
                        }
                    }
                    redirectValue = "mypage.xhtml";
                    break;
                case 403:
                    //Wrong login
                    break;

            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        System.out.println("Details: " + detailsBean.toString());
        return redirectValue;
    }

    public String writeMessage(){
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        try {
            URL url = new URL(urlpath +"/MessagePage/CreateMessage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("PUT");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());

            messageBean.setIdFrom(username);

            DTO_Message message = messageBean.getDTO();

            Gson gson = new Gson();
            String toGson = gson.toJson(message);

            out.write("username=" +username +"&password=" +password +"&gson_message=" +toGson);
            out.close();
            System.out.println(username +" " +password);

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    redirectValue = "messageSent.xhtml";
                    break;
            }
        }catch (Exception e) {

            e.printStackTrace();
        }
        finally {
            if(br != null) { try{br.close();} catch (Exception e){} }
            if(inputStream != null) try{inputStream.close();} catch (Exception e){}
        }
        return redirectValue;
    }

    public String getMyMessages() {
        String redirect = "error.xhtml";

        return redirect;
    }

    public String getMyName() {
        return detailsBean.getName();
    }

    /**
     *  Navigation-menu
     */
    public String getMenu() {
        System.out.println("GetMenu(), username is: "  +username);
        return menuView.getNavigationMenu();
    }


    /**
     * Load DTO items from backend and present to UI presented in HTML code
     *
     *
     *
     *
     */

    /**
     * Get all messages belonging to user
     */


    public String getMessages() {
        //Return to ui
        String messages = "";

        //Download messages from backend
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlpath +"/MessagePage/GetMessages");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());

            out.write("username=" +username +"&password=" +password);
            out.close();

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " +responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    if(line != null) {
                        Gson gson = new Gson();
                        inboxList = gson.fromJson(line, DTO_Messages.class);
                        System.out.println("Messages received, size: " +inboxList.getMessagesList().size());
                        StringBuilder sb = new StringBuilder();
                        for(DTO_Message m: inboxList.getMessagesList()) {
                            if(!m.isRead()){sb.append("<b>");}
                            sb.append("<a href=display_message.xhtml?id=" +m.getId() +">");
                                sb.append(m.getTitle());
                            sb.append("</a>");
                            if(!m.isRead()){sb.append("</b>");}
                            sb.append("<br />");
                            messages = sb.toString();
                        }
                    }
                    break;
            }
        }catch (Exception e) {

            e.printStackTrace();
        }finally {
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
        }

        return messages;
    }

    /**
     * Read a message (downloaded by getMessages) post update for "isRead"-variable to backend if message was not
     * previously read.
     */
    public String getMessage() {
        if (password == null) {
            System.out.println("userBean is null");
        }

        String messageBody = "";
        int messageId = -1;
        DTO_Message readMessage = null;
        System.out.println("Testing... " +username);


        try {
            FacesContext fcontext = FacesContext.getCurrentInstance();
            Map<String, String> map = fcontext.getExternalContext().getRequestParameterMap();
            String mId = map.get("id");


            messageId = Integer.parseInt(mId);

        }catch (Exception e) {
            return "Error: id is not a integer.";
        }
        if(messageId < 0) {
            return "Message id cannot be negative number";
        }

        System.out.println("Message id: " +messageId);

        InputStream inputStream = null;
        BufferedReader br = null;
        try {

            URL url = new URL(urlpath + "/MessagePage/GetMessage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                        httpCon.getOutputStream());
            System.out.println(username + " " +password +" " +messageId);
            out.write("username=" +username +"&password=" +password +"&messageId=" +messageId);
            out.close();

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    Gson gson = new Gson();
                    readMessage = gson.fromJson(line, DTO_Message.class);
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    messageBody = messageBean.parseMessageToXhtml(readMessage);
                    break;
            }

        } catch (Exception e) {

            return "Error: message not found.";
        }finally {
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
        }

        return messageBody;
    }

    /**
     * Get all users from database and display with link to each users log in xhtml format
     */

    public String getAddFriend() {
        String addedFriend = "Could not add friend";

        FacesContext fcontext = FacesContext.getCurrentInstance();
        Map<String, String> map = fcontext.getExternalContext().getRequestParameterMap();
        String friendName = map.get("username");
        System.out.println("Friend to be added: " +friendName);
        try {
            InputStream inputStream = null;
            BufferedReader br = null;
            URL url = new URL(urlpath + "/UserPage/AddFriend");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password +"&friendusername=" + friendName);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    else if(line.equals("success")) {
                        System.out.println("Add friend is success");
                        friendsList.getUserList().add(friendName);
                        addedFriend = "Friend " +friendName +" is added to your friendslist. <br />";
                    }
                    else {
                        System.out.println("Response: " +line);
                    }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addedFriend;
    }

    public String getAllUsers() {
        DTO_Users allUsers = null;
        String displayUsers = "";
        try {
            InputStream inputStream = null;
            BufferedReader br = null;
            URL url = new URL(urlpath + "/UserPage/GetAllUsers");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" + password);
            out.close();

            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    Gson gson = new Gson();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    allUsers = gson.fromJson(line, DTO_Users.class);
                    ArrayList<String> userList = allUsers.getUserList();
                    System.out.println("Users size: " +userList.size());
                    StringBuilder sb = new StringBuilder();
                    for(String u: userList) {
                        System.out.println(u);
                        if(!u.equals(username)) {
                            sb.append("<a href=\"display_user.xhtml?username=" + u + "\">" + u + "</a>");
                            sb.append("    ");
                            sb.append("<a href=\"addfriend.xhtml?username=" + u +"\">Add as friend</a>");
                            sb.append("<br />");
                        }
                    }
                    displayUsers = sb.toString();
                    break;
            }

        } catch (Exception e) {

            return "Error: message not found.";
        }
        return displayUsers;
    }

    /**
     * Get log that belongs to a specified user
     */
    public String getUserLog() {
        String userLog = "Could not find user";

        FacesContext fcontext = FacesContext.getCurrentInstance();
        Map<String, String> map = fcontext.getExternalContext().getRequestParameterMap();
        String userQuery = map.get("username");
        try {
            InputStream inputStream = null;
            BufferedReader br = null;
            URL url = new URL(urlpath + "/UserPage/GetUserLog");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password +"&searchforusername=" + userQuery);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    Gson gson = new Gson();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    DTO_Log retreivedLogs = gson.fromJson(line, DTO_Log.class);
                    ArrayList<DTO_Post> logEntries = retreivedLogs.getPostList();
                    StringBuilder sb = new StringBuilder();
                    for(DTO_Post p: logEntries) {
                        System.out.println(p.getContent());
                        sb.append(p.getContent() + "<br/>");
                    }
                    userLog = sb.toString();
                    break;
            }

        } catch (Exception e) {

        }
        return userLog;
    }

    /**
     * Get all friends of a user
     */
    public String getFriendList() {
        String userFriends = "You have no friends";

        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlpath + "/MyPage/GetFriends");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    Gson gson = new Gson();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    DTO_Users friendL = gson.fromJson(line, DTO_Users.class);

                    friendsList = friendL;
                    System.out.println("Size is: " +friendsList.getUserList().size());

                    ArrayList<String> friends = friendL.getUserList();

                    StringBuilder sb = new StringBuilder();
                    System.out.println("Size: " +friends.size());
                    for(int i = 0; i < friends.size(); i++) {
                        sb.append(friends.get(i));
                        sb.append("<a href=\"removefriend.xhtml?username=" + friends.get(i)+"\"" + ">Remove Friend</a>");
                        sb.append("<br />");

                    }
                    userFriends = sb.toString();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            //if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            //if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
        }
        return userFriends;
    }

    public String getRemoveFriend() {
        String removedFriend = "Could not remove user";

        FacesContext fcontext = FacesContext.getCurrentInstance();
        Map<String, String> map = fcontext.getExternalContext().getRequestParameterMap();
        String friendName = map.get("username");
        try {
            InputStream inputStream = null;
            BufferedReader br = null;
            URL url = new URL(urlpath + "/UserPage/RemoveFriend");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password +"&friendusername=" + friendName);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    if(line.equals("success")) {
                        ArrayList<String> list = friendsList.getUserList();
                        for(int i = 0; i < list.size(); i++) {
                            String u = list.get(i);
                            list.remove(i);
                            removedFriend = "Friend " +u +"is removed from your friendslist. <br />";
                            break;
                        }
                    }
            }

        } catch (Exception e) {
        e.printStackTrace();
        }
        return removedFriend;
    }

    public String getMyFlow() {
        String flow = "No Logs to show";
        System.out.println("In getMyFlow()");
        InputStream inputStream = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlpath + "/MyPage/GetStream");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");

            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=" + username + "&password=" +password);
            out.close();
            int responseCode = httpCon.getResponseCode();
            System.out.println("Get flow, Responsecode: " + responseCode);

            inputStream = httpCon.getInputStream();
            br = null;
            switch (responseCode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();
                    Gson gson = new Gson();
                    if (line == null) {
                        return "Server responses with null or fail";
                    }
                    DTO_Log stream = gson.fromJson(line, DTO_Log.class);

                    ArrayList<DTO_Post> postList = stream.getPostList();
                    StringBuilder sb = new StringBuilder();
                    System.out.println("Postsize: " +postList.size());
                    for(DTO_Post p: postList) {
                        sb.append(p.getAuthor() +":<br />");
                        sb.append(p.getContent());
                        sb.append("<br />");

                    }
                    flow = sb.toString();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }finally {
            if(inputStream!=null)try { inputStream.close();}catch (Exception e){}
            if(br!=null)try { br.close();}catch (Exception e){}
        }
        return flow;
    }

}