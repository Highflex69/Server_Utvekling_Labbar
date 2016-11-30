package RESTful;

import DB.DB_Manager;
import Hibernate.DTO_ChatMessage;
import Hibernate.DTO_Message;
import Hibernate.DTO_Messages;
import Hibernate.DTO_User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Teddy on 2016-11-28.
 */
@Path("/Chat")
public class ChatPage {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";


    @POST()
    @Path("/SendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response sendChatMessage(@FormParam("username") String username,
                                @FormParam("password") String password,
                                @FormParam("message") String jsonStringDTO_ChatMessage)
    {
        Gson gson = new Gson();
        DTO_ChatMessage chatMessage = gson.fromJson(jsonStringDTO_ChatMessage, DTO_ChatMessage.class);
        System.out.println(chatMessage.getDate()+" "+chatMessage.getMessage()+" "+chatMessage.getUser());

        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            boolean logList = databaseManager.sendChatMessageByUser(chatMessage, user);
            if(logList)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }
        return Response.status(403).entity(RESULT_FAILURE).build();
    }


    @POST()
    @Path("/GetMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getChatMessage(@FormParam("username") String username,
                                @FormParam("password") String password,
                                    @FormParam("lastupdate") String lastUpdate)
    {
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_ChatMessage chatMessage = databaseManager.getChatMessageAndRemoveIt(Long.parseLong(lastUpdate));
            if(chatMessage != null)
            {
                return Response.status(200).entity(new Gson().toJson(chatMessage)).build();
            }
        }
        return Response.status(403).entity(null).build();
    }


}
