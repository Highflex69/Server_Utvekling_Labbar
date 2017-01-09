package app.com.game.teddy.labb4_app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText et_Name = (EditText) findViewById(R.id.et_Name);
        final EditText et_Username = (EditText) findViewById(R.id.et_Username);
        final EditText et_Password = (EditText) findViewById(R.id.et_Password);
        final EditText et_Email = (EditText) findViewById(R.id.et_Email);
        final Button b_Register = (Button) findViewById(R.id.b_Register);

        b_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = et_Name.getText().toString();
                final String username = et_Username.getText().toString();
                final String password = et_Password.getText().toString();
                final String email = et_Email.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        /**
                         * gson -> class HERE!
                         * response = kanske boolean?
                         * **/
                        UserDTO userLogin = gson.fromJson(response, UserDTO.class);
                        if(userLogin.getStatus() == 200)
                        {
                            Intent intent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                            intent.putExtra("username", username);
                            RegisterActivity.this.startActivity(intent);
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                            builder.setMessage("Register Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, password, email, responseListener);
                //the queue of which the RESTful-request is done.
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
