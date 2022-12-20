package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.ALL_CARGOS_URL;
import static com.example.cargodeliverycompnay.Constants.ALL_COURIERS_URL;
import static com.example.cargodeliverycompnay.Constants.ALL_MANGERS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cargodeliverycompnay.model.Cargo;
import com.example.cargodeliverycompnay.model.Courier;
import com.example.cargodeliverycompnay.model.Manager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class manager_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try{
//                String response = Rest.sendGet(ALL_MANGERS_URL);
                String response = Rest.sendGet(ALL_MANGERS_URL);
                handler.post(()->{
                    if (!response.equals("")){
                        Gson builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create();

                        Type managerType = new TypeToken<List<Manager>>(){}.getType();
                        Log.d("nikita", response);
                        final List<Manager> managerListFromJson = builder.fromJson(response, managerType);
                        ListView managerListView = findViewById(R.id.managerList);
                        ArrayAdapter<Manager> arrayAdapter = new ArrayAdapter<>(manager_list.this, android.R.layout.simple_list_item_1, managerListFromJson);
                        managerListView.setAdapter(arrayAdapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(manager_list.this, navigation_page.class);
        startActivity(intent);
    }
}