package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.mymac.djtu.R;

public class    BusActivity extends Activity {
    private ImageView btn_back_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        btn_back_bus = (ImageView) findViewById(R.id.btn_back_bus);
        btn_back_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusActivity.this.finish();
            }
        });
    }


}
