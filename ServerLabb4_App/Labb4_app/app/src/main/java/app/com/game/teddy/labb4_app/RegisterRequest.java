package app.com.game.teddy.labb4_app;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teddy on 2017-01-05.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://130.229.145.46:8080/rest/register";
    private String objectToSendJson;
    private UserDTO userDTO;


    public RegisterRequest(String name, String username, String password, String email, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        userDTO = new UserDTO(username, password, name, email);
        objectToSendJson = new Gson().toJson(userDTO, UserDTO.class);
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return objectToSendJson.getBytes();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }

}
