package com.example.cargodeliverycompnay.conrollers;

import static com.example.cargodeliverycompnay.api.Constants.ALL_MANGERS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cargodeliverycompnay.deserializers.LocalDateDeserializer;
import com.example.cargodeliverycompnay.R;
import com.example.cargodeliverycompnay.rest.Rest;
import com.example.cargodeliverycompnay.model.Manager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ManagerListController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try{
                String response = Rest.sendGet(ALL_MANGERS_URL);
                handler.post(()->{
                    if (!response.equals("")){

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
                        }
                        Gson gson = gsonBuilder.setPrettyPrinting().create();

                        Type managerType = new TypeToken<List<Manager>>(){}.getType();

                        final List<Manager> managerListFromJson = gson.fromJson(response, managerType);
                        ListView managerListView = findViewById(R.id.managerList);
                        ArrayAdapter<Manager> arrayAdapter = new ArrayAdapter<>(ManagerListController.this, android.R.layout.simple_list_item_1, managerListFromJson);
                        managerListView.setAdapter(arrayAdapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(ManagerListController.this, NavigationPageController.class);
        startActivity(intent);
    }
}