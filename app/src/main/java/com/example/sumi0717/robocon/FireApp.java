package com.example.sumi0717.robocon;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by sumi0717 on 08-05-2018.
 */

public class FireApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
