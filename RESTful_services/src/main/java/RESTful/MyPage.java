package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import Hibernate.DTO_User;
import com.google.gson.Gson;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONStringer;
import org.codehaus.jettison.json.JSONWriter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileWriter;

import static jdk.nashorn.internal.objects.NativeDate.toJSON;

/**
 * Created by Teddy on 2016-11-23.
 */
@Path("/MyPage")
public class MyPage {
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getPersonalInfo(@FormParam("username") String username,
                                    @FormParam("password") String password)
    {

        System.out.println("in getPersonalInfo");
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);

        if(user != null)
        {
            Gson gson = new Gson();
            return Response.status(200).entity(gson.toJson(user)).build();
        }
        else
        {
            System.out.println("user is null");
            String fail = "status : " + RESULT_FAILURE;
            return Response.status(403).entity(fail).build();
        }
    }
}
