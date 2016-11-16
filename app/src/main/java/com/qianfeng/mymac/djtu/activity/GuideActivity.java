package com.qianfeng.mymac.djtu.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.Guide_PagerAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    //滑动图片
    private ViewPager viewPager;
    private List<ImageView> list_image;
    private int[] image_arr = {R.mipmap.guide_one,R.mipmap.guide_two,R.mipmap.guide_three};
    private Guide_PagerAdapter adapter;
    private EdgeEffectCompat leftEdge;
    private EdgeEffectCompat rightEdge;
    //小白点
    private ImageView[] dots;
    private LinearLayout idot_layout;
    //按钮
    private Button btn_intent;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        viewPager = (ViewPager) findViewById(R.id.Guide_viewPager);
        try {
            Field leftEdgeField = viewPager.getClass().getDeclaredField("mLeftEdge");
            Field rightEdgeField = viewPager.getClass().getDeclaredField("mRightEdge");
            if (leftEdgeField != null && rightEdgeField != null) {
                leftEdgeField.setAccessible(true);
                rightEdgeField.setAccessible(true);
                leftEdge = (EdgeEffectCompat) leftEdgeField.get(viewPager);
                rightEdge = (EdgeEffectCompat) rightEdgeField.get(viewPager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        idot_layout = (LinearLayout) findViewById(R.id.idot_layout);

        dots = new ImageView[image_arr.length];

        btn_intent = (Button) findViewById(R.id.btn_intent);
        intent = new Intent();

        for (int i=0;i<dots.length;i++){
            dots[i] = (ImageView) idot_layout.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);

            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager.setCurrentItem(Integer.parseInt(v.getTag().toString()));
                }
            });

        }

        dots[0].setEnabled(false);

        list_image = new ArrayList<>();
        for (int j= 0;j<image_arr.length;j++){
            ImageView view = new ImageView(this);
            view.setImageResource(image_arr[j]);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            list_image.add(view);
        }

        adapter = new Guide_PagerAdapter(list_image);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.i("TAG","position is : "+ position);
                Log.i("TAG","positionOffset is : "+ positionOffset);
                Log.i("TAG","positionOffsetPixels is : "+ positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                for (int k = 0;k<dots.length;k++){
                    dots[k].setEnabled(true);
                }
                dots[position].setEnabled(false);

                if (position==image_arr.length-1){
                    btn_intent.setVisibility(btn_intent.VISIBLE);
                }else {
                    btn_intent.setVisibility(btn_intent.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(rightEdge!=null&&!rightEdge.isFinished()){
                    //到了最后一张并且还继续拖动，出现蓝色限制边条了

                    startActivity(new Intent(GuideActivity.this, MainActivity.class));
                    GuideActivity.this.finish();
                }
            }
        });

        btn_intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.setClass(GuideActivity.this,MainActivity.class);
                startActivity(intent);
                GuideActivity.this.finish();

            }
        });

    }
}
