package com.example.sumi0717.robocon;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.UUID;


public class RegisterUser extends AppCompatActivity implements View.OnClickListener{

    String otp;
    EditText fname,lname,Email,Pass,CPass;
    Button submit;
    public static final String DATABASE_NAME = "EventDetails";
    SQLiteDatabase mDatabase;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        Firebase.setAndroidContext(this);


        fname=(EditText)findViewById(R.id.FirstName);
        lname=(EditText)findViewById(R.id.lastName);
        Email=(EditText)findViewById(R.id.email);
        Pass=(EditText)findViewById(R.id.password);
        CPass=(EditText)findViewById(R.id.cpsw);
        submit=(Button)findViewById(R.id.signup);
        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);


        submit.setOnClickListener(this);

       // createTables();
       // deleteRows();

    }
    @Override
    public void onClick(View v) {

        String otp_big= UUID.randomUUID().toString();
        otp=otp_big.substring(0, 6);
        //  addDetails();

        String fn=fname.getText().toString().trim();
        String ln=lname.getText().toString().trim();
        String emailid=Email.getText().toString().trim();
        String passwordid=Pass.getText().toString().trim();
        String cp=CPass.getText().toString().trim();

        if(allEntered(fn,ln,emailid,passwordid,cp))
        {
            String subject = "Robocon Account Mail Verification ";
            String message = "Hello User \nPlease enter the following alpha-numeric verification code(OTP) given below to register your account \n"+otp;


            boolean value= isNetworkAvailable();
            if(value==false)
            {
                Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
            }
            else
            {
                SendMail sm = new SendMail(this, emailid, subject, message);

                //Executing sendmail to send email
                sm.execute();


                Intent i=new Intent(getApplicationContext(),Verifyotp.class);
                // Intent intent = new Intent(getActivity(), ShowDynamicView.class);
                Bundle extras = new Bundle();
                extras.putString("SystemValue",otp);

                extras.putString("whoCalled","mailveri");
                extras.putString("firstname",fn);
                extras.putString("lastname",ln);
                extras.putString("id",emailid);
                extras.putString("pass",passwordid);


                i.putExtras(extras);

                startActivity(i);
            }
            // Toast.makeText(getApplicationContext(),"is "+myemail,Toast.LENGTH_LONG).show();

            //Creating SendMail object


        }


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void createTables()
    {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS myUserDetails (\n" +
                        "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        "    firstname varchar(20) NOT NULL,\n" +
                        "    lastname varchar(20)  NOT NULL,\n" +
                        "    email varchar(100) NOT NULL,\n" +
                        "    password varchar(20) NOT NULL\n" +
                        ");"


        );

        Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();



    }

    public void deleteRows()
    {
        String query="delete from myUserDetails";
        mDatabase.execSQL(query);

    }
    public boolean allEntered(String fn,String ln,String email,String passw,String cp)
    {
        if(fn.isEmpty())
        {
            fname.requestFocus();
            fname.setError("Enter Text");
            return false;
        }
        if(ln.isEmpty())
        {

            lname.requestFocus();
            lname.setError("Please Enter Last Name");

            return false;
        }
        if(email.isEmpty())
        {
            Email.requestFocus();
            Email.setError(" Please Enter Email Id");

            return false;
        }
        if(passw.isEmpty())
        {
            Pass.requestFocus();
            Pass.setError(" Please Enter Password");

            return false;
        }
        if(cp.isEmpty())
        {
            CPass.requestFocus();
            CPass.setError("Please RE-Enter Password");

            return false;
        }
        if(!passw.equals(cp))
        {
            Toast.makeText(this, " Passwords do not match", Toast.LENGTH_SHORT).show();
        }
        if(!email.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Incorrect Email Address",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
    }
    public void addDetails()
    {
        String fn=fname.getText().toString().trim();
        String ln=lname.getText().toString().trim();
        String emailid=Email.getText().toString().trim();
        String passwordid=Pass.getText().toString().trim();
        String cp=CPass.getText().toString().trim();


        if(allEntered(fn,ln,emailid,passwordid,cp))
        {
            if(passwordid.equals(cp))
            {
                String insertSQL = "INSERT INTO myUserDetails \n" +
                        "(firstname, lastname, email, password)\n" +
                        "VALUES \n" +
                        "(?, ?, ?, ?);";

                //using the same method execsql for inserting values
                //this time it has two parameters
                //first is the sql string and second is the parameters that is to be binded with the query
                mDatabase.execSQL(insertSQL, new String[]{fn, ln, emailid, passwordid});

                Toast.makeText(this, " Registered Successfully", Toast.LENGTH_LONG).show();
                Intent i =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);

            }
            else
            {
                Toast.makeText(this, " Passwords do not match", Toast.LENGTH_SHORT).show();
            }

        }


    }
}