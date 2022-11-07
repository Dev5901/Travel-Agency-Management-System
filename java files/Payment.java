package com.example.toursandtravelfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {
    EditText acc1,acc2,acc3,acc4,exp,cv;
    String acc,cvc,expire,phoneno;
    String location,cost;
    Button finsh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        finsh=findViewById(R.id.finishpay);
        Intent in=getIntent();
        location=in.getStringExtra("location");
        phoneno=in.getStringExtra("phoneno");
        cost=in.getStringExtra("cost");
        acc1=findViewById(R.id.ac1);
        acc2=findViewById(R.id.ac2);
        acc3=findViewById(R.id.ac3);
        acc4=findViewById(R.id.ac4);
        exp=findViewById(R.id.expiry);
        cv=findViewById(R.id.cvc);

        finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acc=acc1.getText().toString()+acc2.getText().toString()+acc3.getText().toString()+acc4.getText().toString();
                cvc=cv.getText().toString();
                expire=exp.getText().toString();
                if(acc.isEmpty()||cvc.isEmpty()||expire.isEmpty()){
                    Toast.makeText(Payment.this,"Enter All Details",Toast.LENGTH_SHORT).show();
                }
                else if(acc.length()!=13){
                    Toast.makeText(Payment.this,"Enter Valid Account Number",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Payment.this,"Payment Successful",Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(Payment.this,Bill.class);
                    in.putExtra("location",location);
                    in.putExtra("cost",cost);
                    in.putExtra("acc",acc);
                    in.putExtra("phoneno",phoneno);
                    startActivity(in);
                }

            }
        });
    }

    public void done(View view) {
        acc=acc1.getText().toString()+acc2.getText().toString()+acc3.getText().toString()+acc4.getText().toString();
        cvc=cv.getText().toString();
        expire=exp.getText().toString();
        if(acc.isEmpty()||cvc.isEmpty()||expire.isEmpty()){
            Toast.makeText(Payment.this,"Enter All Details",Toast.LENGTH_SHORT).show();
        }
        else if(acc.length()!=13){
            Toast.makeText(Payment.this,"Enter Valid Account Number",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(Payment.this,"Payment Successful",Toast.LENGTH_SHORT).show();
            Intent in=new Intent(Payment.this,Bill.class);
            in.putExtra("location",location);
            in.putExtra("cost",cost);
            in.putExtra("acc",acc);
            in.putExtra("phoneno",phoneno);
            startActivity(in);
        }
    }
}