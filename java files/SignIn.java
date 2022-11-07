package com.example.toursandtravelfinal;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    EditText pass, ph;
    String phoneno, password;
    String a, b;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        pass = findViewById(R.id.reg_password);

        ph = findViewById(R.id.reg_phoneNo);
        btn = findViewById(R.id.reg_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = pass.getText().toString();
                phoneno = ph.getText().toString();
                Log.d("password", password + "  " + phoneno);
                loadDataFromFireBase();


            }
        });


    }

    void loadDataFromFireBase() {

        //Whenever there is any update in assocaited data following code will be executed each time
        final DatabaseReference personDetails = firebaseDatabase.getReference("users");
        final DatabaseReference userdets = personDetails.child(phoneno);
        userdets.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    a = dataSnapshot.getKey();
                    b = dataSnapshot.child("password").getValue().toString();
                    Log.d("datasnap", b);
                    if(b.equalsIgnoreCase(password)){
                        Toast.makeText(SignIn.this,"Logged in",Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(SignIn.this,User .class);
                        in.putExtra("phoneno",phoneno);
                       startActivity(in);
                    }
                    else{
                        Toast.makeText(SignIn.this, "not there", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SignIn.this, "not there", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void Admin(View view) {
        final DatabaseReference admin = firebaseDatabase.getReference("Admin");
        password = pass.getText().toString();
        phoneno = ph.getText().toString();
        admin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    a = dataSnapshot.getKey();
                    b = dataSnapshot.child("password").getValue().toString();
                    Log.d("datasnap", b+password);
                    if(b.equalsIgnoreCase(password)){
                        Toast.makeText(SignIn.this,"Logged in",Toast.LENGTH_SHORT).show();
                        Intent in=new Intent(SignIn.this,Admin.class);
                        in.putExtra("phoneno",phoneno);
                        startActivity(in);
                    }
                    else{
                        Toast.makeText(SignIn.this, "not there", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(SignIn.this, "not there", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}