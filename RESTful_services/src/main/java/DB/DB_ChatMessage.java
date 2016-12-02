package DB;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-28.
 */
@Entity
@Table(name = "T_ChatMessage")
public class DB_ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    private int id;
    @Column(name = "C_DATE")
    private long date;
    @Column(name = "C_USERNAME")
    private String username;
    @Column(name = "C_MESSAGE",columnDefinition = "TEXT")
    private String message;

    public DB_ChatMessage(long date, String username, String message) {
        this.date = date;
        this.username = username;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
