package UI;

import BO.UserHandler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserBean {

    private String username;
    private String password;
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserBean() {
        username="enter username";
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
    public boolean doLogin() {
        return UserHandler.login(username, password);
    }
    public Collection getNames() {
        return UserHandler.getUserNamesByName(username);
    }

    public String login() {
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        try {
            URL url = new URL("http://130.229.146.228:8080/Login");
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
            URL url = new URL("http://130.229.146.228:8080/Register");
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

    public String mypage() {
        BufferedReader br = null;
        InputStream inputStream = null;
        String redirectValue = "error.xhtml";
        System.out.println("Line is null");
        try {
            URL url = new URL("http://130.229.146.228:8080/MyPage");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestMethod("POST");
            OutputStreamWriter out = new OutputStreamWriter(
                    httpCon.getOutputStream());
            out.write("username=carlos" +"&password=test123");
            out.close();

            int responsecode = httpCon.getResponseCode();

            inputStream = httpCon.getInputStream();
            br = null;
            switch(responsecode) {
                case 200:
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    String line = br.readLine();

                    if(line!=null){
                        while(line != null){
                            System.out.println(line);
                            line = br.readLine();
                        }
                        redirectValue = "mypage.xhtml";
                    }
                    else {
                        System.out.println("Line is null");
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

}