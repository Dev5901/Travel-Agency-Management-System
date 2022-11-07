package com.example.toursandtravelfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void add(View view) {
        Intent in=new Intent(Admin.this,AddTour.class);
        startActivity(in);
    }
    public void bill(View view) {
        Intent in=new Intent(Admin.this,Allbills.class);
        startActivity(in);
    }

    public void delete(View view) {
        Intent in=new Intent(Admin.this,deletetour.class);
        startActivity(in);

    }

    public void edit(View view) {
        Intent in=new Intent(Admin.this,EditTour.class);
        startActivity(in);
    }
}