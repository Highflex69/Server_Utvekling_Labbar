package RESTful;

import DB.DB_Manager;
import DB.DB_User;
import org.codehaus.jettison.json.JSONWriter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;

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
    public JSONWriter getPersonalInfo(@FormParam("username") String username,
                                  @FormParam("password") String password)
    {
        DB_Manager databaseManager = new DB_Manager();
        DB_User user = databaseManager.getUserByNameAndPassword(username, password);

        if(user != null)
        {
            String name = user.getName();
            String mail = null;
            String lastTimeLogIn = null;

            JSONWriter jsonWriter = null;
            try{
                jsonWriter = new JSONWriter(new FileWriter("test.json"));
                //start main object:
                jsonWriter.object();    // name : value
                    jsonWriter.key("status"); // name :
                    jsonWriter.value(RESULT_SUCCESS); // : value
                jsonWriter.endObject();
                return jsonWriter;
            }
            catch (Exception e){e.printStackTrace();}
        }
        else
        {
            JSONWriter result = null;
            try{
                result  = new JSONWriter(new FileWriter("result.json"));
                result.object();
                result.key("status");
                result.value(RESULT_FAILURE);
                result.endObject();
                return result;
            }catch (Exception  e){e.printStackTrace();}
        }
        return null;
    }

    /*
    jsonWriter.endObject();
                    //end object:
                    //start object:
                    jsonWriter.object();
                    jsonWriter.key("personal info");
                    //start array:
                    jsonWriter.array();
                        jsonWriter.object(); //start object:
                        jsonWriter.key("name");
                        jsonWriter.value(user.getName());
                        jsonWriter.endObject(); //end object:

                        jsonWriter.object(); //start object:
                        jsonWriter.key("password");
                        jsonWriter.value(user.getPassword());
                        jsonWriter.endObject(); //end object:

                        jsonWriter.object(); //start object:
                        jsonWriter.key("username");
                        jsonWriter.value(user.getUsername());
                        jsonWriter.endObject(); //end object:
                    jsonWriter.endArray();
                    jsonWriter.endObject();
                    //end object:

     */


}
