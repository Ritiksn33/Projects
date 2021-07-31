package com.ckd.CollegeKaDost;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import static com.ckd.CollegeKaDost.Downloads.pdf_name;


import java.io.File;

public class pdfs extends AppCompatActivity {

    private static final int PERMISSION_STORAGE_CODE = 1000;
    private long downloadid;
    SharedPreferences sharedPreferences;

    PDFView book;
    Boolean delete = true;
    String todelete = "new.pdx"; //Set it same as the file name so that it can be deleted
    ProgressBar bar;
    String pathname = "/sdcard/Android/data/com.ckd.CollegeKaDost/files/" + pdf_name;
    Button savefile;
    Button night;
    View nightmode;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_pdfs);

        night = findViewById(R.id.night);
        nightmode = findViewById(R.id.nightmode);

        nightmode.setBackground(Drawable.createFromPath("#00FD7E3A"));



        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(night.getText()=="Study Mode")
                {
                    nightmode.setBackgroundColor(Color.parseColor("#00FD7E3A"));
                    night.setText("Normal Mode");
                }
                else
                {
                    nightmode.setBackgroundColor(Color.parseColor("#30FD7E3A"));
                    night.setText("Study Mode");
                }
            }
        });





        sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();






        savefile = findViewById(R.id.savefile);

        savefile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = (String) savefile.getText();

                if(name.equals("DELETE")) {
                    savefile.setText("save");
                    editor.putString("test", "book1");
                    editor.commit();
                    delete = true;

                    Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();


                }
                else
                {
                    savefile.setText("DELETE");
                    editor.clear();
                    editor.commit();
                    delete = false;

                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

                }

            }
        });

        File n = new File("/sdcard/Android/data/com.ckd.CollegeKaDost/files");

        if(!n.exists())
        {
            n.mkdirs();
        }

        File x = new File(pathname);
        if(x.exists()) {
            delete = false;
            book = findViewById(R.id.pdf);
            bar = findViewById(R.id.bar);
            savefile = findViewById(R.id.savefile);
            savefile.setTextColor(Color.parseColor("#FF51D000"));
            savefile.setText("DELETE");
            String c = "file:///" + x.getAbsolutePath();
            bar.setVisibility(View.GONE);
            book.fromUri(Uri.parse(c)).load();

        }
        else {

            savefile = findViewById(R.id.savefile);
            savefile.setTextColor(Color.parseColor("#D81B60"));
            savefile.setText("SAVE");


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission dined
                    String[] premissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(premissions, PERMISSION_STORAGE_CODE);
                } else {
                    startdownloading();


                }
            } else {
                startdownloading();

            }


            IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            registerReceiver(new BroadcastReceiver() {
                @SuppressLint("SdCardPath")
                @Override
                public void onReceive(Context context, Intent intent) {

                    long broadcastDownlaodId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    if (broadcastDownlaodId == downloadid) {
                        if (getDownloadStatus() == DownloadManager.STATUS_SUCCESSFUL) {
                            book = (PDFView) findViewById(R.id.pdf);
                            bar = findViewById(R.id.bar);



                            File path = new File(pathname);

                            bar.setVisibility(View.GONE);
                            String c = "file:///" + path.getAbsolutePath();

                            //Even if you are not giving the extension of file it will work
                            book.fromUri(Uri.parse(c)).load();

                            startService(new Intent(getBaseContext(), ExitService.class));
                            book.isSaveEnabled();


                        }


                    }


                }
            }, filter);


        }
    }

    public void startdownloading()
    {
        //the url must be modified according to the directory of database
        String url = "https://education.github.com/git-cheat-sheet-education.pdf";
        //bar.setVisibility(View.VISIBLE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI|  DownloadManager.Request.NETWORK_MOBILE);
        request.allowScanningByMediaScanner();



        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.Q)
        {

            request.setDestinationInExternalPublicDir("/Android/data/com.ckd.CollegeKaDost/files", pdf_name);
        }
        else
        {
            @SuppressLint("SdCardPath") File path = new File(pathname);

            String u = "file:///" + path.getAbsolutePath();

            request.setDestinationUri(Uri.parse(u));
        }



        DownloadManager manger = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);


        assert manger != null;
        downloadid = manger.enqueue(request);



    }



    private int getDownloadStatus()
    {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadid);


        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(query);


        if(cursor.moveToFirst())
        {
            int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
            int status = cursor.getInt(columnIndex);


            return status;

        }

        return DownloadManager.ERROR_UNKNOWN;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();



        if(delete)
        {
            File fdelete = new File( pathname);
            if(fdelete.exists())
            {
                fdelete.delete();
            }

        }

    }


    public void save(View view)
    {
        delete = false;
        savefile = findViewById(R.id.savefile);
        savefile.setTextColor(Color.parseColor("#FF51D000"));
        savefile.setText("SAVED");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_STORAGE_CODE:
            {
                if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    startdownloading();
                }
            }
        }
    }




}
