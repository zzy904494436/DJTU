package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.mymac.djtu.R;

public class LeaderActivity extends Activity {
    private ImageView btn_back_lead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        btn_back_lead = (ImageView) findViewById(R.id.btn_back_lead);
        btn_back_lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeaderActivity.this.finish();
            }
        });
    }
}
