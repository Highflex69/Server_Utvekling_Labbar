package DB;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-24.
 */
@Entity
@Table(name = "T_POST")
public class DB_Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    private int id;

    @Column(name = "C_LOG_CONTENT", columnDefinition = "TEXT")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "C_LOG_AUTHORID")
    private DB_User authorId;

    public DB_Post(String content, DB_User author)
    {
        this.content = content;
        this.authorId = author;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public DB_User getAuthorId() {
        return authorId;
    }
}
