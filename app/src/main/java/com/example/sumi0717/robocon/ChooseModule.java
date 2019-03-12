package com.example.sumi0717.robocon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseModule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_module);
        Button event=(Button) findViewById(R.id.competition);
        Button training=(Button)findViewById(R.id.training);
        Button del=(Button)findViewById(R.id.deleteev);
        Button viewU=(Button)findViewById(R.id.viewusers);



        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addevents.class);
                startActivity(i);
            }
        });

        training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),addTrainings.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DeleteEvent.class);
                startActivity(i);
            }
        });
    }
}
