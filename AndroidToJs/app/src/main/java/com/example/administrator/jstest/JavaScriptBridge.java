package com.example.administrator.jstest;

import android.webkit.JavascriptInterface;


/**
 * Created by WJK on 2016/10/27.
 */
public class JavaScriptBridge {

    private MainActivity context;
    private String urlNow = "";

    public JavaScriptBridge(MainActivity context) {
        this.context = context;
    }

    @JavascriptInterface
    public void functionA(String msg) {
        context.setText("Function A == "+msg);
    }

    @JavascriptInterface
    public void functionB(String msg) {
        context.setText("Function B == "+msg);
    }


    public String getUrlNow() {
        return urlNow;
    }

    public void setUrlNow(String urlNow) {
        this.urlNow = urlNow;
    }

}
