package com.example.toursandtravelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class finaledit extends AppCompatActivity {
    TextView location;
    EditText cost,time,itinery,image;
    String place,costt,timeperiod,itineary,img;
    DatabaseReference reference;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaledit);
        Intent in=getIntent();
        location=findViewById(R.id.reg_name);
        cost = findViewById(R.id.reg_username);
        time = findViewById(R.id.reg_email);
        image=findViewById(R.id.image);
        itinery = findViewById(R.id.reg_phoneNo);
        location.setText(in.getStringExtra("location"));
        place=in.getStringExtra("location");
    }

    public void edit(View view) {
        if(cost.getText().toString().isEmpty()||time.getText().toString().isEmpty()||itinery.getText().toString().isEmpty())
        {
            Toast.makeText(finaledit.this,"Enter Details",Toast.LENGTH_SHORT).show();
        }
        else {
            costt=cost.getText().toString();
            timeperiod=time.getText().toString();
            itineary=itinery.getText().toString();
            img=image.getText().toString();
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("tours");
            AddTourClass helperClass = new AddTourClass(place, costt, timeperiod, itineary,img);
            reference.child(place).setValue(helperClass);
            Toast.makeText(finaledit.this,"Edited",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(finaledit.this,Admin.class);
        }

        }
    }
