import DB.DB_Message;
import DB.DB_User;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Teddy on 2016-11-17.
 */
public class Main {

    public static void main(final String[] args) throws Exception {

        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            /*session.beginTransaction();
            EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("TestPU");
            EntityManager em = emf.createEntityManager();

            //search with username
            String username = "username";
            DB_User user =  (DB_User) em.createQuery(
                    "from DB_User user where user."+username+" = :searchString")
                    .setParameter("searchString", "test")
                    .getSingleResult();
            System.out.println("Password: " + user.getPassword());

            em.close();
            emf.close();*/

            /*DB_Message message = new DB_Message("title", "blablala", user, user2);
            session.save(message);
            session.getTransaction().commit();*/
        } finally {
            session.close();
        }
    }
}