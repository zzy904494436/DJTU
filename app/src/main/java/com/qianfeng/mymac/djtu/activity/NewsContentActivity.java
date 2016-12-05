package com.qianfeng.mymac.djtu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.qianfeng.mymac.djtu.R;

public class NewsContentActivity extends AppCompatActivity {

    private WebView news_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        news_web = (WebView) findViewById(R.id.news_web);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String url = "http://news.djtu.edu.cn/list/detail.asp?m=3&id=" + id;

        news_web.setWebViewClient( new WebViewClient());
        news_web.getSettings().setJavaScriptEnabled(true);
        news_web.loadUrl(url);

    }
}
