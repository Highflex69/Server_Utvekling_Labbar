package DB;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by Teddy on 2016-11-21.
 */
@NamedQueries({
        @NamedQuery(name = "DB_User.findAll", query =  "SELECT u FROM DB_User u"),
        @NamedQuery(name = "DB_User.findById", query = "SELECT u FROM DB_User u WHERE u.id = :id"),
        @NamedQuery(name = "DB_User.findByName", query = "SELECT u FROM DB_User u WHERE u.name = :name")})
@Entity
@Table(name = "T_USER")
public class DB_User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID")
    private int id;
    @NotNull
    @Column( name = "C_NAME")
    private String name;
    @NotNull
    @Column(name = "C_USERNAME", unique = true)
    private String username;
    @NotNull
    @Column( name = "C_PASSWORD")
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



    public static DB_User findByName(String name)
    {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("TestPU");
        EntityManager em = emf.createEntityManager();

        //search with username

        DB_User user =  (DB_User) em.createQuery(
                "from DB_User user where user.name = :searchString")
                .setParameter("searchString", name)
                .getSingleResult();
        em.close();
        emf.close();
        return user;
    }
}
