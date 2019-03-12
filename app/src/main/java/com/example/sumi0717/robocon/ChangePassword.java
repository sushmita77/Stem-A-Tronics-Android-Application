package com.example.sumi0717.robocon;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.mail.internet.InternetAddress;

public class ChangePassword extends AppCompatActivity {

    EditText new_pass,confirm;
    Button submit;
    public static final String DATABASE_NAME = "EventDetails";
    SQLiteDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        new_pass=(EditText)findViewById(R.id.new_P);
        confirm=(EditText)findViewById(R.id.new_RP);
        submit=(Button)findViewById(R.id.change);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NewPass=new_pass.getText().toString().trim();
                String ConfirmPass=confirm.getText().toString().trim();

                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                String user = b.getString("EmailForward");



                if(allentered(NewPass,ConfirmPass))
                {

                    if(NewPass.equals(ConfirmPass))
                    {
                        String query="update myUserDetails set password='"+NewPass+"' where email='"+user+"'";

                        mDatabase.execSQL(query);
                        Toast.makeText(getApplicationContext(),"Password changed",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(getApplication(),"Passwords do not Match",Toast.LENGTH_SHORT).show();
                    }
                }




            }
        });




    }

    public boolean allentered(String sn,String cp)
    {
        if(sn.isEmpty())
        {
            new_pass.requestFocus();
            new_pass.setError("Please Enter New Password");

            return false;
        }
        if(cp.isEmpty())
        {
            confirm.requestFocus();
            confirm.setError("Please Re Enter Password");
            return  false;
        }
        return  true;
    }
}
