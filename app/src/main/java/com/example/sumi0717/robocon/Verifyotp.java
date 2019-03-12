package com.example.sumi0717.robocon;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.UUID;

public class Verifyotp extends AppCompatActivity {

    EditText otp;
    Button check;
    public static final String DATABASE_NAME = "EventDetails";
    SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyotp);


        otp=(EditText)findViewById(R.id.otpgot);
        check=(Button)findViewById(R.id.verify);
        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otpInput=otp.getText().toString().trim();

                if(otpInput.isEmpty())
                {
                    otp.requestFocus();
                    otp.setError("Please Enter Received OTP");
                }
                else
                {
                    Intent intent = getIntent();
                    Bundle extras = intent.getExtras();
//
                    String CalledBy = extras.getString("whoCalled");



                    if(CalledBy.equals("forgotveri"))
                    {
                        String comp_generated = extras.getString("SystemValue");
                        String UserEmail = extras.getString("Email");

                      //  Toast.makeText(getApplication()," system "+comp_generated,Toast.LENGTH_LONG).show();
                       // Toast.makeText(getApplication()," got "+otpInput,Toast.LENGTH_LONG).show();

                        if(otpInput.equals(comp_generated))
                        {
                            Intent i=new Intent(getApplicationContext(),ChangePassword.class);
                            Bundle b = new Bundle();
                            b.putString("EmailForward",UserEmail);
                            i.putExtras(b);

                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplication(),"Incorrect OTP", Toast.LENGTH_LONG).show();
                        }
                    }

                    if(CalledBy.equals("mailveri"))
                    {
                        String comp_generated = extras.getString("SystemValue");
                        String fname=extras.getString("firstname");
                        String lname=extras.getString("lastname");
                        String eid=extras.getString("id");
                        String pas=extras.getString("pass");


                        Toast.makeText(getApplication()," system "+comp_generated,Toast.LENGTH_LONG).show();
                      //  Toast.makeText(getApplication()," got "+otpInput,Toast.LENGTH_LONG).show();

                        if(otpInput.equals(comp_generated))
                        {

                            String insertSQL = "INSERT INTO myUserDetails \n" +
                                    "(firstname, lastname, email, password)\n" +
                                    "VALUES \n" +
                                    "(?, ?, ?, ?);";

                            //using the same method execsql for inserting values
                            //this time it has two parameters
                            //first is the sql string and second is the parameters that is to be binded with the query
                            mDatabase.execSQL(insertSQL, new String[]{fname, lname, eid, pas});

                            Toast.makeText(getApplication(), " Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);

                        }
                        else
                        {
                            Toast.makeText(getApplication(),"Incorrect OTP", Toast.LENGTH_LONG).show();
                        }


                    }


                }





            }
        });
    }

    public void getOTP()
    {

    }
}
