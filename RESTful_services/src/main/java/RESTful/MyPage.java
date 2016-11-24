package RESTful;

import DB.DB_Manager;
import DB.DB_User;
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
    private static final String RESULT_SUCCESS="success";
    private static final String RESULT_FAILURE="fail";

    @POST()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getPersonalInfo(@FormParam("username") String username,
                                    @FormParam("password") String password)
    {
        System.out.println("in getPersonalInfo");
        DB_Manager databaseManager = new DB_Manager();
        DB_User user = databaseManager.getUserByNameAndPassword(username, password);

        if(user != null)
        {
            System.out.println("user is not null");
            StringBuilder result = new StringBuilder();
            result.append("status:" + RESULT_SUCCESS+"\n");
            result.append("id:" + user.getId()+"\n");
            result.append("name:" + user.getName()+"\n");
            result.append("username:" + user.getUsername()+"\n");
            result.append("password:" + user.getPassword());
            result.append("noOfUnreadMessages:" + 10);
            return Response.status(200).entity(result.toString()).build();
        }
        else
        {
            System.out.println("user is null");
            String fail = "status : " + RESULT_FAILURE;
            return Response.status(403).entity(fail).build();
        }
    }
}
