package com.example.sumi0717.robocon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeleteEvent extends AppCompatActivity {

     ListView mylistView;
     Button home;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_event);

        mylistView=(ListView)findViewById(R.id.lv);
        home=(Button)findViewById(R.id.hme);
    //    myList=new ArrayList<String>();

        letsdoit();


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });



        mylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String selectedFromList = (String) mylistView.getItemAtPosition(i);
                String lines[] = selectedFromList.split("\\r?\\n");

//                        Toast.makeText(getActivity(),"the split is"+lines[0],Toast.LENGTH_SHORT).show();
//
//                        Intent appInfo = new Intent(getActivity(), ShowDynamicView.class);
//                        appInfo.putExtra(PARAM,lines[0]);
//                        startActivity(appInfo);

//                Intent intent = new Intent(getApplicationContext(), ShowDynamicView.class);
//                Bundle extras = new Bundle();
//                extras.putString("CompName",lines[1]);
                final String compN=lines[1];
                final Firebase mrefmain=new Firebase("https://robocon-89a7c.firebaseio.com/Competitions");
                Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Competitions/");
                mref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for(DataSnapshot dsp:dataSnapshot.getChildren() )
                        {

                                if(dsp.child("competitionName").getValue(String.class).equals(compN))
                                {

                                            String deleteit=dsp.getKey();


                                    try {

                                    mrefmain.child(deleteit).removeValue();
                                        Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                                        letsdoit();


                                    }
                                    catch (Exception e) {

                                        e.printStackTrace();
                                    }


//                                    Toast.makeText(getContext(),"in if",Toast.LENGTH_SHORT).show();

                                }


                        }

                        // value=dataSnapshot.child("city").getValue(String.class);
                        //   Toast.makeText(getApplicationContext(),"its "+value,Toast.LENGTH_SHORT).show();
                        //chck.setText(ansList.get(0)+ansList.get(1)+ansList.get(2));
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });


//                extras.putString("who_calls","events");
//                intent.putExtras(extras);
//                startActivity(intent);
            }
        });




    }

    public void letsdoit()
    {
       final List<String> myList=new ArrayList<String>();

        Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Competitions/");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot dsp:dataSnapshot.getChildren() )
                {

                    String start_date=dsp.child("start_date").getValue(String.class);
                    SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");


                    try {
                        Date chkDate = curFormater.parse(start_date);
                        Calendar cal = Calendar.getInstance();
                        String date1 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1) +"-"+cal.get(Calendar.YEAR);

                        Date currentDate = curFormater.parse(date1);

                        if(chkDate.compareTo(currentDate)>=0){
                            myList.add("\n"+dsp.child("competitionName").getValue(String.class)+"\n"+dsp.child("institutionName").getValue(String.class)+"\n"+dsp.child("start_date").getValue(String.class)+" at "+dsp.child("start_time").getValue(String.class));


                            //System.out.println("The entered date is within the range of six months");
                        }
                        else{
                            System.out.println("The entered date is before than the current system date");
                        }

                    }
                    catch (ParseException e) {

                        e.printStackTrace();
                    }


//                                    Toast.makeText(getContext(),"in if",Toast.LENGTH_SHORT).show();


                }
                String values[]=new String[myList.size()];
                values=myList.toArray(values);


                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,values);
                mylistView.setAdapter(adapter);
                mylistView.setVisibility(View.VISIBLE);
                // value=dataSnapshot.child("city").getValue(String.class);
                //   Toast.makeText(getApplicationContext(),"its "+value,Toast.LENGTH_SHORT).show();
                //chck.setText(ansList.get(0)+ansList.get(1)+ansList.get(2));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
