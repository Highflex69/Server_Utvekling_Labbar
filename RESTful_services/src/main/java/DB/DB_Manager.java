package DB;

import Hibernate.*;
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

    public int addUser(String name, String username, String password)
    {
        System.out.println("Adding a new user");
        int result = -1;
        try
        {
            DB_User newUser = new DB_User(name, username, password);
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
            result = 1;
        }
        catch (Exception e) {e.printStackTrace();}
        finally {session.close();}
        return result;
    }

    public boolean addLogByUser(String content, DTO_User author)
    {
        boolean result = false;
        try
        {
            //create a receive DTO_USER and create a new DB_USER to set into DB_Post as author.
            DB_Post newLog = new DB_Post(content, getUserByUsername(author.getUsername()));
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
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

    public boolean createMessageByUser(DTO_Message msg, DTO_User user)
    {
        boolean result = false;
        try
        {
            //create a receive DTO_USER and create a new DB_USER to set into DB_Post as author.
            //String title, String content, DB_User from, DB_User to
            DB_Message newMessage = new DB_Message(msg.getTitle(), msg.getContent(), getUserByUsername(user.getUsername()) , this.getUserByUsername(msg.getToUsername()));
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.beginTransaction();
            session.save(newMessage);
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
        DTO_User user = getUserByNameAndPassword(username, password);

        if(user != null)
        {
            return true;
        }
        return false;
    }

    public DTO_User getUserByNameAndPassword(String username, String password)
    {
        System.out.println("getting user by username and password" +" "+ username+ " " + password);
        DTO_User userToReturn = null;
        try{

            session.beginTransaction();
            DB_User user =  (DB_User) em.createQuery(
                    "from DB_User user where user.username = :searchUsername " +
                            "and user.password = :searchPassword")
                    .setParameter("searchUsername", username).setParameter("searchPassword", password)
                    .getSingleResult();
            userToReturn = new DTO_User(user.getId(), user.getName(), user.getUsername(), getNoOfUnreadMessagesByUser(user));

        }catch (Exception e) {e.printStackTrace();}
        finally
        {
            if(session.isOpen()){session.close();}
            if(em.isOpen()){em.close();}
            if(emf.isOpen()){emf.close();}
        }

        return userToReturn;
    }

    public DTO_User getUserDTOByUsername(String username)
    {
        DB_User user = getUserByUsername(username);
        DTO_User foundUser = null;
        if(user != null)
        {
            foundUser = new DTO_User(user.getId(), user.getName(), user.getUsername(), getNoOfUnreadMessagesByUser(user));
        }
        return foundUser;
    }

    private DB_User getUserByUsername(String username)
    {
        System.out.println("get user by username");
        DB_User user = null;
        try{
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }

            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }

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
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
                emf = Persistence.createEntityManagerFactory("TestPU");
                em = emf.createEntityManager();
            }
            List<DB_Post> logList;
            session.beginTransaction();
            logList = (List<DB_Post>) em.createQuery(
                    "from DB_Post post where post.authorId.id = :searchId")
                    .setParameter("searchId", id)
                    .getResultList();

            for (DB_Post log : logList)
            {
                logs.addPost(new DTO_Post(log.getId(), log.getContent(), log.getAuthorId().getId()));
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            session.close();
            em.close();
            emf.close();
        }
        return logs;
    }



    public DTO_Messages getMessagesByUserId(int id) {

        DTO_Messages messages = new DTO_Messages();
        try{
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }

            List<DB_Message> msgList;
            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }
            msgList = (List<DB_Message>) em.createQuery(
                    "from DB_Message msg where msg.from.id = :searchId")
                    .setParameter("searchId", id)
                    .getResultList();

            for (DB_Message msg : msgList)
            {
                messages.add(new DTO_Message(msg.getId(), msg.getTitle(), msg.getContent(), msg.getTo().getUsername(), msg.getFrom().getId(), msg.isRead()));
            }
        }catch (Exception e){e.printStackTrace();}
        finally {

            session.close();
            em.close();
            emf.close();
        }
        return messages;
    }

    private DB_Message getMessageById(int id) {

        DB_Message message = null;
        try{
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }

            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }

            message = (DB_Message) em.createQuery(
                    "from DB_Message msg where msg.from.id = :searchId")
                    .setParameter("searchId", id)
                    .getSingleResult();
            return message;
        }catch (Exception e){e.printStackTrace();}
        finally {

            session.close();
            em.close();
            emf.close();
        }
        return message;
    }

    private int getNoOfUnreadMessagesByUser(DB_User user)
    {
        DTO_Messages messageList = getMessagesByUserId(user.getId());
        int amount = 0;

        for (DTO_Message msg : messageList.getMessagesList())
        {
            if(!msg.isRead())
            {
                amount++;
            }
        }

        return amount;
    }

    public boolean setReadToMessageById(int readToMessageById) {

        boolean result = false;
        try
        {
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }
            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }
            DB_Message message = getMessageById(readToMessageById);
            message.setRead();
            session.saveOrUpdate(message);
            result = true;
        }
        catch (Exception e){e.printStackTrace();}
        finally {
            session.close();
            em.close();
            emf.close();
        }
        return result;
    }

    public DTO_Users getAllUsernames() {
        DTO_Users allUsers = null;
        try
        {
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }
            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }
            List<DB_User> userList = (List<DB_User>) em.createQuery(
                    "from DB_User user").getResultList();

            if(userList != null)
            {
                for(DB_User user : userList)
                {
                    allUsers.addUser(user.getUsername());
                }
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            if(session.isOpen()){session.close();}
            if(em.isOpen()){em.close();}
            if(emf.isOpen()){emf.close();}
        }
        return allUsers;
    }

    public int addFriendToUser(String friendUsername, String username) {
        //results: -1 error, 1 added, 2 already friend;
        DB_User user = getUserByUsername(username);
        DB_User friend = getUserByUsername(friendUsername);
        boolean alreadyFriend = false;
        int result = -1;

        if(user != null || friend != null)
        {
            for(DB_User friendFound : user.getFriends())
            {
                if(friendFound.getId() == friend.getId())
                {
                    alreadyFriend = true;
                }
                result = 2;
            }
        }

        if(!alreadyFriend)
        {
            try{
                if(!session.isOpen())
                {
                    session = HibernateUtil.getSessionFactory().openSession();
                    emf = Persistence.createEntityManagerFactory("TestPU");
                    em = emf.createEntityManager();
                }
                session.getTransaction();

                user.addFriend(user);
                session.saveOrUpdate(user);
                session.getTransaction().commit();
                result = 1;
            }catch (Exception e){result = -1; e.printStackTrace();}
            finally {
                session.close();
                em.close();
                emf.close();
            }
        }

        return result;
    }

    public DTO_Message getMessageByIdWithUserVerification(int id, String username, String password) {

        try{
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            if(!emf.isOpen())
            {
                emf = Persistence.createEntityManagerFactory("TestPU");
            }
            if(!em.isOpen())
            {
                em = emf.createEntityManager();
            }
            if(!session.getTransaction().isActive())
            {
                session.beginTransaction();
            }

            DB_Message message = (DB_Message) em.createQuery(
                    "from DB_Message msg where msg.from.id = :searchId")
                    .setParameter("searchId", id)
                    .getSingleResult();
            if(message.getTo().getUsername().equalsIgnoreCase(username) && message.getTo().getPassword().equalsIgnoreCase(password))
            {
                return new DTO_Message(message.getId(), message.getTitle(), message.getContent(), message.getTo().getUsername(), message.getFrom().getId(), message.isRead());
            }
        }catch (Exception e){e.printStackTrace();}
        finally {

            session.close();
            em.close();
            emf.close();
        }
        return null;
    }
}
