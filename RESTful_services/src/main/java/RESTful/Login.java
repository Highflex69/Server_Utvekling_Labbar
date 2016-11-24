package RESTful;

import DB.DB_Manager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * Created by Teddy on 2016-11-23.
 */
@Path("/Login")
public class Login {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response verifyUserLogin(@FormParam("username") String username,
                                    @FormParam("password") String password)
    {
        DB_Manager databaseManager = new DB_Manager();
        boolean result = databaseManager.verifyUser(username, password);
        if(result)
        {
            return Response.status(201).entity(RESULT_SUCCESS).build();
        }
        return Response.status(403).entity(RESULT_FAILURE).build();
    }
}
