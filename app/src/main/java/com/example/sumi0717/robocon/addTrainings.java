package com.example.sumi0717.robocon;

import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class addTrainings extends AppCompatActivity {

    public static final String DATABASE_NAME = "EventDetails";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    SQLiteDatabase mDatabase;
//    Spinner country;
//    Spinner city,state;
    EditText myCountry,myState,myCity;
    Button mysubmit;

    EditText competitionName,institutionName,institutionAddress, registrationFee,pincode,descriptionMy;

    TextView startDate,startTime,endDate,endTime;
    EditText PName,PEmail,PhNO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trainings);

        mysubmit=(Button)findViewById(R.id.submit);


        myCountry=(EditText) findViewById(R.id.country);
        myCity=(EditText) findViewById(R.id.city);
        myState=(EditText) findViewById(R.id.state);

        competitionName=(EditText)findViewById(R.id.trainingName);
        institutionAddress=(EditText)findViewById(R.id.InstitutionAddress);
        institutionName=(EditText)findViewById(R.id.institutionName);
        pincode=(EditText)findViewById(R.id.pincode);
        registrationFee=(EditText)findViewById(R.id.RegistrationFee);

        descriptionMy=(EditText)findViewById(R.id.description);
        //startDate=(EditText)findViewById(R.id.start_date);
        //DatePicker startdate=(DatePicker)findViewById(R.id.start_date);


        startDate=(TextView)findViewById(R.id.start_date) ;
        startTime=(TextView)findViewById(R.id.start_time);
        endDate=(TextView)findViewById(R.id.end_date);
        endTime=(TextView)findViewById(R.id.end_time) ;


        PName=(EditText)findViewById(R.id.contact_person);
        PEmail=(EditText)findViewById(R.id.contact_email);
        PhNO=(EditText)findViewById(R.id.phone_no);

        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

      //  createTables();
      //  deleteRows();


        mysubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"on button click",Toast.LENGTH_SHORT);

                String competition=competitionName.getText().toString().trim();
                String institutionN=institutionName.getText().toString().trim();
                String institutionA=institutionAddress.getText().toString().trim();
                String Country = myCountry.getText().toString().trim();
                String State=myState.getText().toString().trim();
                String City =myCity.getText().toString().trim();
                String Pincode=pincode.getText().toString().trim();
                String RegistrationF=registrationFee.getText().toString().trim();
                String mydescription=descriptionMy.getText().toString().trim();
                String Start_time=startTime.getText().toString().trim();
                String StartDate=startDate.getText().toString().trim();
                String EndDate=endDate.getText().toString().trim();
                String End_time=endTime.getText().toString().trim();
                String Contact_name=PName.getText().toString().trim();
                String Contact_email=PEmail.getText().toString().trim();
                String Contact_No=PhNO.getText().toString().trim();

             //   Toast.makeText(getApplicationContext(),City,Toast.LENGTH_SHORT).show();


                if(allEntered(competition,mydescription,StartDate,EndDate,Start_time,End_time,institutionN,institutionA,Country,State,City,Pincode,RegistrationF,Contact_name,Contact_email,Contact_No))
                {

//                    String insertSQL = "INSERT INTO donetraining \n" +
//                            "(competitionName, institutionName, institutionAddress, country,state,city,pincode,description,start_date,end_date,start_time,end_time,contactN,contactE,contactPH,registrationFee)\n" +
//                            "VALUES \n" +
//                            "(?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?);";
//
//                    //using the same method execsql for inserting values
//                    //this time it has two parameters
//                    //first is the sql string and second is the parameters that is to be binded with the query
//                    mDatabase.execSQL(insertSQL, new String[]{competition, institutionN, institutionA, Country, State, City, Pincode,mydescription,StartDate,EndDate,Start_time,End_time,Contact_name,Contact_email,Contact_No, RegistrationF});


                    String otp_big= UUID.randomUUID().toString();
                    String unid=otp_big.substring(0, 6);
//                       String insertSQL = "INSERT INTO donerobo \n" +
//                               "(competitionName, institutionName, institutionAddress, country,state,city,pincode,description,start_date,end_date,start_time,end_time,contactN,contactE,contactPH,registrationFee)\n" +
//                               "VALUES \n" +
//                               "(?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?);";
//
//                       //using the same method execsql for inserting values
//                       //this time it has two parameters
//                       //first is the sql string and second is the parameters that is to be binded with the query
//                       mDatabase.execSQL(insertSQL, new String[]{competition, institutionN, institutionA, Country, State, City, Pincode,mydescription,StartDate,EndDate,Start_time,End_time,Contact_name,Contact_email,Contact_No, RegistrationF});

                    Firebase firebase=new Firebase("https://robocon-89a7c.firebaseio.com/Trainings/");
                    Firebase mainEle=firebase.child(unid);
                    Firebase firebasechild=mainEle.child("trainingName");
                    firebasechild.setValue(competition);
                    Firebase firebasechild2=mainEle.child("institutionName");
                    firebasechild2.setValue(institutionN);
                    Firebase firebasechild3=mainEle.child("institutionAddress");
                    firebasechild3.setValue(institutionA);
                    Firebase firebasechild4=mainEle.child("country");
                    firebasechild4.setValue(Country);

                    Firebase firebasechild5=mainEle.child("state");
                    firebasechild5.setValue(State);
                    Firebase firebasechild6=mainEle.child("city");
                    firebasechild6.setValue(City);

                    Firebase firebasechild7=mainEle.child("pincode");
                    firebasechild7.setValue(Pincode);
                    Firebase firebasechild8=mainEle.child("description");
                    firebasechild8.setValue(mydescription);

                    Firebase firebasechild9=mainEle.child("start_date");
                    firebasechild9.setValue(StartDate);
                    Firebase firebasechild10=mainEle.child("end_date");
                    firebasechild10.setValue(EndDate);


                    Firebase firebasechild11=mainEle.child("start_time");
                    firebasechild11.setValue(Start_time);
                    Firebase firebasechild12=mainEle.child("end_time");
                    firebasechild12.setValue(End_time);

                    Firebase firebasechild13=mainEle.child("contactN");
                    firebasechild13.setValue(Contact_name);
                    Firebase firebasechild14=mainEle.child("contactE");
                    firebasechild14.setValue(Contact_email);


                    Firebase firebasechild15=mainEle.child("contactPH");
                    firebasechild15.setValue(Contact_No);
                    Firebase firebasechild16=mainEle.child("registrationFee");
                    firebasechild16.setValue(RegistrationF);


                    Toast.makeText(getApplicationContext(), " Added Successfully", Toast.LENGTH_SHORT).show();
                    try {

                        Thread.sleep(2000);
                        clearAll();
                        openDialog();


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }




                    //   erase();
                    // }

                }

            }
        });





        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);

                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(addTrainings.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        startDate.setText(String.valueOf(i2)+"-"+String.valueOf(i1)+"-"+String.valueOf(i));
                    }
                }, year, month, day);
                dp.getDatePicker().setMinDate(System.currentTimeMillis()-1000);



                dp.setTitle("Select Date");
                dp.show();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int month = mcurrentTime.get(Calendar.MONTH);

                int day = mcurrentTime.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(addTrainings.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        i1=i1+1;
                        endDate.setText(String.valueOf(i2)+"-"+String.valueOf(i1)+"-"+String.valueOf(i));
                    }
                }, year, month, day);
                dp.getDatePicker().setMinDate(System.currentTimeMillis()-1000);



                dp.setTitle("Select Date");
                dp.show();
            }
        });





        startTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(endTime.getWindowToken(), 0);
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addTrainings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour;
                        String minute, amOrPm;
                        if (selectedHour > 12) {
                            hour = selectedHour - 12;
                            amOrPm= "PM";
                        } else {
                            if(selectedHour==12)
                            {
                                hour=12;
                                amOrPm= "PM";
                            }
                            else
                            {
                                hour = selectedHour;
                                amOrPm = "AM";
                            }

                        }
                        if(selectedMinute < 10) {
                            minute = "0"+selectedMinute;
                        } else {
                            minute = "" + selectedMinute;
                        }
                        startTime.setText(hour + " : " + minute + " " + amOrPm);
                        //  endTime.setText( hour + ":" + selectedMinute);
                    };
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });



        endTime.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(endTime.getWindowToken(), 0);
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(addTrainings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        int hour;
                        String minute, amOrPm;
                        if (selectedHour > 12) {
                            hour = selectedHour - 12;
                            amOrPm= "PM";
                        } else {
                            if(selectedHour==12)
                            {
                                hour=12;
                                amOrPm= "PM";
                            }
                            else
                            {
                                hour = selectedHour;
                                amOrPm = "AM";
                            }

                        }
                        if(selectedMinute < 10) {
                            minute = "0"+selectedMinute;
                        } else {
                            minute = "" + selectedMinute;
                        }
                        endTime.setText(hour + " : " + minute + " " + amOrPm);
                        //  endTime.setText( hour + ":" + selectedMinute);
                    };
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
//        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedItem = adapterView.getItemAtPosition(i).toString();
//                if(selectedItem.equals("India"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Maharashtra");
//                    list.add("Chennai");
//                    list.add("Gujarat");
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    state.setAdapter(dataAdapter);
//
//                }
//
//                else  if(selectedItem.equals("UK"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Northern Ireland");
//                    list.add("Scotland");
//                    list.add("Wales");
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    state.setAdapter(dataAdapter);
//
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        /*for state now*/
//        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String selectedItem = adapterView.getItemAtPosition(i).toString();
//                if(selectedItem.equals("Maharashtra"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Pune");
//                    list.add("Mumbai");
//                    list.add("Nashik");
//                    list.add("Nagpur");
//                    list.add("Aurangabad");
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(dataAdapter);
//
//                }
//
//                else  if(selectedItem.equals("Chennai"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Vadapalani");
//                    list.add("Pallavaram");
//                    list.add("Madhavaram");
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(dataAdapter);
//
//                }
//                else  if(selectedItem.equals("Scotland"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Edinburgh");
//                    list.add("Motherwell");
//                    list.add("Dumbarton");
//                    list.add("St Andrews");
//
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(dataAdapter);
//
//                }
//                else  if(selectedItem.equals("Northern Ireland"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Newry");
//                    list.add("Armagh");
//
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(dataAdapter);
//
//                }
//                else  if(selectedItem.equals("Wales"))
//                {
//                    // do your stuff
//                    List<String> list = new ArrayList<String>();
//                    list.add("Cardiff");
//                    list.add("St Davids");
//                    list.add("Newport");
//
//                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                            android.R.layout.simple_spinner_item, list);
//                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    city.setAdapter(dataAdapter);
//
//                }
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });



    }
    public void openDialog()
    {
        ExampleDialog exampleDialog=new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");
    }

    public void createTables()
    {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS donetraining (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    competitionName varchar(200) NOT NULL,\n" +
                        "    institutionName varchar(200)  NOT NULL,\n" +
                        "    institutionAddress varchar(200) NOT NULL,\n" +
                        "    country varchar(200) NOT NULL,\n" +
                        "    state varchar(200) NOT NULL,\n" +
                        "    city varchar(200) NOT NULL,\n" +
                        "    pincode varchar(20) NOT NULL,\n" +
                        "    description varchar(200) NOT NULL,\n" +
                        "    start_date varchar(20) NOT NULL,\n" +
                        "    end_date varchar(20) NOT NULL,\n" +
                        "    start_time varchar(20) NOT NULL,\n" +
                        "    end_time varchar(20) NOT NULL,\n" +
                        "    contactN varchar(20) NOT NULL,\n" +
                        "    contactE varchar(20) NOT NULL,\n" +
                        "    contactPH varchar(20) NOT NULL,\n" +
                        "    registrationFee varchar(20) NOT NULL\n" +
                        ");"


        );

        Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();

    }

    public boolean allEntered(String competition,String mydescription,String StartDate,String EndDate,String Start_time,String End_time,String institutionN,String institutionA,String Country,String State,String City,String Pincode,String RegistrationF,String cn,String ce,String cpn)
    {
    //    Toast.makeText(this, " in checking ", Toast.LENGTH_SHORT).show();

        if(competition.isEmpty())
        {
            competitionName.requestFocus();
            competitionName.setError("Please enter Competition Name");

            return false;
        }
        if(mydescription.isEmpty())
        {
            descriptionMy.requestFocus();
            descriptionMy.setError("Please enter Description");
            //  Toast.makeText(getApplicationContext(),"Please enter Description",Toast.LENGTH_SHORT).show();
            return false;
        }

        if(StartDate.equals("Choose Start Date"))
        {
            Toast.makeText(getApplicationContext(),"Please enter Start Date",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(EndDate.equals("Choose End Date"))
        {
            Toast.makeText(getApplicationContext(),"Please enter End Date",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(Start_time.equals("Choose Start Time"))
        {
            Toast.makeText(getApplicationContext(),"Please enter Start Time",Toast.LENGTH_SHORT).show();

            return false;
        }
        if(End_time.equals("Choose End time"))
        {
            Toast.makeText(getApplicationContext(),"Please enter End Time",Toast.LENGTH_SHORT).show();

            return false;
        }

        if(institutionN.isEmpty())
        {
            institutionName.requestFocus();
            institutionName.setError("Please enter Institution Name");

            return false;
        }
        if(institutionA.isEmpty())
        {
            institutionAddress.requestFocus();
            institutionAddress.setError("Please enter Institution Address");

            return false;
        }
        if(Country.isEmpty())
        {
            myCountry.requestFocus();
            myCountry.setError("Please enter Country");

            return false;
        }
        if(State.isEmpty())
        {
            myState.requestFocus();
            myState.setError("Please enter State");

            return false;
        }
        if(City.isEmpty())
        {
            myCity.requestFocus();
            myCity.setError("Please enter City");

            return false;
        }
        if(Pincode.isEmpty())
        {
            pincode.requestFocus();
            pincode.setError("Please enter Competition Name");

            return false;
        }
        if(RegistrationF.isEmpty())
        {
            registrationFee.requestFocus();
            registrationFee.setError("Please enter Competition Name");

            return false;
        }

        if(cn.isEmpty())
        {
            PName.requestFocus();
            PName.setError("Please enter Contact Name");

            return false;
        }
        if(ce.isEmpty())
        {
            PEmail.requestFocus();
            PEmail.setError("Please enter Contact Email");

            return false;
        }
        if(cpn.isEmpty())
        {
            PhNO.requestFocus();
            PhNO.setError("Please enter Phone No");

            return false;
        }
        if (!ce.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Incorrect Email Address",Toast.LENGTH_SHORT).show();
            return false;
        }
        try {
            SimpleDateFormat curFormater = new SimpleDateFormat("dd-MM-yyyy");
            Date startD = curFormater.parse(StartDate);
            SimpleDateFormat curFormater1 = new SimpleDateFormat("dd-MM-yyyy");
            Date endD = curFormater1.parse(EndDate);


            if(startD.compareTo(endD)>0){
                Toast.makeText(getApplicationContext(),"Start Date cannot be greater than End Date",Toast.LENGTH_LONG).show();
                return false;

            }

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return true;
    }

    public void clearAll()
    {


        myCity.setText("");
        myCountry.setText("");
        myState.setText("");
        competitionName.setText("");
        institutionAddress.setText("");
        institutionName.setText("");
        pincode.setText("");
        registrationFee.setText("");
        descriptionMy.setText("");
        startDate.setText("Choose Start Date");
        startTime.setText("Choose Start Time");
        endDate.setText("Choose End Date");
        endTime.setText("Choose End Time");
        PName.setText("");
        PEmail.setText("");
        PhNO.setText("");

    }


    public void deleteRows()
    {
        Toast.makeText(getApplicationContext(),"in delete rows",Toast.LENGTH_SHORT).show();
        String query="delete from donetraining";
        mDatabase.execSQL(query);

    }
}
