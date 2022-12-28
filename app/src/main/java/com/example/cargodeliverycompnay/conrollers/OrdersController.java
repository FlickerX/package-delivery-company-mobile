package com.example.cargodeliverycompnay.conrollers;

import static com.example.cargodeliverycompnay.api.Constants.ALL_ORDERS_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cargodeliverycompnay.deserializers.LocalDateDeserializer;
import com.example.cargodeliverycompnay.R;
import com.example.cargodeliverycompnay.rest.Rest;
import com.example.cargodeliverycompnay.model.Destination;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OrdersController extends AppCompatActivity {

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
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
                        }
                        Gson gson = gsonBuilder.setPrettyPrinting().create();

                        Type orderType = new TypeToken<List<Destination>>(){}.getType();

                        final List<Destination> orderListFromJson = gson.fromJson(response, orderType);

                        ListView orderListView = findViewById(R.id.orderList);

                        ArrayAdapter<Destination> arrayAdapter = new ArrayAdapter<>(OrdersController.this, android.R.layout.simple_list_item_1, orderListFromJson);
                        orderListView.setAdapter(arrayAdapter);

                        orderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(OrdersController.this, "Selected Truck: " + orderListFromJson.get(i).getId(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(OrdersController.this, OrderDetailedController.class);
                                String id = String.valueOf(orderListFromJson.get(i).getId());
                                intent.putExtra("SELECTED_ORDER_ID", id);
                                startActivity(intent);
                            }
                        });
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(OrdersController.this, NavigationPageController.class);
        startActivity(intent);
    }
}