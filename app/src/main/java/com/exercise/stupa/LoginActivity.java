package com.exercise.stupa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.exercise.stupa.object.retrofit.LoginRequest;
import com.exercise.stupa.object.retrofit.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    EditText usernameView, passwordView;
    Button loginButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameView = (EditText)findViewById(R.id.usernameView);
        passwordView = (EditText)findViewById(R.id.passwordView);
        loginButton = (Button)findViewById(R.id.loginButton);

        sharedPreferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postLogin();
            }
        });
    }

    public void postLogin(){
        final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
        pd.setTitle("Login");
        pd.setMessage("Loading...");
        pd.show();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(usernameView.getText().toString());
        loginRequest.setPassword(passwordView.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final StupaApi service = retrofit.create(StupaApi.class);

        final Call<LoginResponse> loginResponseCall = service.postLogin(loginRequest);

        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginData = response.body();

                if(loginData.isSuccess()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("login", true);
                    editor.putString("name", loginData.getData().getName());
                    editor.putString("api_token", loginData.getApi_token());

                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    pd.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("DEUBGS", t.toString());
                pd.dismiss();
            }
        });
    }
}
