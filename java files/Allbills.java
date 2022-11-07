package com.example.toursandtravelfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Allbills extends AppCompatActivity {
    ListView listView;
    List<allbillclass> Tname=new ArrayList<allbillclass>();
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    String name,cost,location;
    String phoneno="11";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allbills);
        listView=findViewById(R.id.allbill);
        final DatabaseReference personDetails = firebaseDatabase.getReference("bill");
        personDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                DataSnapshot lastDataSnapshot = null;
                Iterable<DataSnapshot> iterable = snapshot.getChildren();
                for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                    lastDataSnapshot = (DataSnapshot) iterator.next();
                    Log.d("userdets", lastDataSnapshot.getKey());
                    final DatabaseReference billloc=personDetails.child(lastDataSnapshot.getKey());
                    //Log.d("billinfo",billloc.toString());
                    billloc.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            DataSnapshot lastDataSnapshot = null;
                            Iterable<DataSnapshot> iterable = snapshot.getChildren();
                            for (Iterator iterator = iterable.iterator(); iterator.hasNext(); ) {
                                lastDataSnapshot = (DataSnapshot) iterator.next();
                                Log.d("userinfo", lastDataSnapshot.getKey());
                                cost=snapshot.child(lastDataSnapshot.getKey()).child("cost").getValue().toString();
                                location=snapshot.child(lastDataSnapshot.getKey()).child("location").getValue().toString();
                                name=snapshot.child(lastDataSnapshot.getKey()).child("name").getValue().toString();
                                phoneno=snapshot.child(lastDataSnapshot.getKey()).child("phoneno").getValue().toString();
                                allbillclass allbillclass=new allbillclass(name,location,phoneno,cost);
                                Tname.add(allbillclass);
                                Log.d("detailsin",phoneno+location);
                            }
                            Asynchronous asynk=new Asynchronous();
                            asynk.execute();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    //allbillclass t=new allbillclass(lastDataSnapshot.getKey());
                    //Tname.add(t);}

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private class Asynchronous extends AsyncTask<String ,Void,List<allbillclass>> {
        @Override
        protected List<allbillclass> doInBackground(String... strings) {
            return Tname;
        }

        @Override
        protected void onPostExecute(List<allbillclass> allbillclasses) {
            Log.d("Async" , "InAsynchronous");
            final BillNamesArrayAdapter cityadapter=new  BillNamesArrayAdapter(Allbills.this , allbillclasses);
            listView.setAdapter(cityadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Tourlistclass f=(Tourlistclass) cityadapter.getItem(position);
                    //Intent in=new Intent(Allbills.this,finaledit.class);
                    //in.putExtra("location",f.location);
                    //startActivity(in);

                }
            });
        }
    }
}