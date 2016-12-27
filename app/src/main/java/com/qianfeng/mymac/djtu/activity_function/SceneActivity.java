package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.mymac.djtu.R;

public class SceneActivity extends Activity {

    private ImageView btn_back_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scene);
        btn_back_s = (ImageView) findViewById(R.id.btn_back_s);
        btn_back_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SceneActivity.this.finish();
            }
        });
    }
}
