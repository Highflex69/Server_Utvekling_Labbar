package DB;

import DB.DB_User;
import Hibernate.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

    public boolean verifyUser(String username, String password)
    {
        DB_User user = getUserByNameAndPassword(username, password);

        if(user != null)
        {
            return true;
        }
        return false;
    }

    public DB_User getUserByNameAndPassword(String username, String password)
    {
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

}
