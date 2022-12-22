package com.example.cargodeliverycompnay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NavigationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
    }

    public void openTruckList(View view) {
        Intent intent = new Intent(NavigationPage.this, TruckListPage.class);
        startActivity(intent);
    }

    public void navigateToCargoList(View view) {
        Intent intent = new Intent(NavigationPage.this, CargoList.class);
        startActivity(intent);
    }

    public void navigateToManagersPage(View view) {
        Intent intent = new Intent(NavigationPage.this, ManagerList.class);
        startActivity(intent);
    }

    public void navigateToOrdersPage(View view) {
        Intent intent = new Intent(NavigationPage.this, Orders.class);
        startActivity(intent);
    }
}