package com.example.cargodeliverycompnay.conrollers;

import static com.example.cargodeliverycompnay.api.Constants.DELETE_TRUCK_BY_ID;
import static com.example.cargodeliverycompnay.api.Constants.TRUCK_BY_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cargodeliverycompnay.R;
import com.example.cargodeliverycompnay.rest.Rest;
import com.example.cargodeliverycompnay.model.Truck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailedTruckController extends AppCompatActivity {
    Executor executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_truck);

        int truckId = getTruckId();

        executor.execute(()->{
            try{
                String response = Rest.sendGet(TRUCK_BY_ID + truckId);
                handler.post(()->{
                    if (!response.equals("")){
                        Gson builder = new GsonBuilder().create();

                        Truck truck = builder.fromJson(response, Truck.class);

                        EditText mark = findViewById(R.id.markField);
                        mark.setText(truck.getMark());

                        EditText model = findViewById(R.id.modelField);
                        model.setText(truck.getModel());

                        EditText engineLiters = findViewById(R.id.engineLitersField);
                        engineLiters.setText(truck.getEngineLiters().toString());

                        EditText horsePower = findViewById(R.id.horsePowerField);
                        horsePower.setText(truck.getHorsePower().toString());

                        EditText color = findViewById(R.id.colorField);
                        color.setText(truck.getColor());

                        makeFieldsNoEditable(mark, model, engineLiters, horsePower, color);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void makeFieldsNoEditable(EditText mark, EditText model, EditText engineLiters, EditText horsePower, EditText color) {
        mark.setKeyListener(null);
        model.setKeyListener(null);
        engineLiters.setKeyListener(null);
        horsePower.setKeyListener(null);
        color.setKeyListener(null);
    }

    private int getTruckId() {
        Intent currentIntent = getIntent();
        Bundle bundle = currentIntent.getExtras();
        int orderId = Integer.parseInt(bundle.getString("SELECTED_TRUCK_ID"));
        return orderId;
    }

    public void deleteTruck(View view) {
        int truckId = getTruckId();

        executor.execute(()->{
            String response = null;
            try {
                response = Rest.sendDelete(DELETE_TRUCK_BY_ID + truckId);;
                Intent intent = new Intent(DetailedTruckController.this, TruckListController.class);
                startActivity(intent);
                Toast.makeText(DetailedTruckController.this, "Truck delete Successfully", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}