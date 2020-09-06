package com.example.orderfree_user.UI.Payment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.orderfree_user.R;

public class IamportActivity extends AppCompatActivity {
    private WebView mainWebView;
    private final String APP_SCHEME = "iamportkakao://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iamport);

        mainWebView = (WebView) findViewById(R.id.web_iamport);

        mainWebView.setWebViewClient(new KakaoWebViewClient(this));
        WebSettings settings = mainWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setSupportMultipleWindows(true);

        mainWebView.loadUrl("localhost:8080");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        if ( intent != null ) {
            Uri intentData = intent.getData();

            if ( intentData != null ) {
                //카카오페이 인증 후 복귀했을 때 결제 후속조치
                String url = intentData.toString();

                if ( url.startsWith(APP_SCHEME) ) {
                    String path = url.substring(APP_SCHEME.length());
                    if ( "process".equalsIgnoreCase(path) ) {
                        mainWebView.loadUrl("javascript:IMP.communicate({result:'process'})");
                    } else {
                        mainWebView.loadUrl("javascript:IMP.communicate({result:'cancel'})");
                    }
                }
            }
        }

    }
}
