package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import Hibernate.DTO_Log;
import Hibernate.DTO_Message;
import Hibernate.DTO_Messages;
import Hibernate.DTO_User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Teddy on 2016-11-24.
 */
@Path("/MessagePage")
public class MessagePage {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @PUT()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/CreateMessage")
    public Response createMessage(@FormParam("username") String username,
                                  @FormParam("password") String password,
                                  @FormParam("gson_message") String msgJsonString)
    {
        //construct DTO_Message from gson.
        Gson gson = new Gson();
        DTO_Message messageToCreate = gson.fromJson(msgJsonString, DTO_Message.class);
        System.out.println(messageToCreate.getTitle()+" "+messageToCreate.getContent()+" "+messageToCreate.getToUsername() +" "+messageToCreate.getFromId()+" "+messageToCreate.isRead());

        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            boolean logList = databaseManager.createMessageByUser(messageToCreate, user);
            if(logList)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }
        return Response.status(403).entity(RESULT_FAILURE).build();

    }


    @POST()
    @Path("/GetMessages")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMessages(@FormParam("username") String username,
                             @FormParam("password") String password)
    {
        System.out.println("username= " +username + " password= " + password);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_Messages msgList = databaseManager.getMessagesByUserId(user.getId());
            if(msgList != null)
            {
                System.out.println("response in GetMessages: " + msgList.getMessagesList().toString());
                Gson gson = new Gson();
                return Response.status(200).entity(gson.toJson(msgList)).build();
            }
        }
        return Response.status(403).entity(null).build();
    }

    @POST()
    @Path("/GetMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMessage(@FormParam("username") String username,
                                   @FormParam("password") String password,
                                   @FormParam("messageId") String id)
    {
        System.out.println("username= " +username + " password= " + password + "messageID = " + id);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_Message msg = databaseManager.getMessageByIdWithUserVerification(Integer.parseInt(id), username, password);
            if(msg!= null)
            {
                System.out.println("get message " + id + "successful");
                return Response.status(200).entity(new Gson().toJson(msg)).build();
            }
        }
        return Response.status(500).entity(RESULT_FAILURE).build();
    }




    @POST()
    @Path("/ReadMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response setMessageRead(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("messageId") String id)
    {
        System.out.println("username= " +username + " password= " + password + "messageID = " + id);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            boolean result = databaseManager.setReadToMessageById(Integer.parseInt(id));
            if(result)
            {
                System.out.println("set message " + id + " to read is successful");
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }
        return Response.status(200).entity(RESULT_FAILURE).build();
    }

}
