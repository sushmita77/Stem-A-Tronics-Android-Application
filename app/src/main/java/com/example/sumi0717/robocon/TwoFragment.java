package com.example.sumi0717.robocon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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


public class TwoFragment extends Fragment {
    EditText City;
    Button  Search;
    SQLiteDatabase db;
    ListView mylistView;
    public static final String DATABASE_NAME = "EventDetails";
    public static  final  String PARAM="param";
    Spinner timeshow;



    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Firebase.setAndroidContext(getContext());
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //   ImageView imageView = (ImageView) getView().findViewById(R.id.foo);
        // or  (ImageView) view.findViewById(R.id.foo);

        City=(EditText)getView().findViewById(R.id.city);
        Search=(Button) getView().findViewById(R.id.search);
        mylistView=(ListView)getView().findViewById(R.id.listView);
        timeshow=(Spinner)getView().findViewById(R.id.duration);

        db = getActivity().openOrCreateDatabase(DATABASE_NAME,android.content.Context.MODE_PRIVATE ,null);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String inputCity=City.getText().toString().trim();
                String inputGot= (String) timeshow.getSelectedItem();
               final  List<String> myList=new ArrayList<String>();



                if(inputGot.equals("All Events"))
                {
                    // Toast.makeText(getContext(),"got "+inputCity,Toast.LENGTH_SHORT).show();

                    Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Trainings/");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                            {
                                if(dsp.child("city").getValue(String.class).equals(inputCity))
                                {
                                    String start_date=dsp.child("start_date").getValue(String.class);
                                    SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");


                                    try {
                                        Date chkDate = curFormater.parse(start_date);
                                        Calendar cal = Calendar.getInstance();
                                        String date1 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1) +"-"+cal.get(Calendar.YEAR);

                                        Date currentDate = curFormater.parse(date1);

                                        if(chkDate.compareTo(currentDate)>=0){
                                            myList.add("\n"+dsp.child("trainingName").getValue(String.class)+"\n"+dsp.child("institutionName").getValue(String.class)+"\n"+dsp.child("start_date").getValue(String.class)+" at "+dsp.child("start_time").getValue(String.class));


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
                            }
                            String values[]=new String[myList.size()];
                            values=myList.toArray(values);


                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,values);
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


                else if(inputGot.equals("Today"))
                {

                    Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Trainings/");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                            {
                                if(dsp.child("city").getValue(String.class).equals(inputCity))
                                {
                                    String start_date=dsp.child("start_date").getValue(String.class);
                                    SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");


                                    try {
                                        Date chkDate = curFormater.parse(start_date);
                                        Calendar cal = Calendar.getInstance();
                                        String date1 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1) +"-"+cal.get(Calendar.YEAR);

                                        Date currentDate = curFormater.parse(date1);

                                        if(chkDate.compareTo(currentDate)==0){
                                            myList.add("\n"+dsp.child("trainingName").getValue(String.class)+"\n"+dsp.child("institutionName").getValue(String.class)+"\n"+dsp.child("start_date").getValue(String.class)+" at "+dsp.child("start_time").getValue(String.class));


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
                            }
                            String values[]=new String[myList.size()];
                            values=myList.toArray(values);


                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,values);
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
                else if(inputGot.equals("Next 6 months"))
                {
                    Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Trainings/");
                    mref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot dsp:dataSnapshot.getChildren() )
                            {
                                if(dsp.child("city").getValue(String.class).equals(inputCity))
                                {
                                    String dateStr=dsp.child("start_date").getValue(String.class);
                                    SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");


                                    try {
                                        Date chkDate = curFormater.parse(dateStr);
                                        Calendar cal = Calendar.getInstance();
                                        String date1 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1) +"-"+cal.get(Calendar.YEAR);
                                        //System.out.println(date1);
                                        Date currentDate = curFormater.parse(date1);
                                        cal.add(Calendar.MONTH, 6);
                                        String date2 = cal.get(Calendar.DATE)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
                                        //System.out.println(date2);

                                        Date after6Months = curFormater.parse(date2);
                                        //System.out.println(after6Months);

                                        if(chkDate.compareTo(currentDate)>=0){
                                            if(chkDate.compareTo(after6Months)<0){

                                                myList.add("\n"+dsp.child("trainingName").getValue(String.class)+"\n"+dsp.child("institutionName").getValue(String.class)+"\n"+dsp.child("start_date").getValue(String.class)+" at "+dsp.child("start_time").getValue(String.class));

                                            }

                                        }


                                    } catch (ParseException e) {

                                        e.printStackTrace();
                                    }


//                                    Toast.makeText(getContext(),"in if",Toast.LENGTH_SHORT).show();

                                }
                            }
                            String values[]=new String[myList.size()];
                            values=myList.toArray(values);


                            ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,values)
                            {
                                @Override
                                public View getView(int position, View convertView, ViewGroup parent){
                                    // Get the current item from ListView
                                    View view = super.getView(position,convertView,parent);
                                    if(position %2 == 1)
                                    {
                                        // Set a background color for ListView regular row/item
                                        view.setBackgroundColor(Color.parseColor("#ffcccc"));
                                    }
//                                    else
//                                    {
//                                        // Set the background color for alternate row/item
//                                        view.setBackgroundColor(Color.parseColor("#FFCCCB4C"));
//                                    }
                                    return view;
                                }
                            };
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
                }  //else if closes


//
//                String values[]=new String[myList.size()];
//                values=myList.toArray(values);
//
//
//                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,values);
//                mylistView.setAdapter(adapter);
//                mylistView.setVisibility(View.VISIBLE);
//


                //closing the cursor
           //     cursorEmployees.close();
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
                        Intent intent = new Intent(getActivity(), ShowDynamicView.class);
                        Bundle extras = new Bundle();
                        extras.putString("CompName",lines[1]);
                        extras.putString("who_calls","trainings");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                });


            }
        });


    }
}
