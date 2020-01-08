package barkote.emo.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import barkote.emo.login.Network.Instance;
import barkote.emo.login.Network.NetworkCalls;
import barkote.emo.login.Network.models.Data;
import barkote.emo.login.Network.models.Results;
import barkote.emo.login.Network.models.Signin;
import barkote.emo.login.Network.models.User;
import barkote.emo.login.utils.AnimationUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public Button login;
    public View v;
    public EditText username, password;
    public TextView forgot, logo;

    public String puname;
    public String ppass;

    public Call<Results> resultsCall;
    public static NetworkCalls networkCalls;

    public Results results;
    public ProgressDialog progressBar;

    public Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        networkCalls = Instance.getApiClient();
        progressBar = new ProgressDialog(this);

        helper = new Helper(getApplicationContext());
        setup();


    }

    private void setup() {


        login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forgot = findViewById(R.id.forgot_password);
        logo = findViewById(R.id.logo);

        AnimationUtils.makeRoundCorner(login, Color.parseColor("#0696DC"), 15, 500);
        AnimationUtils.showMe(login, 600);

        AnimationUtils.enterTop(logo, 300);
        AnimationUtils.showMe(username, 500);
        AnimationUtils.showMe(password, 500);
        AnimationUtils.enterLeft(forgot, 500);

    }


    public void login(View view) {

        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Loading data from server ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.show();

        puname = username.getText().toString().trim();
        ppass = password.getText().toString().trim();


        Signin signin = new Signin(puname, ppass);


        resultsCall = MainActivity.networkCalls.login(signin);


        //noinspection NullableProblems
        resultsCall.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {

                if(response.isSuccessful()){
                    progressBar.cancel();
                    results = response.body();
                    assert results != null;


                    Data data = results.getData();


                    helper.savetoken(data.getToken());
                    startActivity(new Intent(MainActivity.this,Home.class));
                }

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

            }
        });

    }
}
