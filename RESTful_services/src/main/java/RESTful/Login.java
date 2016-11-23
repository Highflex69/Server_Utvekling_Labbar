package RESTful;

import DB.DB_Manager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


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
    public String verifyUserLogin(@FormParam("username") String username,
                               @FormParam("password") String password)
    {
        DB_Manager databaseManager = new DB_Manager();
        boolean result = databaseManager.verifyUser(username, password);
        if(result)
        {
            return RESULT_SUCCESS;
        }
        return RESULT_FAILURE;
    }
}
