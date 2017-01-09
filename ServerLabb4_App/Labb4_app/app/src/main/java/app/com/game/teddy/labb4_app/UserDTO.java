package app.com.game.teddy.labb4_app;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2017-01-09.
 */

public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String email;
    private int status;
    private List<BandDTO> bandList;

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
        this.name = "empty";
        this.email = "empty";
        this.status= -1;
        this.bandList = new ArrayList<BandDTO>();
    }

    public UserDTO(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getStatus() {
        return status;
    }

    public List<BandDTO> getBandList() {
        return bandList;
    }

    public String getPassword() {
        return password;
    }

}
