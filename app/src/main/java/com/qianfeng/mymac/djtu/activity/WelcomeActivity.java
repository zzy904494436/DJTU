package com.qianfeng.mymac.djtu.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.qianfeng.mymac.djtu.R;

public class WelcomeActivity extends AppCompatActivity {

    private SharedPreferences sp ;
    private Intent intent;
    //倒计时
    private MyCountDownTimer mc;
    private TextView time_welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sp = getSharedPreferences("Welcome", Context.MODE_PRIVATE);
        intent = new Intent();

        String msg = sp.getString("isFirst","未登陆过");
        switch (msg){
            case "未登陆过":
                Editor editor =  sp.edit();
                editor.putString("isFirst","登陆过");
                editor.commit();
                intent.setClass(WelcomeActivity.this,GuideActivity.class);
                break;
            case "登陆过":
                intent.setClass(WelcomeActivity.this,MainActivity.class);
                break;
        }
        time_welcome = (TextView) findViewById(R.id.time_welcome);
        mc = new MyCountDownTimer(4000, 1000);
        mc.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },4000);

    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         *
         * @param millisInFuture
         * 表示以毫秒为单位 倒计时的总数
         *
         *
         * 例如 millisInFuture=1000 表示1秒
         * @param countDownInterval
         * 表示 间隔 多少微秒 调用一次 onTick 方法
         *
         *
         * 例如: countDownInterval =1000 ; 表示每1000毫秒调用一次onTick()
         *
         */
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        public void onFinish() {
            time_welcome.setText("正在跳转");
        }
        public void onTick(long millisUntilFinished) {
            time_welcome.setText("倒计时：" + millisUntilFinished / 1000 + "秒");
        }
    }
}
