package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import Hibernate.DTO_User;
import com.google.gson.Gson;

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
        System.out.println("username= " +username + " password= " + password);
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user != null)
        {
            Gson gson = new Gson();
            String userStr = gson.toJson(user);
            System.out.println(userStr);
            return Response.status(200).entity(userStr).build();
        }
        return Response.status(403).entity(RESULT_FAILURE).build();
    }

    @GET()
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getExample()
    {
        return Response.status(200).entity("Hello World!").build();
    }

    @GET()
    @Path("/getdb")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getExample2()
    {
        DB_Manager dataDb = new DB_Manager();
        DTO_User user = dataDb.getUserByNameAndPassword("carlos", "test123");
        return Response.status(200).entity(user.getName()).build();
    }

}
