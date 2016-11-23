import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Teddy on 2016-11-18.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    static{
        try{
            //Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            //Make sure you log the exception, as it might be swallowed
            System.err.print("Initial SeddionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
