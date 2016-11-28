package DB;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "T_FRIENDS",
            joinColumns = {@JoinColumn(name = "C_USER_ID")},
            inverseJoinColumns={@JoinColumn(name = "C_FRIENd_ID")})
    private List<DB_User> friends = null;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "T_FRIENDS",
            joinColumns = {@JoinColumn(name = "C_FRIENd_ID")},
            inverseJoinColumns={@JoinColumn(name = "C_USER_ID")})
    private List<DB_User> friendOf = null;

    public DB_User(String name, String username, String password)
    {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public DB_User() {
        this.friends = new ArrayList<DB_User>();
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

    public List<DB_User> getFriends() {
        return friends;
    }

    public void addFriend(DB_User user)
    {
        this.friends.add(user);
    }

    public void setFriends(List<DB_User> friends) {
        this.friends = friends;
    }

    public List<DB_User> getFriendOf() {
        return friendOf;
    }

    public void setFriendOf(List<DB_User> friendOf) {
        this.friendOf = friendOf;
    }


}
