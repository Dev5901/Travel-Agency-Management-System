package com.example.toursandtravelfinal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class Razorpay extends AppCompatActivity implements PaymentResultListener {
    private Button startpayment,billl;
    private EditText orderamount;
    private String TAG =" main";
    DatabaseReference reference;
    String phoneno,location,cost,name;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(Razorpay.this,"Inside Razor pay" , Toast.LENGTH_SHORT).show();
        setContentView(R.layout.activity_razorpay);
        billl=findViewById(R.id.billclass);
        billl.setClickable(false);
        billl.setEnabled(false);
        Intent in=getIntent();
        phoneno=in.getStringExtra("phoneno");
        cost=in.getStringExtra("cost");
        location=in.getStringExtra("location");

        startpayment = (Button) findViewById(R.id.startpayment);
        orderamount = (EditText) findViewById(R.id.orderamount);

        billl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("buttonworks","works");
                Intent in=new Intent(Razorpay.this,Bill.class);
                in.putExtra("location",location);
                in.putExtra("cost",cost);
                in.putExtra("phoneno",phoneno);
                startActivity(in);
            }
        });
    }

    public void startPayment() {
        /**
         * You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Manan Shah");
            options.put("description", "Testing Payment");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://rzp-mobile.s3.amazonaws.com/images/rzp.png");
            options.put("currency", "INR");
            String payment = orderamount.getText().toString();
            // amount is in paise so please multiple it by 100
            //Payment failed Invalid amount (should be passed in integer paise. Minimum value is 100 paise, i.e. ₹ 1)
            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "9mashah@gmail.com");
            preFill.put("contact", "9920595121" +
                    "" +
                    "");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        // payment successfull pay_DGU19rDsInjcF2
        Log.e(TAG, " payment successfull "+ s.toString());
        //Toast.makeText(this, "Payment successfully done! " +s, Toast.LENGTH_SHORT).show();
        Toast.makeText(Razorpay.this,"start intent",Toast.LENGTH_SHORT).show();
        billl.setClickable(true);
        billl.setEnabled(true);





    }




    @Override
    public void onPaymentError(int i, String s) {
        Log.e(TAG,  "error code "+String.valueOf(i)+" -- Payment failed "+s.toString()  );
        try {
            Toast.makeText(this, "Payment error please try again", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Exception in onPaymentError", e);
        }

    }

    public void payment(View view) {
        if(orderamount.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Amount is empty", Toast.LENGTH_LONG).show();
        }else {
            startPayment();
        }
    }



}