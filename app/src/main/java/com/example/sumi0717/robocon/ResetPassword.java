package com.example.sumi0717.robocon;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    Button resetit;
    EditText emailgot;
    String otp;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        resetit=(Button)findViewById(R.id.resetP);
        emailgot=(EditText)findViewById(R.id.emailE);


        resetit.setOnClickListener(this);
    }

    private void sendEmail() {
        //Getting content for email

        String otp_big= UUID.randomUUID().toString();
        otp=otp_big.substring(0, 6);
        String myemail = emailgot.getText().toString().trim();

        if(myemail.isEmpty())
        {
            emailgot.requestFocus();
            emailgot.setError("Please Enter your Email");

        }
        if(!myemail.matches(emailPattern))
        {
            Toast.makeText(getApplicationContext(),"Incorrect Email Address",Toast.LENGTH_SHORT).show();
        }
        else
        {
            String subject = "Robocon Account Password Recovery ";
            String message = "Hello User \nPlease enter the following numeric verification code(OTP) given below to recover your account \n"+otp;

            // Toast.makeText(getApplicationContext(),"is "+myemail,Toast.LENGTH_LONG).show();
            boolean value= isNetworkAvailable();
            if(value==false)
            {
                Toast.makeText(getApplicationContext(),"You are not connected to the internet",Toast.LENGTH_SHORT).show();
            }


            else
            {
                SendMail sm = new SendMail(this, myemail, subject, message);

                //Executing sendmail to send email
                sm.execute();

                Intent i=new Intent(getApplicationContext(),Verifyotp.class);
                // Intent intent = new Intent(getActivity(), ShowDynamicView.class);
                Bundle extras = new Bundle();
                extras.putString("SystemValue",otp);
                extras.putString("Email",myemail);
                extras.putString("whoCalled","forgotveri");

                i.putExtras(extras);

                startActivity(i);
            }

            //Creating SendMail object


        }

    }


    @Override
    public void onClick(View v) {
        sendEmail();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
