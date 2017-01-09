package app.com.game.teddy.labb4_app;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText et_Username = (EditText) findViewById(R.id.et_Username);
        final EditText et_Password = (EditText) findViewById(R.id.et_Password);
        final Button b_Login = (Button) findViewById(R.id.b_Login);
        final TextView tv_Register = (TextView) findViewById(R.id.tv_Register);

        //debug: fake login button
        final Button b_DebugLogin = (Button) findViewById(R.id.debug_login_button);
        b_DebugLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                intent.putExtra("username", "debug");
                LoginActivity.this.startActivity(intent);
            }
        });
        //debug end

        tv_Register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });


        b_Login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String username = et_Username.getText().toString();
                final String password = et_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        /**
                         * gson -> class HERE!
                         * response = boolean(loginSucess)
                         * **/
                        Log.v("Login response:", response);
                        UserDTO userLogin = gson.fromJson(response, UserDTO.class);

                        if(userLogin.getStatus() == 200)
                        {
                            Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_SHORT).show();// test
                            Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                            intent.putExtra("username", username);
                            LoginActivity.this.startActivity(intent);
                        }
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Login Failed")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }
                };

                //the post resquest with carlosTest test123
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                //the queue of which the RESTful-request is done.
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
