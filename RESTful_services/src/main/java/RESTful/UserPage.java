package RESTful;

import DB.DB_Manager;
import Hibernate.DTO_Log;
import Hibernate.DTO_User;
import Hibernate.DTO_Users;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Teddy on 2016-11-25.
 */
@Path("/UserPage")
public class UserPage {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Path("/GetUserLog")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMyLog(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("searchforusername") String searchUsername)
    {
        System.out.println("username= " +username + " password= " + password + " search for = " + searchUsername);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_User userFound = databaseManager.getUserDTOByUsername(searchUsername);
            if(userFound != null)
            {
                DTO_Log logList = databaseManager.getLogsByUserId(userFound.getId());
                if(logList != null)
                {
                    return Response.status(200).entity(new Gson().toJson(logList)).build();
                }
            }
        }
        return Response.status(403).entity(null).build();
    }

    @POST()
    @Path("/GetAllUsers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getAllUsers(@FormParam("username") String username,
                             @FormParam("password") String password)
    {
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_Users usersFound = databaseManager.getAllUsernames();
            if(usersFound != null)
            {
                return Response.status(200).entity(new Gson().toJson(usersFound)).build();
            }
        }
        return Response.status(403).entity(null).build();
    }

    @POST()
    @Path("/AddFriend")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addFriend(@FormParam("username") String username,
                                @FormParam("password") String password,
                                @FormParam("friendusername") String friendUsername)
    {
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user != null)
        {
            int result = databaseManager.addFriendToUser(friendUsername, user.getUsername());
            if(result == 1)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
            else if(result == 2)
            {
                return Response.status(200).entity("already_friend").build();
            }
        }
        return Response.status(500).entity(RESULT_FAILURE).build();
    }

    @POST()
    @Path("/RemoveFriend")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response removeFriend(@FormParam("username") String username,
                                @FormParam("password") String password,
                                @FormParam("friendusername") String friendUsername)
    {
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user != null)
        {
            boolean result = databaseManager.removeFriendWithIdFromUser(friendUsername, user.getUsername());
            if(result)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }
        return Response.status(500).entity(RESULT_FAILURE).build();
    }





}
