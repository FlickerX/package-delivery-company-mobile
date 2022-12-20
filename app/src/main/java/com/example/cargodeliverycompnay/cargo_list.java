package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.ALL_CARGOS_URL;
import static com.example.cargodeliverycompnay.Constants.ALL_TRUCKS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cargodeliverycompnay.model.Cargo;
import com.example.cargodeliverycompnay.model.Truck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class cargo_list extends AppCompatActivity {

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

                        ArrayAdapter<Cargo> arrayAdapter = new ArrayAdapter<>(cargo_list.this, android.R.layout.simple_list_item_1, cargoListFromJson);
                        cargoListView.setAdapter(arrayAdapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(cargo_list.this, navigation_page.class);
        startActivity(intent);
    }
}