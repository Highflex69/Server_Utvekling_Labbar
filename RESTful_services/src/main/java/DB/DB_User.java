package DB;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-21.
 */
@Entity
@Table(name = "T_USER")
public class DB_User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    private int id;

    @Column( name = "C_NAME", nullable = false)
    private String name;

    @Column(name = "C_USERNAME", unique = true, nullable = false)
    private String username;

    @Column( name = "C_PASSWORD", nullable = false)
    private String password;

    public DB_User(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public DB_User() {
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
