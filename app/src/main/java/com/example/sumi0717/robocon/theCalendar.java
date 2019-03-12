package com.example.sumi0717.robocon;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class theCalendar extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    TextView txt;

    public theCalendar() {

    }

    @SuppressLint("ValidFragment")
    public theCalendar(View v) {
        txt = (TextView)v;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dp = new DatePickerDialog(getActivity(), this, year, month, day);
        dp.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
        return dp;

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i2+"/"+(i1+1)+"/"+i;
        txt.setText(date);
    }
}
