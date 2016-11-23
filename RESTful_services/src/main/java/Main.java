import DB.DB_User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Map;

/**
 * Created by Teddy on 2016-11-23.
 */
public class Main {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            /*EntityManagerFactory emf =
                    Persistence.createEntityManagerFactory("TestPU");
            EntityManager em = emf.createEntityManager();*/

            //search with username
           /* String username = "username";
            DB_User user =  new DB_User("test","test","test123");
            //session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();*/


            /*DB_User user =(DB_User) em.createQuery(
                    "from DB_User user where user."+username+" = :searchString")
                    .setParameter("searchString", "test")
                    .getSingleResult();
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