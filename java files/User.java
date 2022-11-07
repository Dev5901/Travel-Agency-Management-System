package com.example.toursandtravelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {
    String phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Intent in=getIntent();
        phoneno=in.getStringExtra("phoneno");
    }

    public void tour(View view) {
        Intent in=new Intent(User.this,Tour.class);
        in.putExtra("phoneno",phoneno);
        startActivity(in);
    }

    public void feedback(View view) {
        Intent intent = new Intent(getApplicationContext(), ShowFeedback.class);
        intent.putExtra("userName", phoneno);
        startActivity(intent);
    }
}