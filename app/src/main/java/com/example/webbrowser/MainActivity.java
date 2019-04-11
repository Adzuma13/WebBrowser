package com.example.webbrowser;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.browser);
        webView.loadUrl("https://www.google.com/");

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                return super.shouldOverrideUrlLoading(view, request);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    webView.loadUrl(request.getUrl().toString());
                    Button btnBack = findViewById(R.id.btnBack);
                    Button btnForward = findViewById(R.id.btnForward);
                    Button btnReboot = findViewById(R.id.btnReboot);
                    Button btnSearch = findViewById(R.id.btnSearch);
                    pressBack(btnBack);
                    pressForward(btnForward);
                    pressReboot(btnReboot);
//                    pressSearch(btnSearch);
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    public void pressBack(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.isPressed() && webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });
    }

    public void pressForward(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.isPressed() && webView.canGoForward()){
                    webView.goForward();
                }
            }
        });
    }

    public void pressReboot(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button.isPressed()){
                    webView.reload();
                }
            }
        });
    }

//    public void pressSearch(Button button){
//        final EditText urlEditText = findViewById(R.id.editUrl);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = urlEditText.getText().toString();
//                if (editTextURL(url)){
//                    webView.loadUrl(url);
//                    MainActivity.this.progre.setProgress(0);
//                }
//            }
//        });
//    }

    public boolean editTextURL(String url){
        return true;
    }
}


