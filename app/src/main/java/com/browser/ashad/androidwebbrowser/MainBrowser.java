package com.browser.ashad.androidwebbrowser;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainBrowser extends AppCompatActivity
{





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_browser);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                Fragment selectedFragment=null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home:

                        selectedFragment = homfragment.newInstance();

                        break;
                    case R.id.navigation_dashboard:

                        selectedFragment=BookmarkFragment.newInstance();
                        break;
                    case R.id.navigation_notifications:

                        selectedFragment=Timeing.newInstance();
                        break;
                }


                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;

            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, homfragment.newInstance());
        transaction.commit();

    }


}
