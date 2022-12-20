package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.ALL_CARGOS_URL;
import static com.example.cargodeliverycompnay.Constants.ALL_ORDERS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.cargodeliverycompnay.model.Cargo;
import com.example.cargodeliverycompnay.model.Destination;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class orders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(()->{
            try{
                String response = Rest.sendGet(ALL_ORDERS_URL);
                handler.post(()->{
                    if (!response.equals("")){
                        Gson builder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create();

                        Type orderType = new TypeToken<List<Destination>>(){}.getType();

                        final List<Destination> orderListFromJson = builder.fromJson(response, orderType);

                        ListView orderListView = findViewById(R.id.orderList);

                        ArrayAdapter<Destination> arrayAdapter = new ArrayAdapter<>(orders.this, android.R.layout.simple_list_item_1, orderListFromJson);
                        orderListView.setAdapter(arrayAdapter);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(orders.this, navigation_page.class);
        startActivity(intent);
    }
}