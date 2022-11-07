package com.example.toursandtravelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Bill extends AppCompatActivity {
    String location,cost,acc,phoneno;
    DatabaseReference reference,c1,c2;
    FirebaseDatabase firebaseDatabase;
    FirebaseDatabase rootNode;
    FirebaseAuth mAuth ;
    FirebaseAuth auth;
    String name;
    TextView person,location1,accountno,price;
    int c;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        person=findViewById(R.id.name111);

        location1=findViewById(R.id.place);
        price=findViewById(R.id.price);
        Intent in=getIntent();
        location=in.getStringExtra("location");
        cost=in.getStringExtra("cost");
        phoneno=in.getStringExtra("phoneno");
        location1.setText(location);
        price.setText(cost);
        firebaseDatabase = FirebaseDatabase.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("bill");
         mAuth = FirebaseAuth.getInstance();
         final DatabaseReference persond=firebaseDatabase.getReference().child("users").child(phoneno);
         persond.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 name=snapshot.child("name").getValue().toString();
                 Log.d("namecheck",name);
                 person.setText(name);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
        final DatabaseReference billdata = firebaseDatabase.getReference("bill");
        billdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                   Log.d("check00",phoneno+" "+ name);
                   billhelp b=new billhelp(name,acc,location,cost);
                   reference = FirebaseDatabase.getInstance().getReference("bill").child(phoneno).child(location);

                   // reference.child("bill").child(number).child("location").child("acc").setValue(cakcsa);
                //reference.child("bill").child(number).child("location").child("cos").setValue(cakcsa);

                   HashMap<String, String> hashMap = new HashMap<>();
                   hashMap.put("name", name);
                   hashMap.put("location", location);
                   hashMap.put("cost", cost);
                   hashMap.put("phoneno", phoneno);
                   reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           Log.d("donedone", "donedone");
                           Toast.makeText(Bill.this,"Added ",Toast.LENGTH_SHORT).show();
                       }
                   });
                   //reference.child(phoneno).push().setValue(b);
                if (snapshot.hasChild("cost")){
                    Log.d("heschild","yes");
                    billdata.child("cost").removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}