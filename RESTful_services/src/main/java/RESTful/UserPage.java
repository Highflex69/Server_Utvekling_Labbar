package RESTful;

import DB.DB_Manager;
import Hibernate.DTO_Log;
import Hibernate.DTO_User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Teddy on 2016-11-25.
 */
@Path("/UserPage")
public class UserPage {

    @POST()
    @Path("/GetUserLog")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getMyLog(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("searchforusename") String searchUsername)
    {
        System.out.println("username= " +username + " password= " + password + " search for = " + searchUsername);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user !=null)
        {
            DTO_User userFound = databaseManager.getUserDTOByUsername(searchUsername);
            if(userFound != null)
            {
                DTO_Log logList = databaseManager.getLogsById(user.getId());
                if(logList != null)
                {
                    return Response.status(200).entity(new Gson().toJson(logList)).build();
                }
            }
        }
        return Response.status(403).entity(null).build();
    }
}
