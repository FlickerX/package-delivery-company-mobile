package com.example.cargodeliverycompnay;

import static com.example.cargodeliverycompnay.Constants.DESTINATION_BY_ID;
import static com.example.cargodeliverycompnay.Constants.UPDATE_DESTINATION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cargodeliverycompnay.model.Destination;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OrderDetailed extends AppCompatActivity {
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detailed);

        int orderId = getOrderId();

        executor.execute(()->{
            try{
                String response = Rest.sendGet(DESTINATION_BY_ID + orderId);
                handler.post(()->{
                    if (!response.equals("")){
                        Destination order = getOrder(response);

                        EditText address = findViewById(R.id.addressField);
                        address.setText(order.getAddress());

                        EditText requestedDeliveryDate = findViewById(R.id.requestedDateField);
                        requestedDeliveryDate.setText(order.getRequestedDeliveryDate().toString());

                        EditText deliveryStartDate = findViewById(R.id.startDateField);
                        deliveryStartDate.setText(order.getDeliveryStartDate().toString());

                        EditText deliveryEndDate = findViewById(R.id.endDateField);
                        if (order.getDeliveryEndDate() == null){
                            deliveryEndDate.setText("In Progress");
                        }else{
                            deliveryEndDate.setText(order.getDeliveryEndDate().toString());
                        }

                        requestedDeliveryDate.setKeyListener(null);
                        deliveryStartDate.setKeyListener(null);
                        deliveryEndDate.setKeyListener(null);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Destination getOrder(String response) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        }
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        Destination order = gson.fromJson(response, Destination.class);
        return order;
    }

    private int getOrderId() {
        Intent currentIntent = getIntent();
        Bundle bundle = currentIntent.getExtras();
        int orderId = Integer.parseInt(bundle.getString("SELECTED_ORDER_ID"));
        return orderId;
    }

    public void updateOrder(View view) throws IOException {
        int orderId = getOrderId();

        EditText addressField = findViewById(R.id.addressField);
        String address = addressField.getText().toString();

        executor.execute(()->{
            String response = null;
            try {
                response = Rest.sendGet(DESTINATION_BY_ID + orderId);
                Destination order = getOrder(response);
                String data = "{\"id\":"
                        + order.getId() + ", \"address\": \""
                        + address + "\", \"requestedDeliveryDate\": \""
                        + order.getRequestedDeliveryDate() + "\", \"deliveryStartDate\": \""
                        + order.getDeliveryStartDate() + "\", \"deliveryEndDate\": \""
                        + order.getDeliveryEndDate() + "\", \"status\": \""
                        + order.getStatus() + "\", \"truck\": "
                        + order.getTruck() + "}";
                String putResponse = Rest.sendPut(UPDATE_DESTINATION, data);
                Intent intent = new Intent(OrderDetailed.this, Orders.class);
                startActivity(intent);
                Toast.makeText(OrderDetailed.this, "Order Updated Successfully: ", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void navigateToMainPage(View view) {
        Intent intent = new Intent(OrderDetailed.this, Orders.class);
        startActivity(intent);
    }
}