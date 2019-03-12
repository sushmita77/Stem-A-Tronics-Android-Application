package com.example.sumi0717.robocon;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by sumi0717 on 13-04-2018.
                                */

                        public class ExampleDialog extends AppCompatDialogFragment {
                            @Override
                            public Dialog onCreateDialog(Bundle savedInstanceState) {
                                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater=getActivity().getLayoutInflater();
                                View view =inflater.inflate(R.layout.layout_dialog,null);
                                builder.setView(view)
                                        .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {


                                            }
                                        })
                                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent i=new Intent(getActivity(),MainActivity.class);
                                                startActivity(i);


                    }
                });
         return builder.create();



    }
}
