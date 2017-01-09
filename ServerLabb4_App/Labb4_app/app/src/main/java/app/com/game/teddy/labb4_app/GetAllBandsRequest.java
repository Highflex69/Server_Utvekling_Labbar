package app.com.game.teddy.labb4_app;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teddy on 2017-01-09.
 */

public class GetAllBandsRequest extends StringRequest {
    private static final String AllBands_REQUEST_URL = "http://130.229.145.46:8080/rest/all-bands";
    private UserDTO userDTO;
    private String objectToSendJson;

    public GetAllBandsRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, AllBands_REQUEST_URL, listener, null);
        userDTO = new UserDTO(username, password);
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
