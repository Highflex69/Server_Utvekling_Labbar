package DB;

import Hibernate.*;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

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
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            DB_User newUser = new DB_User(name, username, password);
            session.beginTransaction();
            session.save(newUser);
            session.getTransaction().commit();
            result = 1;
        }
        catch (Exception e) {e.printStackTrace();session.getTransaction().rollback();}
        finally {session.close();}
        return result;
    }

    //post
    public boolean addLogByUser(String content, DTO_User author)
    {
        boolean result = false;
        try
        {
            //create a receive DTO_USER and create a new DB_USER to set into DB_Post as author.
            DB_Post newLog = new DB_Post(content, getUserByUsername(author.getUsername()), new Date().getTime());
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            session.beginTransaction();
            session.save(newLog);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
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
        catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            session.close();
        }
        return result;
    }

    public boolean sendChatMessageByUser(DTO_ChatMessage chatMsg, DTO_User user)
    {
        boolean result = false;

        try
        {
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
            }
            DB_ChatMessage newChatMsg = new DB_ChatMessage(chatMsg.getDate(), chatMsg.getUser(), chatMsg.getMessage());
            session.beginTransaction();
            session.save(newChatMsg);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            session.close();
        }
        return result;
    }





    public boolean removeFriendWithIdFromUser(String friendUsername, String username)
    {
        boolean result = false;
        try
        {
            DB_User currentUser = getUserByUsername(username);
            DB_User friend = getUserByUsername(friendUsername);
            initConnections();

            int size = currentUser.getFriends().size();
            for(int i=0;i<size;i++)
            {
                if(currentUser.getFriends().get(i).getId() == friend.getId())
                {
                    currentUser.getFriends().remove(i);
                }
            }
            int friendSize = friend.getFriends().size();
            for (int j=0;j<friendSize;j++)
            {
                if(friend.getFriends().get(j).getId() == currentUser.getId())
                {
                    friend.getFriends().remove(j);
                }
            }

            session.saveOrUpdate(currentUser);
            session.saveOrUpdate(friend);
            session.getTransaction().commit();
            result = true;
        }
        catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return result;
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

        }catch (Exception e) {e.printStackTrace(); session.getTransaction().rollback();}
        finally
        {
            closeConnections();
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
           initConnections();
            System.out.println("searchUsername = " + username);
            user =  (DB_User) em.createQuery(
                    "from DB_User user where user.username = :searchUsername")
                    .setParameter("searchUsername", username)
                    .getSingleResult();
        }catch (Exception e) {e.printStackTrace();session.getTransaction().rollback();}
        finally
        {
            closeConnections();
        }
        return user;
    }

    public DTO_Log getLogsByUserId(int id)
    {

        DTO_Log logs = new DTO_Log();
        try{
            List<DB_Post> logList;
            initConnections();

            logList = (List<DB_Post>) em.createQuery(
                    "from DB_Post post where post.authorId.id = :searchId")
                    .setParameter("searchId", id)
                    .getResultList();

            for (DB_Post log : logList)
            {
                logs.addPost(new DTO_Post(log.getId(), log.getContent(), log.getAuthorId().getUsername()));
            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return logs;
    }



    public DTO_Messages getMessagesByUserId(int id) {

        DTO_Messages messages = new DTO_Messages();
        try{
            List<DB_Message> msgList;
            initConnections();

            msgList = (List<DB_Message>) em.createQuery(
                    "from DB_Message msg where msg.to.id = :searchId")
                    .setParameter("searchId", id)
                    .getResultList();

            for (DB_Message msg : msgList)
            {
                messages.add(new DTO_Message(msg.getId(), msg.getTitle(), msg.getContent(), msg.getTo().getUsername(), msg.getFrom().getUsername(), msg.isRead()));
            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return messages;
    }

    private DB_Message getMessageById(int id) {

        DB_Message message = null;
        try{
            initConnections();

            message = (DB_Message) em.createQuery(
                    "from DB_Message msg where msg.id = :searchId")
                    .setParameter("searchId", id)
                    .getSingleResult();
            return message;
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
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
            DB_Message message = getMessageById(readToMessageById);
            initConnections();

            message.setRead();
            session.saveOrUpdate(message);
            result = true;
        }
        catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return result;
    }

    public DTO_Users getAllUsernames() {
        DTO_Users allUsers = null;
        try
        {
            initConnections();

            List<DB_User> userList = (List<DB_User>) em.createQuery(
                    "from DB_User").getResultList();
            System.out.println("all user size:" + userList.size());
            if(userList != null)
            {
                allUsers = new DTO_Users();
                for(DB_User user : userList)
                {
                    allUsers.addUser(user.getUsername());
                }
            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return allUsers;
    }

    public int addFriendToUser(String friendUsername, String username) {
        //results: -1 error, 1 added, 2 already friend;
        DB_User user = getUserByUsername(username);
        DB_User friend = getUserByUsername(friendUsername);

        boolean alreadyFriend = false;
        int result = -1;

        if(user != null && friend != null)
        {
            if(!session.isOpen())
            {
                session = HibernateUtil.getSessionFactory().openSession();
                emf = Persistence.createEntityManagerFactory("TestPU");
                em = emf.createEntityManager();
            }
            List<DB_User> friends = user.getFriends();
            if(friends != null)
            {
                for(DB_User friendFound : friends)
                {
                    if(friendFound.getId() == friend.getId())
                    {
                        alreadyFriend = true;
                        result = 2;
                    }

                }
            }
        }

        if(!alreadyFriend)
        {
            try{
                initConnections();
               // session.beginTransaction();

                user.addFriend(friend);
                friend.addFriend(user);
                session.saveOrUpdate(user);
                session.saveOrUpdate(friend);
                session.getTransaction().commit();
                result = 1;
            }catch (Exception e){result = -1; e.printStackTrace(); session.getTransaction().rollback();}
            finally {
                closeConnections();
            }
        }

        return result;
    }

    public DTO_Message getMessageByIdWithUserVerification(int id, String username, String password) {

        DTO_Message result = null;

        try{
            initConnections();

            DB_Message message = (DB_Message) em.createQuery(
                    "from DB_Message msg where msg.id = :searchId")
                    .setParameter("searchId", id)
                    .getSingleResult();
            System.out.println("messageId = " + id);
            System.out.println("message-TO = " + message.getTo().getUsername() + " username = " + username);
            System.out.println("message-TO-password = " + message.getTo().getPassword() + " password = " + password);
            if(message.getTo().getUsername().equalsIgnoreCase(username) && message.getTo().getPassword().equalsIgnoreCase(password))
            {
                System.out.println("real user");
                message.setRead();
                session.saveOrUpdate(message);
                session.getTransaction().commit();
                System.out.println(message.getContent());
                result = new DTO_Message(message.getId(), message.getTitle(), message.getContent(), message.getTo().getUsername(), message.getFrom().getUsername(), message.isRead());

            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return result;
    }

    public DTO_Log getUserStream(DTO_User user) {
        DTO_Log stream = null;

        try
        {
            DB_User currentUser = getUserByUsername(user.getUsername());
           initConnections();

            List<DB_User> friendList = currentUser.getFriends();
            if(friendList!=null)
            {
                stream = new DTO_Log();
                ArrayList<DTO_Log> friensLog = new ArrayList<DTO_Log>();
                for(DB_User friend : friendList)
                {
                    for(DTO_Post post : getLogsByUserId(friend.getId()).getPostList())
                    {
                        stream.addPost(post);
                    }
                }
                if(stream != null)
                {
                    stream = sortLogByTimePosted(stream);
                }
            }

        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return stream;
    }

    private DTO_Log sortLogByTimePosted(DTO_Log listToSort)
    {
        Collections.sort(listToSort.getPostList());
        return  listToSort;
    }


    public DTO_Users getFriendsOfUser(DTO_User user) {

        DTO_Users friends = null;
        try
        {
            initConnections();

            DB_User userFromDB = getUserByUsername(user.getUsername());
            if(userFromDB.getFriends() != null)
            {
                friends = new DTO_Users();
                for (DB_User friend : userFromDB.getFriends())
                {
                    friends.addUser(friend.getUsername());
                }
            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return friends;
    }

    public DTO_ChatMessage getChatMessageAndRemoveIt(long lastUpdate) {
        DTO_ChatMessage chatMessage = null;
        try
        {
            initConnections();
            List<DB_ChatMessage> chatMsgList = (List<DB_ChatMessage>) em.createQuery(
                    "from DB_ChatMessage msg").getResultList();

            if(chatMsgList !=null)
            {
                for(DB_ChatMessage db_ChatMsg : chatMsgList)
                {
                    if(lastUpdate <= db_ChatMsg.getDate())
                    {
                        chatMessage = new DTO_ChatMessage(db_ChatMsg.getDate(), db_ChatMsg.getUsername(), db_ChatMsg.getMessage());
                        break;
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();session.getTransaction().rollback();}
        finally {
            closeConnections();
        }
        return chatMessage;
    }







    private void initConnections()
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
    }

    private void closeConnections()
    {
        if(session.isOpen()){session.close();}
        if(em.isOpen()){em.close();}
        if(emf.isOpen()){emf.close();}
    }


}
