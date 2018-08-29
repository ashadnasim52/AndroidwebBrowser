package com.example.ashad.androidwebbrowser;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Date;

public class WebBrowser extends AppCompatActivity {

    SwipeRefreshLayout swipecontainer;
    EditText urlcontent;
    WebView browser;
    int permissioncode;
    int storagepermissioncode=1;
    ImageButton bookmark,items,home;
    Context mcontext;
    @Override
    protected void onPostResume() {

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "You have already granted this permission!", Toast.LENGTH_SHORT).show();
            permissioncode=2;
        }
        else
            permissioncode=1;

        super.onPostResume();
    }




    @Override
    public void onBackPressed() {


        if (browser.canGoBack())
        {
            browser.goBack();
        }
        else
        {
            super.onBackPressed();
            browser.clearCache(true);
            browser.clearHistory();
            browser.clearMatches();
            browser.clearFormData();
            browser.clearSslPreferences();
            WebStorage.getInstance().deleteAllData();

            try
            {
                mcontext.deleteDatabase("webview.db");
                mcontext.deleteDatabase("webviewCache.db");
            } catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);
        urlcontent=findViewById(R.id.urlshown);

        bookmark=findViewById(R.id.bookmark);
        home=findViewById(R.id.homego);
        items=findViewById(R.id.sshowitems);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent i=new Intent(getApplicationContext(),MainBrowser.class);
              startActivity(i);
            }
        });
        bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        swipecontainer=findViewById(R.id.swipecontainer);
        Intent i = getIntent();
        String webadress = i.getStringExtra("site");
        browser = findViewById(R.id.webvieww);
        browser.getSettings().setJavaScriptEnabled(true); // enable javascript


        browser.loadUrl(webadress);
        browser.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                urlcontent.setText(browser.getOriginalUrl());
                Log.i("ashadurl","is"+browser.getOriginalUrl());

                view.clearCache(true);

            }
        });

        urlcontent.setText(browser.getOriginalUrl());
        Log.i("ashadurl","is"+browser.getOriginalUrl());
        swipecontainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                browser.reload();
                Log.i("ashadmans","is "+browser.getProgress());
                if (browser.getProgress()==10)
                {
                    swipecontainer.setRefreshing(false);
                }

            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            //use checkSelfPermission()
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "You have already granted this permission!",
                        Toast.LENGTH_SHORT).show();
                //TODO need to be remoaved toast
                permissioncode=2;


                //TODO heree do any thing if permission granted






            }
            else {
                requeststoragepermisssion();
            }



        }
        else
        {
            //simply use the required feature
            //as the user has already granted permission to them during installation








        }

        browser.setDownloadListener(new DownloadListener() {

            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(
                        Uri.parse(url));

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); //Notify client once download is completed!
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "Private Browser");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", //To notify the Client that the file is being downloaded
                        Toast.LENGTH_LONG).show();

            }
        });


    }
    //helper method for clearCache() , recursive
//returns number of deleted files
    static int clearCacheFolder(final File dir, final int numDays) {

        int deletedFiles = 0;
        if (dir!= null && dir.isDirectory()) {
            try {
                for (File child:dir.listFiles()) {

                    //first delete subdirectories recursively
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }

                    //then delete the files and subdirectories in this dir
                    //only empty directories can be deleted, so subdirs have been done first
                    if (child.lastModified() < new Date().getTime() - numDays * DateUtils.DAY_IN_MILLIS) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            }
            catch(Exception e) {
                Log.e("tag", String.format("Failed to clean the cache, error %s", e.getMessage()));
            }
        }
        return deletedFiles;
    }

    /*
     * Delete the files older than numDays days from the application cache
     * 0 means all files.
     */
    public static void clearCache(final Context context, final int numDays) {
        Log.i("tag", String.format("Starting cache prune, deleting files older than %d days", numDays));
        int numDeletedFiles = clearCacheFolder(context.getCacheDir(), numDays);
        Log.i("tag", String.format("Cache pruning completed, %d files deleted", numDeletedFiles));
    }

    public void sshowdialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permision is needed to to download anything ")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"turn ON permission for uor app",Toast.LENGTH_LONG).show();
                        //TODO change to our name of app
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent,storagepermissioncode);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create().show();
    }



    public void requeststoragepermisssion()
    {

        //here i am requesting permission
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},storagepermissioncode);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storagepermissioncode)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Click Again for Sending", Toast.LENGTH_SHORT).show();
                permissioncode=2;
            } else {
                sshowdialog();
            }
        }
    }

}
