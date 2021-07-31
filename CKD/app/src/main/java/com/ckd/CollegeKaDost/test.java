package com.ckd.CollegeKaDost;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_test);
        WebView webView=findViewById(R.id.webView);
        final ProgressBar progressBar=findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);

        String url ="https://www.tutorialspoint.com/java/java_tutorial.pdf";
        String finalURL="http://drive.google.com/viewerng/viewer?embedded=true&url="+url;

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        webView.setWebChromeClient(new WebChromeClient(){
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Objects.requireNonNull(getSupportActionBar()).setTitle("Loading..");
                if(newProgress==100)
                {
                    progressBar.setVisibility(View.GONE);
                    getSupportActionBar().setTitle(R.string.app_name);          }
            }
        });

        webView.loadUrl(finalURL);
    }
}