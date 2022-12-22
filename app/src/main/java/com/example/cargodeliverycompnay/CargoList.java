package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.ALL_CARGOS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cargodeliverycompnay.model.Cargo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CargoList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_list);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try{
                String response = Rest.sendGet(ALL_CARGOS_URL);
                handler.post(()->{
                    if (!response.equals("")){
                        Gson builder = new GsonBuilder().create();

                        Type cargoType = new TypeToken<List<Cargo>>(){}.getType();

                        final List<Cargo> cargoListFromJson = builder.fromJson(response, cargoType);

                        ListView cargoListView = findViewById(R.id.cargoList);

                        ArrayAdapter<Cargo> arrayAdapter = new ArrayAdapter<>(CargoList.this, android.R.layout.simple_list_item_1, cargoListFromJson);
                        cargoListView.setAdapter(arrayAdapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(CargoList.this, NavigationPage.class);
        startActivity(intent);
    }
}