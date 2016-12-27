package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.mymac.djtu.R;

public class LogoActivity extends Activity {
    private ImageView btn_back_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        btn_back_logo = (ImageView) findViewById(R.id.btn_back_logo);
        btn_back_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoActivity.this.finish();
            }
        });
    }
}
