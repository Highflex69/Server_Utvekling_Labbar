package RESTful;

import DB.DB_Manager;
import Hibernate.DTO_Log;
import Hibernate.DTO_User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Teddy on 2016-11-24.
 */
@Path("/MyLogPage")
public class MyLogPage {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Path("/GetLog")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMyLog(@FormParam("username") String username,
                                    @FormParam("password") String password)
    {
        System.out.println("username= " +username + " password= " + password);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_Log logList = databaseManager.getLogsByUserId(user.getId());
            if(logList != null)
            {
                return Response.status(200).entity(new Gson().toJson(logList)).build();
            }
        }
        return Response.status(403).entity(null).build();
    }

    @PUT()
    @Path("/CreateLog")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createLogByUser(@FormParam("username") String username,
                                        @FormParam("password") String password,
                                        @FormParam("content") String content)
    {
        System.out.println("username= " +username + " password= " + password + " content: " + content);
        DB_Manager databaseManager = new DB_Manager();
        System.out.println();
        DTO_User verifyUser = databaseManager.getUserByNameAndPassword(username, password);
        if(verifyUser != null)
        {
            boolean result = databaseManager.addLogByUser(content, verifyUser);
            if(result)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }

        return Response.status(403).entity(RESULT_FAILURE).build();
    }
}
