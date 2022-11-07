package com.example.toursandtravelfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.toursandtravelfinal.R;
import com.example.toursandtravelfinal.Tourlistclass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Tour extends AppCompatActivity {
    ListView listView;
    List<Tourlistclass> Tname=new ArrayList<Tourlistclass>();
    FirebaseAuth firebaseAuth;
    String phoneno;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        listView=findViewById(R.id.tourname);
        Intent in=getIntent();
         phoneno = in.getStringExtra("phoneno");

        final DatabaseReference personDetails = firebaseDatabase.getReference("tours");
        personDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot lastDataSnapshot = null;
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                    lastDataSnapshot = (DataSnapshot) iterator.next();
                    Log.d("userdets",lastDataSnapshot.getKey());
                    Tourlistclass t=new Tourlistclass(lastDataSnapshot.getKey());
                    Tname.add(t);

                }
                Asynchronous asynk=new Asynchronous();
                asynk.execute();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });
    }

    private class Asynchronous extends AsyncTask<String ,Void,List<Tourlistclass>> {
        @Override
        protected List<Tourlistclass> doInBackground(String... strings) {
            return Tname;
        }

        @Override
        protected void onPostExecute(List<Tourlistclass> Tourlistclasses) {
            Log.d("Async" , "InAsynchronous");
            final TourNamesArrayAdapter cityadapter=new  TourNamesArrayAdapter(Tour.this , Tourlistclasses);
            listView.setAdapter(cityadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Tourlistclass f=(Tourlistclass) cityadapter.getItem(position);
                    Intent in=new Intent(Tour.this,Tourdetails.class);
                    in.putExtra("location",f.location);
                    in.putExtra("phoneno",phoneno);
                    startActivity(in);

                }
            });
        }
    }

    public void buttonpress(View view) {

    }
}