package com.example.sumi0717.robocon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RetrieveFir extends AppCompatActivity {
    Button check;
    String location;
   // String ans;
   List<String> ansList;
   TextView chck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_fir);
        check = (Button) findViewById(R.id.ghe);
        chck=(TextView)findViewById(R.id.changeq);

        ansList=new ArrayList<String>();


        Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Competitions/");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp:dataSnapshot.getChildren() )
                {
                    if(dsp.child("city").getValue(String.class).equals("Pune"))
                    {
                        Toast.makeText(getApplicationContext(),"in if",Toast.LENGTH_SHORT).show();

                        ansList.add(dsp.child("state").getValue(String.class)+dsp.child("country").getValue(String.class));
                    }
                }

                // value=dataSnapshot.child("city").getValue(String.class);
             //   Toast.makeText(getApplicationContext(),"its "+value,Toast.LENGTH_SHORT).show();
                chck.setText(ansList.get(0)+ansList.get(1)+ansList.get(2));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"its "+value,Toast.LENGTH_SHORT).show();
            }
        });


    }
}