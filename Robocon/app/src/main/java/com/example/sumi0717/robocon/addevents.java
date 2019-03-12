package com.example.sumi0717.robocon;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addevents extends AppCompatActivity {

    public static final String DATABASE_NAME = "EventDetails";
    SQLiteDatabase mDatabase;
    Spinner country;
     Spinner city,state;
     Button submit;

    EditText competitionName,institutionName,institutionAddress, registrationFee,pincode;
    EditText startDate,Description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevents);



         submit=(Button)findViewById(R.id.submit);


        country=(Spinner)findViewById(R.id.country);
        city=(Spinner)findViewById(R.id.city);
        state=(Spinner)findViewById(R.id.state);

        competitionName=(EditText)findViewById(R.id.competitionName);
        institutionAddress=(EditText)findViewById(R.id.InstitutionAddress);
        institutionName=(EditText)findViewById(R.id.institutionName);
        pincode=(EditText)findViewById(R.id.pincode);
        registrationFee=(EditText)findViewById(R.id.RegistrationFee);

        Description=(EditText)findViewById(R.id.description);
        //startDate=(EditText)findViewById(R.id.start_date);
        DatePicker startdate=(DatePicker)findViewById(R.id.start_date);

        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        createTables();
   //     deleteRows();






        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("India"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Maharashtra");
                    list.add("Chennai");
                    list.add("Gujarat");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    state.setAdapter(dataAdapter);

                }

               else  if(selectedItem.equals("UK"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Northern Ireland");
                    list.add("Scotland");
                    list.add("Wales");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    state.setAdapter(dataAdapter);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*for state now*/
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = adapterView.getItemAtPosition(i).toString();
                if(selectedItem.equals("Maharashtra"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Pune");
                    list.add("Mumbai");
                    list.add("Nashik");
                    list.add("Nagpur");
                    list.add("Aurangabad");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);

                }

                else  if(selectedItem.equals("Chennai"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Vadapalani");
                    list.add("Pallavaram");
                    list.add("Madhavaram");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);

                }
                else  if(selectedItem.equals("Scotland"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Edinburgh");
                    list.add("Motherwell");
                    list.add("Dumbarton");
                    list.add("St Andrews");


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);

                }
                else  if(selectedItem.equals("Northern Ireland"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Newry");
                    list.add("Armagh");


                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);

                }
                else  if(selectedItem.equals("Wales"))
                {
                    // do your stuff
                    List<String> list = new ArrayList<String>();
                    list.add("Cardiff");
                    list.add("St Davids");
                    list.add("Newport");

                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_spinner_item, list);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    city.setAdapter(dataAdapter);

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"zhala",Toast.LENGTH_SHORT).show();
                addDetails();
            }
        });
    }

    public void createTables()
    {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS myEventDetails (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    competitionName varchar(200) NOT NULL,\n" +
                        "    institutionName varchar(200)  NOT NULL,\n" +
                        "    institutionAddress varchar(200) NOT NULL,\n" +
                        "    country varchar(200) NOT NULL,\n" +
                        "    state varchar(200) NOT NULL,\n" +
                        "    city varchar(200) NOT NULL,\n" +
                        "    pincode varchar(20) NOT NULL,\n" +
                        "    registrationFee varchar(20) NOT NULL\n" +

                        ");"

        );

        Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();


    }

    public boolean allEntered(String competition,String institutionN,String institutionA,String Country,String State,String City,String Pincode,String RegistrationF)
    {
        Toast.makeText(this, " in checking ", Toast.LENGTH_SHORT).show();
        if(competition.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Competition Name",Toast.LENGTH_SHORT).show();
            return false;
        }
          if(institutionN.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Institution Name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(institutionA.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Institution Address",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Country.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Country",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(State.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter State",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(City.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter City",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(Pincode.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Pincode",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(RegistrationF.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Please enter Registration Fee",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    public void addDetails()
    {
        Toast.makeText(this, " in add detials", Toast.LENGTH_SHORT).show();
        String competition=competitionName.getText().toString().trim();
        String institutionN=institutionName.getText().toString().trim();
        String institutionA=institutionAddress.getText().toString().trim();
        String Country = country.getSelectedItem().toString();
        String State=state.getSelectedItem().toString();
        String City =city.getSelectedItem().toString();
        String Pincode=pincode.getText().toString().trim();
        String RegistrationF=registrationFee.getText().toString().trim();

        if(allEntered(competition,institutionN,institutionA,Country,State,City,Pincode,RegistrationF))
        {

       //     Toast.makeText(this, " ****hotay******", Toast.LENGTH_SHORT).show();
//
         //   Cursor check = mDatabase.rawQuery("select * from myEventDetails", null);

         //   if(check.getCount()==0||!(check.moveToFirst()))
           // {


          //      Toast.makeText(this, " ****INNNNNNNNNNNNNNNNNNNN******", Toast.LENGTH_SHORT).show();
                String insertSQL = "INSERT INTO myEventDetails \n" +
                        "(competitionName, institutionName, institutionAddress, country,state,city,pincode,registrationFee)\n" +
                        "VALUES \n" +
                        "(?, ?, ?, ?,?,?,?,?);";

                //using the same method execsql for inserting values
                //this time it has two parameters
                //first is the sql string and second is the parameters that is to be binded with the query
                mDatabase.execSQL(insertSQL, new String[]{competition, institutionN, institutionA, Country, State, City, Pincode, RegistrationF});

                Toast.makeText(this, " Added Successfully", Toast.LENGTH_SHORT).show();

             //   erase();
           // }

        }

    }

    public void deleteRows()
    {
        String query="delete from myEventDetails";
        mDatabase.execSQL(query);

    }
}
