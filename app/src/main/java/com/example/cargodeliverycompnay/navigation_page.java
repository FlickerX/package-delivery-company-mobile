package com.example.cargodeliverycompnay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class navigation_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
    }

    public void openTruckList(View view) {
        Intent intent = new Intent(navigation_page.this, TruckListPage.class);
        startActivity(intent);
    }

    public void navigateToCargoList(View view) {
        Intent intent = new Intent(navigation_page.this, cargo_list.class);
        startActivity(intent);
    }

    public void navigateToManagersPage(View view) {
        Intent intent = new Intent(navigation_page.this, manager_list.class);
        startActivity(intent);
    }

    public void navigateToOrdersPage(View view) {
        Intent intent = new Intent(navigation_page.this, orders.class);
        startActivity(intent);
    }
}