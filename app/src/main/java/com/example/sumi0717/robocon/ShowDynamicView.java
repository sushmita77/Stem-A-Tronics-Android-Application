package com.example.sumi0717.robocon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class ShowDynamicView extends AppCompatActivity {

    public static final String DATABASE_NAME = "EventDetails";
//    SQLiteDatabase mDatabase;
   TextView contact,descp,venue,date;
   Button show;
   String location=null;
  //  TextView myvenue;
//   ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dynamic_view);

       // mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        contact=(TextView)findViewById(R.id.contactD);
        descp=(TextView)findViewById(R.id.desc);
        venue=(TextView)findViewById(R.id.venue);
        date=(TextView)findViewById(R.id.datedetails);
        show=(Button)findViewById(R.id.showmap) ;
   //     imageView=(ImageView)findViewById(R.id.imageView);


    //    Cursor cursorEmployees=null;


//        String value=getIntent().getStringExtra(OneFragment.PARAM);
//        Toast.makeText(getApplicationContext(),"its rceived "+value,Toast.LENGTH_SHORT).show();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String value = extras.getString("CompName");
        String check = extras.getString("who_calls");

       // Toast.makeText(getApplicationContext(),"lets see"+value,Toast.LENGTH_SHORT).show();


                                if(check.equals("events"))
                                {
                                    Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Competitions/");
                                     mref.addValueEventListener(new ValueEventListener() {
                                         @Override
                                         public void onDataChange(DataSnapshot dataSnapshot) {
                                             for(DataSnapshot dsp:dataSnapshot.getChildren() ){

                                                 if(dsp.child("competitionName").getValue(String.class).equals(value))
                                                 {
                                                     date.setText("\t"+dsp.child("start_date").getValue(String.class)+" to \t"+dsp.child("end_date").getValue(String.class) +" \n"+"\t"+"Time: "+dsp.child("start_time").getValue(String.class)+" - "+dsp.child("end_time").getValue(String.class));
                                                     venue.setText("\t"+dsp.child("institutionName").getValue(String.class)+"\n"+"\t"+dsp.child("institutionAddress").getValue(String.class)+"\n"+"\t"+dsp.child("city").getValue(String.class)+"\t "+dsp.child("pincode").getValue(String.class)+"\n"+"\t"+dsp.child("state").getValue(String.class)+"\n"+"\t"+dsp.child("country").getValue(String.class));
                                                     contact.setText("\t"+dsp.child("contactN").getValue(String.class)+"\n"+"\t"+dsp.child("contactE").getValue(String.class)+"\n"+"\t"+dsp.child("contactPH").getValue(String.class));
                                                     descp.setText("\t"+dsp.child("description").getValue(String.class) +"\n"+ "\t"+"Registration Fee :"+dsp.child("registrationFee").getValue(String.class));
                                                     location=dsp.child("institutionName").getValue(String.class)+dsp.child("institutionAddress").getValue(String.class)+dsp.child("city").getValue(String.class)+dsp.child("state").getValue(String.class)+dsp.child("country").getValue(String.class);

                                                 }
                                             }
                                         }

                                         @Override
                                         public void onCancelled(FirebaseError firebaseError) {

                                         }
                                     });



                                     //cursorEmployees = mDatabase.rawQuery("SELECT * FROM donerobo where competitionName='"+value+"'", null);
                                }
                                if(check.equals("trainings"))
                                {
                                    Firebase mref=new Firebase("https://robocon-89a7c.firebaseio.com/Trainings/");
                                    mref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for(DataSnapshot dsp:dataSnapshot.getChildren() ){

                                                if(dsp.child("trainingName").getValue(String.class).equals(value))
                                                {
                                                    date.setText("\t"+dsp.child("start_date").getValue(String.class)+" to \t"+dsp.child("end_date").getValue(String.class) +" \n"+"\t"+"Time: "+dsp.child("start_time").getValue(String.class)+" - "+dsp.child("end_time").getValue(String.class));
                                                    venue.setText("\t"+dsp.child("institutionName").getValue(String.class)+"\n"+"\t"+dsp.child("institutionAddress").getValue(String.class)+"\n"+"\t"+dsp.child("city").getValue(String.class)+"\t "+dsp.child("pincode").getValue(String.class)+"\n"+"\t"+dsp.child("state").getValue(String.class)+"\n"+"\t"+dsp.child("country").getValue(String.class));
                                                    contact.setText("\t"+dsp.child("contactN").getValue(String.class)+"\n"+"\t"+dsp.child("contactE").getValue(String.class)+"\n"+"\t"+dsp.child("contactPH").getValue(String.class));
                                                    descp.setText("\t"+dsp.child("description").getValue(String.class) +"\n"+ "\t"+"Registration Fee :"+dsp.child("registrationFee").getValue(String.class));
                                                    location=dsp.child("institutionName").getValue(String.class)+dsp.child("institutionAddress").getValue(String.class)+dsp.child("city").getValue(String.class)+dsp.child("state").getValue(String.class)+dsp.child("country").getValue(String.class);

                                                }
                                            }

                                        }

                                        @Override
                                        public void onCancelled(FirebaseError firebaseError) {

                                        }
                                    });

                                    // cursorEmployees = mDatabase.rawQuery("SELECT * FROM donetraining where competitionName='"+value+"'", null);
                                }

