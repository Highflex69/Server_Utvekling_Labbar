package app.com.game.teddy.labb4_app;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserAreaActivity extends AppCompatActivity {
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout mDrawerLayout;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView tv_WelcomeMsg = (TextView) findViewById(R.id.tv_WelcomeMsg);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.activity_user_area);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        tv_WelcomeMsg.setText("Welcome "+ username+"!");
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item))
        {
            Response.Listener<String> responseListener = new Response.Listener<String>(){

                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    /**
                     * gson -> class HERE!
                     * response = boolean(loginSucess)
                     * **/
                    Log.v("Login response:", response);
                    BandDTO[] allBands = gson.fromJson(response, BandDTO[].class);
                    Log.v("arraylist response:", allBands[1].getName());
                }
            };

            //the post resquest with carlosTest test123
            GetAllBandsRequest getAllBandsRequest = new GetAllBandsRequest(username, "test123", responseListener);
            //the queue of which the RESTful-request is done.
            RequestQueue queue = Volley.newRequestQueue(UserAreaActivity.this);
            queue.add(getAllBandsRequest);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
