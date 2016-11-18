import BO.Student;
import org.hibernate.Session;

/**
 * Created by Teddy on 2016-11-17.
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = HibernateUtil.getSessionFactory().openSession();
        try {

            session.beginTransaction();
            Student test = new Student();
            test.setId(1);
            test.setName("test");
            session.save(test);
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }
}