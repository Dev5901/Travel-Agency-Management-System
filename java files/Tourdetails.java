package com.example.toursandtravelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Tourdetails extends AppCompatActivity {
    String location,cost,time,itineary,phoneno,image;
    TextView costt,period,itinery,place;
    Button gotopayment;
    ImageView img;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourdetails);

        gotopayment=findViewById(R.id.gotopay);
        Intent in=getIntent();
        location=in.getStringExtra("location");
        phoneno = in.getStringExtra("phoneno");

        place=findViewById(R.id.placee);
        place.setText(location);
        img=findViewById(R.id.image);
        costt=findViewById(R.id.cost);
        period=findViewById(R.id.timee);
        itinery=findViewById(R.id.itineary);
        Log.d("intourdetails","yes");



         reference = FirebaseDatabase.getInstance().getReference().child("tours").child(location);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 cost=snapshot.child("cost").getValue().toString();
                 time=snapshot.child("time").getValue().toString();
                 itineary=snapshot.child("iteneary").getValue().toString();
                 image=snapshot.child("image").getValue().toString();
                 costt.setText(cost);
                 period.setText(time);
                 itinery.setText(itineary);
                 Log.d("imageload",image);
                 try {
                     Picasso.get().load(String.valueOf(image)).into(img);
                 }catch (Exception e){}

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void payment(View view) {
        Intent in=new Intent(Tourdetails.this,Razorpay.class);
      //  Toast.makeText(Tourdetails.this , "Going to Razor Pay" , Toast.LENGTH_SHORT).show();
        in.putExtra("location",location);
        in.putExtra("cost",cost);
        in.putExtra("amount", cost);
        in.putExtra("phoneno",phoneno);
        startActivity(in);
    }
}