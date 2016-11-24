package DB;

import Hibernate.DTO_Log;
import Hibernate.DTO_Post;
import Hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Teddy on 2016-11-23.
 */
public class DB_Manager {
    private Session session;
    private EntityManagerFactory emf;
    private EntityManager em;

    public DB_Manager()
    {
        session = HibernateUtil.getSessionFactory().openSession();
        emf = Persistence.createEntityManagerFactory("TestPU");
        em = emf.createEntityManager();
    }

    public int addUser(DB_User newUser)
    {
        System.out.println("Adding a new user");
        int result = -1;
        try
        {
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
            result = 1;
        }
        catch (Exception e) {e.printStackTrace();}
        finally {session.close();}
        return result;
    }

    public boolean addLogByUser(String content, DB_User author)
    {
        boolean result = false;
        try
        {
            //create a receive DTO_USER and create a new DB_USER to set into DB_Post as author.
            DB_Post newLog = new DB_Post(content, author);
            session.beginTransaction();
            session.save(newLog);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            session.close();
        }
        return result;
    }



    public boolean verifyUser(String username, String password)
    {
        System.out.println("verifying user");
        DB_User user = getUserByNameAndPassword(username, password);

        if(user != null)
        {
            return true;
        }
        return false;
    }

    public DB_User getUserByNameAndPassword(String username, String password)
    {
        System.out.println("getting user by username and password");
        DB_User user = null;
        try{
            session.beginTransaction();
            user =  (DB_User) em.createQuery(
                    "from DB_User user where user.username = :searchUsername " +
                            "and user.password = :searchPassword")
                    .setParameter("searchUsername", username).setParameter("searchPassword", password)
                    .getSingleResult();
        }catch (Exception e) {e.printStackTrace();}
        finally
        {
            session.close();
            em.close();
            emf.close();
        }

        return user;
    }

    private DB_User getUserByUsername(String username)
    {
        System.out.println("get user by username");
        DB_User user = null;
        try{
            session.beginTransaction();
            user =  (DB_User) em.createQuery(
                    "from DB_User user where user.username = :searchUsername")
                    .setParameter("searchUsername", username)
                    .getSingleResult();
        }catch (Exception e) {e.printStackTrace();}
        finally
        {
            session.close();
            em.close();
            emf.close();
        }
        return user;
    }

    public DTO_Log getLogsById(int id)
    {

        DTO_Log logs = new DTO_Log();
        try{
            List<DB_Post> logList = null;
            session.beginTransaction();
            logList = (List<DB_Post>) em.createQuery(
                    "from DB_Post post where post.authorId = :searchId")
                    .setParameter("searchId", id)
                    .getResultList();
            for (DB_Post log : logList)
            {
                logs.addPost(new DTO_Post(log.getId(), log.getContent(), log.getAuthorId()));
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            session.close();
            em.close();
            emf.close();
        }
        return logs;
    }

}
