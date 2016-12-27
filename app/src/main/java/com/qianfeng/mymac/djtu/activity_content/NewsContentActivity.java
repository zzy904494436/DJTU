package com.qianfeng.mymac.djtu.activity_content;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qianfeng.mymac.djtu.R;

public class NewsContentActivity extends AppCompatActivity {

    private WebView news_web;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        news_web = (WebView) findViewById(R.id.news_web);
        Intent intent = getIntent();
        String m = intent.getStringExtra("type");
        String id = intent.getStringExtra("id");
        if (m.equals("1")){
            //top xinwen
            url = "http://news.djtu.edu.cn/list/detail.asp?m=3&id=" + id;
        }else if(m.equals("2")){
            //nofi
            url = "http://news.djtu.edu.cn/list/detail.asp?m=4&id=" + id;
        }else {
            url = id;
        }

        news_web.setWebViewClient( new WebViewClient());
        news_web.getSettings().setJavaScriptEnabled(true);
        news_web.loadUrl(url);
    }
}