//
//        while (cursorEmployees.moveToNext()) {
//
//            String instiN=cursorEmployees.getString(cursorEmployees.getColumnIndex("institutionName"));
//
//            String instiA=cursorEmployees.getString(cursorEmployees.getColumnIndex("institutionAddress"));
//            String pincode=cursorEmployees.getString(cursorEmployees.getColumnIndex("pincode"));
//            String start_date=cursorEmployees.getString(cursorEmployees.getColumnIndex("start_date"));
//            String start_time=cursorEmployees.getString(cursorEmployees.getColumnIndex("start_time"));
//            String end_date=cursorEmployees.getString(cursorEmployees.getColumnIndex("end_date"));
//            String end_time=cursorEmployees.getString(cursorEmployees.getColumnIndex("end_time"));
//            String city=cursorEmployees.getString(cursorEmployees.getColumnIndex("city"));
//            String state=cursorEmployees.getString(cursorEmployees.getColumnIndex("state"));
//            String country=cursorEmployees.getString(cursorEmployees.getColumnIndex("country"));
//            String description=cursorEmployees.getString(cursorEmployees.getColumnIndex("description"));
//            String registrationFee=cursorEmployees.getString(cursorEmployees.getColumnIndex("registrationFee"));
//            String contactE=cursorEmployees.getString(cursorEmployees.getColumnIndex("contactE"));
//            String contactN=cursorEmployees.getString(cursorEmployees.getColumnIndex("contactN"));
//            String contactPH=cursorEmployees.getString(cursorEmployees.getColumnIndex("contactPH"));
//            location=instiN+instiA+city +state+country;
//
//        //    Toast.makeText(getApplicationContext(),"lets see"+value,Toast.LENGTH_SHORT).show();
//
//          //  Toast.makeText(getApplicationContext(),"looo"+instiN+start_date+start_time,Toast.LENGTH_SHORT).show();
////
//         date.setText("\t"+start_date+" to \t"+end_date +" \n"+"\t"+"Time: "+start_time+" - "+end_time);
//            venue.setText("\t"+instiN+"\n"+"\t"+instiA+"\n"+"\t"+city+"\t "+pincode+"\n"+"\t"+state+"\n"+"\t"+country);
//  contact.setText("\t"+contactN+"\n"+"\t"+contactE+"\n"+"\t"+contactPH);
//            descp.setText("\t"+description +"\n"+ "\t"+"Registration Fee :"+registrationFee);
//
////            Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode("Maharashtra Institute of Technology"));
////            imageView.setImageURI(null);
////            imageView.setImageURI(mapUri);
//            // your content
//        }

           // cursorEmployees.close();

        //---dooo
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }
        });

    }
}
