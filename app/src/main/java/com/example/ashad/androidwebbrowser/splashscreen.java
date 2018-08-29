package com.example.ashad.androidwebbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        Thread th=new Thread(){
            @Override

            public void run()
            {
                try
                {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent i=new Intent(getApplicationContext(),MainBrowser.class);
                    startActivity(i);
                    finish();

                }
            }
        };
        th.start();


    }
    @Override
    protected  void onPause()
    {
        super.onPause();
        finish();
    }
}
