package com.example.toursandtravelfinal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddTour extends AppCompatActivity {
    //Variables
    EditText location, cost, timeperiod, itineary,image;
    Button regBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    double randomno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);
//Hooks to all xml elements in activity_sign_up.xml
        location = findViewById(R.id.reg_name);
        image=findViewById(R.id.image);
        cost = findViewById(R.id.reg_username);
        timeperiod = findViewById(R.id.reg_email);
        itineary = findViewById(R.id.reg_phoneNo);

        regBtn = findViewById(R.id.reg_btn);
//Save data in FireBase on button click
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("tours");
//Get all the values
                String place = location.getText().toString();
                String tcost = cost.getText().toString();
                String time = timeperiod.getText().toString();
                String itinery = itineary.getText().toString();
                String img=image.getText().toString();
                Log.d("imageshow",img);

                //AddTourClass helperClass = new AddTourClass(place, tcost, time, itinery,img);
                //reference.child(place).setValue(helperClass);
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("image", img);
                hashMap.put("cost", tcost);
                hashMap.put("time", time);
                hashMap.put("name", place);
                hashMap.put("iteneary", itinery);
                reference.child(place).setValue(hashMap);
                Toast.makeText(AddTour.this,"clickdd",Toast.LENGTH_SHORT).show();
            }
        });//Register Button method end
    }//onCreate Method End
}