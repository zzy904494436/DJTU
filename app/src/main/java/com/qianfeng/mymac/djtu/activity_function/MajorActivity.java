package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.qianfeng.mymac.djtu.R;
import com.squareup.picasso.Picasso;

public class MajorActivity extends Activity {
    private ImageView btn_back_major;
    private ImageView iv_major1;
    private ImageView iv_major2;
    private ImageView iv_major3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_major);

        btn_back_major = (ImageView)findViewById(R.id.btn_back_major);
        iv_major1 = (ImageView)findViewById(R.id.iv_major1);
        iv_major2 = (ImageView)findViewById(R.id.iv_major2);
        iv_major3 = (ImageView)findViewById(R.id.iv_major3);

        btn_back_major.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MajorActivity.this.finish();
            }
        });

        Picasso.with(getApplicationContext()).load(R.mipmap.major1).into(iv_major1);
        Picasso.with(getApplicationContext()).load(R.mipmap.major2).into(iv_major2);
        Picasso.with(getApplicationContext()).load(R.mipmap.major3).into(iv_major3);
    }
}
