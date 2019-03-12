package com.example.sumi0717.robocon;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import com.example.sumi0717.robocon.R;
import com.example.sumi0717.robocon.OneFragment;
import com.example.sumi0717.robocon.TwoFragment;
import com.example.sumi0717.robocon.ThreeFragment;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageView imageView;
    public static final String DATABASE_NAME = "EventDetails";
    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase=openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);

        createTablesEv();
        createTablesTr();
        createTablesUs();
//        deleteRowsEv();;
//        deleteRowsTr();
//
//        deleteRowsUs();



       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        imageView=(ImageView)findViewById(R.id.img);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void createTablesEv()
    {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS donerobo (\n" +
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

        //Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();

    }
    public void createTablesTr()
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

      //  Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();

    }
    public void deleteRowsEv()
    {
        Toast.makeText(getApplicationContext(),"in delete rows",Toast.LENGTH_SHORT).show();
        String query="delete from donerobo";
        mDatabase.execSQL(query);

    }




    public void deleteRowsTr()
    {
        Toast.makeText(getApplicationContext(),"in delete rows",Toast.LENGTH_SHORT).show();
        String query="delete from donetraining";
        mDatabase.execSQL(query);

    }
    public void createTablesUs()
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

    //    Toast.makeText(this, " created tables ", Toast.LENGTH_SHORT).show();



    }

    public void deleteRowsUs()
    {
        String query="delete from myUserDetails";
        mDatabase.execSQL(query);

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Competitions");
        adapter.addFragment(new TwoFragment(), "Trainers");
     //   adapter.addFragment(new ThreeFragment(), "Stories");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}