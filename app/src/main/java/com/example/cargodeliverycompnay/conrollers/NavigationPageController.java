package com.example.cargodeliverycompnay.conrollers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.cargodeliverycompnay.R;

public class NavigationPageController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
    }

    public void openTruckList(View view) {
        Intent intent = new Intent(NavigationPageController.this, TruckListController.class);
        startActivity(intent);
    }

    public void navigateToCargoList(View view) {
        Intent intent = new Intent(NavigationPageController.this, CargoListController.class);
        startActivity(intent);
    }

    public void navigateToManagersPage(View view) {
        Intent intent = new Intent(NavigationPageController.this, ManagerListController.class);
        startActivity(intent);
    }

    public void navigateToOrdersPage(View view) {
        Intent intent = new Intent(NavigationPageController.this, OrdersController.class);
        startActivity(intent);
    }
}