package RESTful;

import DB.DB_Manager;
import DB.DB_User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Teddy on 2016-11-23.
 */
@Path("/Register")
public class Register {
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";


    @PUT()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String registerUser(@FormParam("name") String name,
                                 @FormParam("username") String username,
                                 @FormParam("password") String password) throws IOException
    {
        System.out.println("in regisdterUSET!!");
        int result = new DB_Manager().addUser(name, username, password);
        System.out.println("result = "+result);
        if(result == 1)
        {
            return RESULT_SUCCESS;
        }
        return RESULT_FAILURE;
    }
}
