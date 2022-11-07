package com.example.toursandtravelfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

public class deletetour extends AppCompatActivity {
    ListView listView;
    List<Tourlistclass> Tname=new ArrayList<Tourlistclass>();
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletetour);
        listView=findViewById(R.id.tourname);

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
            final TourNamesArrayAdapter cityadapter=new  TourNamesArrayAdapter(deletetour.this , Tourlistclasses);
            listView.setAdapter(cityadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("tours");

                    Tourlistclass f=(Tourlistclass) cityadapter.getItem(position);
                    String place = null;
                    String costt=null;
                    String timeperiod=null;
                    String itineary=null;
                    String img=null;
                    AddTourClass helperClass = new AddTourClass(place, costt, timeperiod, itineary,img);
                    reference.child(f.location).setValue(helperClass);
                    Toast.makeText(deletetour.this,"Deleted",Toast.LENGTH_SHORT).show();


                }
            });
        }
    }

}