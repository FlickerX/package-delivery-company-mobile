package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.COURIER_LOGIN_URL;
import static com.example.cargodeliverycompnay.Constants.MANAGER_LOGIN_URL;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void validateUser(View view) {
        EditText username = findViewById(R.id.usernameField);
        EditText password = findViewById(R.id.passwordField);
        CheckBox isManager = findViewById(R.id.isManagerCheckbox);

//        String data = "{\"login\":\"" + username.getText().toString() + "\", \"psw\": \"" + password.getText().toString() + "\", \"type\":\"" + isManager.isChecked() + "\"}";

        String data = "{\"login\":\"" + username.getText().toString() + "\", \"password\": \"" + password.getText().toString() + "\"}";
        Executor executor = Executors.newSingleThreadExecutor(); // Gijos
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try{
                String response = "";
                if (isManager.isChecked()){
                    response = Rest.sendPost(MANAGER_LOGIN_URL, data);
                }else{
                    response = Rest.sendPost(COURIER_LOGIN_URL, data);
                }
                String finalResponse = response;
                handler.post(()->{
                    if (!finalResponse.equals("")){
                        Intent intent = new Intent(MainActivity.this, navigation_page.class);
                        intent.putExtra("User", finalResponse);
                        startActivity(intent);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}