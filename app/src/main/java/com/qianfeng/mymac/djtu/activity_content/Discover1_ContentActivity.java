package com.qianfeng.mymac.djtu.activity_content;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mymac.djtu.R;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Discover1_ContentActivity extends Activity {

    @ViewInject(R.id.webViewFirst)WebView webView;
    @ViewInject(R.id.webPic)ImageView webPic;
    @ViewInject(R.id.shareToSina)RelativeLayout shareToSina;
    @ViewInject(R.id.webViewErro)TextView webViewErro;
    //,@ViewInject(R.id.scrollViewWeb)ScrollView scrollViewWeb;

    private String url = "";
    private String urlPic = "";
    private String title = "";
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover1__content);

        x.view().inject(this);
        webViewErro.setVisibility(View.GONE);
        //
        //overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
        //
        Intent intent = getIntent();

        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        webView.getSettings().setJavaScriptEnabled(true);
        //webview如果有错误的话_(:з」∠)_：
        //webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //alertDialog.dismiss();
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //alertDialog.dismiss();
                super.onPageFinished(view, url);

            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                webViewErro.setVisibility(View.VISIBLE);
                webViewErro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        webViewErro.setVisibility(View.GONE);
                        webView.loadUrl(url);
                    }
                });
            }
        });
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(url);
        //动画效果
        TranslateAnimation translate =
                new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF,0f,
                        Animation.RELATIVE_TO_SELF,0f,
                        Animation.RELATIVE_TO_SELF,0f,
                        Animation.RELATIVE_TO_SELF,-1f
                );
        translate.setDuration(2500);
        translate.setFillAfter(true);
        webPic.setAnimation(translate);
        //
        //


        urlPic = intent.getStringExtra("pic");
        Picasso.with(this).load(urlPic).into(webPic);
        //新浪分享
    }

    public void getPicBitMap(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                URLConnection con;
                try {
                    URL url = new URL(urlPic);
                    con = url.openConnection();
                    con.connect();
                    con.setConnectTimeout(5000);
                    if (con.getConnectTimeout()<2000){
                        InputStream is = con.getInputStream();
                        bitmap = BitmapFactory.decodeStream(is);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
