package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import Hibernate.DTO_Log;
import Hibernate.DTO_Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

/**
 * Created by Teddy on 2016-11-24.
 */
@Path("/MyLogPage")
public class MyLogPage {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMyLogs(@FormParam("username") String username,
                                    @FormParam("password") String password,
                              @FormParam("id") int id)
    {
        DB_Manager databaseManager = new DB_Manager();
        boolean result = databaseManager.verifyUser(username, password);
        if(result)
        {
            DTO_Log logList = databaseManager.getLogsById(id);
            if(logList != null)
            {
                return Response.status(200).entity(logList).build();
            }
        }
        return Response.status(403).entity(null).build();
    }

    @PUT()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createLogByUser(@FormParam("username") String username,
                                        @FormParam("password") String password,
                                        @FormParam("id") int id,
                                        @FormParam("content") String content)
    {
        DB_Manager databaseManager = new DB_Manager();
        DB_User verifyUser = databaseManager.getUserByNameAndPassword(username, password);
        if(verifyUser != null)
        {
            boolean result = databaseManager.addLogByUser(content, verifyUser);
            if(result)
            {
                return Response.status(200).entity(RESULT_SUCCESS).build();
            }
        }

        return Response.status(200).entity(RESULT_FAILURE).build();
    }
}
