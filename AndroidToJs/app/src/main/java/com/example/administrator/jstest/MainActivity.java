package com.example.administrator.jstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button btn;
    private TextView textView;
    private WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        textView = (TextView) findViewById(R.id.text);
        webView = (WebView) findViewById(R.id.webview);

        settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/test.html");

        test();
    }


    private void test() {

        /**
         * js调用android
         */
        final JavaScriptBridge bridge = new JavaScriptBridge(this);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                bridge.setUrlNow(url);
                return true;
            }
        });
        webView.addJavascriptInterface(bridge, "appNativeJavascriptBridge");


        /**
         * android调用js
         */
        final String str = "hello world";
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javaCallJsFunction('" + str + "')");
            }
        });

    }

    public void setText(String msg) {
        textView.setText(msg);
    }


}
