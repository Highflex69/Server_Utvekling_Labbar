package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import Hibernate.DTO_Log;
import Hibernate.DTO_Post;
import Hibernate.DTO_User;
import Hibernate.DTO_Users;
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

            System.out.println("user is null");
            String fail = "status : " + RESULT_FAILURE;
            return Response.status(403).entity(fail).build();

    }

    @POST()
    @Path("/GetStream")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getStream(@FormParam("username") String username,
                                    @FormParam("password") String password)
    {
        System.out.println("in getStream");
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);

        if(user != null)
        {
            DTO_Log streamLog = databaseManager.getUserStream(user);
            if(streamLog!=null)
            {
                for (DTO_Post p : streamLog.getPostList())
                {
                    System.out.println("post: " + p.getContent());
                }
                return Response.status(200).entity(new Gson().toJson(streamLog)).build();
            }
        }
            System.out.println("user is null");
            String fail = "status : " + RESULT_FAILURE;
            return Response.status(403).entity(fail).build();
    }

    @POST()
    @Path("/GetFriends")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getFriends(@FormParam("username") String username,
                              @FormParam("password") String password)
    {
        System.out.println("in getStream");
        DB_Manager databaseManager = new DB_Manager();
        DTO_User user = databaseManager.getUserByNameAndPassword(username, password);
        if(user != null)
        {
            DTO_Users friends = databaseManager.getFriendsOfUser(user);
            if(friends != null)
            {
                System.out.println("friends size=" + friends.getUserList().size());
                for (String text : friends.getUserList())
                {
                    System.out.println("friend: " + text);
                }
                return Response.status(200).entity(new Gson().toJson(friends)).build();
            }
        }
        System.out.println("user is null");
        return Response.status(403).entity(RESULT_FAILURE).build();
    }
}
