package DB;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-21.
 */
@Entity
@Table(name = "T_MESSAGE")
public class DB_Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "C_TITLE", length = 50)
    private String title;
    @Column(name = "C_CONTENT", columnDefinition = "TEXT")
    private String content;
    @ManyToOne()
    @JoinColumn(name = "C_RECIEVER_ID")
    private DB_User to;
    @ManyToOne()
    @JoinColumn(name = "C_SENDER_ID")
    private DB_User from;

    private boolean isPrivate;

    public DB_Message() {}
    public DB_Message(String title, String content, DB_User from, DB_User to)
    {
        this.title = title;
        this.content = content;
        this.to = to;
        this.from = from;
        this.isPrivate = false;
    }
    public DB_Message(String title, String content, DB_User from, DB_User to, boolean isPrivate)
    {
        this.title = title;
        this.content = content;
        this.to = to;
        this.from = from;
        this.isPrivate = isPrivate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
