package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.squareup.picasso.Picasso;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MapActivity extends Activity {
    private ImageView iv_map;
    private ImageView btn_back_map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        iv_map = (ImageView)findViewById(R.id.iv_map);
        btn_back_map = (ImageView)findViewById(R.id.btn_back_map);

        btn_back_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapActivity.this.finish();
            }
        });
        String url = "http://www.djtu.edu.cn/_uploadimg/plane_b.jpg";
        Picasso.with(getApplicationContext()).load(url).resize(1500,1000).into(iv_map);
    }
}
