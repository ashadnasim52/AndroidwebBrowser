package com.example.ashad.androidwebbrowser;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homfragment extends Fragment {
    Button searchbutton;
    EditText urlorkeyword;
    String urlwithuouthrrp;
    TextView google,yahoo,facebook,paytm,flipkart,snapdeal,pexels,instgram,twiiter,youtube,limkedin,stackoverflow;
    public static homfragment newInstance()
    {
        homfragment homfragmenta=new homfragment();
        return homfragmenta;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.homefragment,container,false);

        searchbutton=(Button)view.findViewById(R.id.searchbutton);
        urlorkeyword=view.findViewById(R.id.urlorkeyword);

        //,,,,,,,,,,,;
        google=view.findViewById(R.id.google);
        yahoo=view.findViewById(R.id.yahoo);
        facebook =view.findViewById(R.id.facebook);
        paytm=view.findViewById(R.id.paytm);
        flipkart=view.findViewById(R.id.flipkart);
        snapdeal=view.findViewById(R.id.snapdeal);
        pexels=view.findViewById(R.id.pexels);
        instgram=view.findViewById(R.id.instagram);
        twiiter=view.findViewById(R.id.twitter);
        youtube=view.findViewById(R.id.youtube);
        limkedin =view.findViewById(R.id.linkedin);
        stackoverflow=view.findViewById(R.id.stackoverflow);
//        =view.findViewById(R.id.);
//        =view.findViewById(R.id.);



        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast seaaching= Toast.makeText(getContext(), "Searching..", Toast.LENGTH_SHORT);

               String urlormaybekeyword=urlorkeyword.getText().toString();
               if (TextUtils.isEmpty(urlormaybekeyword))
               {
                  Toast error= Toast.makeText(getContext(),"Please Enter Something",Toast.LENGTH_SHORT);

                  error.show();
               }
               else
               {
                   seaaching.show();


                   urlwithuouthrrp=urlormaybekeyword.replace("https://www.","").replace("https://www.","").replace("https://www.","").replace("http://www.","").replace("https://www.","").replace("htt://www.","").replace("https://ww.","").replace("https://www.","").replace("https://","").replace("tps://www.","").replace("https://www.","").replace("https://.","").replace("htps://ww.","").replace("https://www.","").replace("ps://www.","").replace("https://www.","").replace("https://www.","").replace("://.","").replace("://www.","").replace("https://.","").replace("https://","").replace("ht://www.","").replace("https://www.","").replace("https://www.","").replace("tps://www.","").replace(".tx","").replace("tps://www.","").replace("tps://www.","").replace(".com","");



                    String htp="https://www.";
                    String com=".com";
                   Intent i=new Intent(getContext(),WebBrowser.class);
                   i.putExtra("site",htp.concat(urlwithuouthrrp).concat(com));
                   Log.i("weber","is"+htp+urlwithuouthrrp);
                   startActivity(i);
                   seaaching.cancel();
               }
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.google.com");
                startActivity(i);


            }
        });


        yahoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://in.yahoo.com/");
                startActivity(i);

            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.facebook.com/");
                startActivity(i);

            }
        });


        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://paytm.com/");
                startActivity(i);


            }
        });


        flipkart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.flipkart.com/");
                startActivity(i);


            }
        });


        snapdeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.snapdeal.com/");
                startActivity(i);


            }
        });


        pexels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.pexels.com/");
                startActivity(i);


            }
        });


        instgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.instagram.com/");
                startActivity(i);


            }
        });



        twiiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://twitter.com/");
                startActivity(i);


            }
        });



        youtube .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://www.youtube.com/");
                startActivity(i);


            }
        });



        limkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://in.linkedin.com/");
                startActivity(i);


            }
        });



        stackoverflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getContext(),WebBrowser.class);
                i.putExtra("site","https://stackoverflow.com/");
                startActivity(i);


            }
        });



//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
//
//
//
//        .setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });


        return view;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
