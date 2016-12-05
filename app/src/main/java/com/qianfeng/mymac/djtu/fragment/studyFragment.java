package com.qianfeng.mymac.djtu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity.NewsContentActivity;
import com.qianfeng.mymac.djtu.adapter.Frag1_GridView_Adapter;
import com.qianfeng.mymac.djtu.adapter.Frag_Vps_Adapter;
import com.qianfeng.mymac.djtu.entityclass.Gridview_Item;
import com.qianfeng.mymac.djtu.entityclass.ViewP_News;
import com.squareup.picasso.Picasso;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class studyFragment extends Fragment {

    private GridView grid_frag1;

    private List<Gridview_Item> gridview_list;
    private Frag1_GridView_Adapter grid_adapter;

    private ViewPager image_pager_frag1;
    private List<ImageView> vps_list;
    private Frag_Vps_Adapter vps_adapter;
    private int currentPosition;

    private TextView title_pager_frag1;

    private LinearLayout dots_pager_frag1;
    private ImageView[] dots;

    private TextView nofi_frag1;
    private TextView news_frag1;

    private boolean isrunning = true;
    private Handler handler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            //让ViewPager滑到下一页
            image_pager_frag1.setCurrentItem(image_pager_frag1.getCurrentItem()+1);
            //延时，循环调用handler
            if(isrunning){
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        }
    };
    public studyFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_study, container, false);
        //
        nofi_frag1 = (TextView) view.findViewById(R.id.nofi_frag1);
        news_frag1 = (TextView) view.findViewById(R.id.news_frag1);

        grid_frag1 = (GridView) view.findViewById(R.id.grid_frag1);
        image_pager_frag1 = (ViewPager) view.findViewById(R.id.image_pager_frag1);
        title_pager_frag1 = (TextView) view.findViewById(R.id.title_pager_frag1);
        dots_pager_frag1 = (LinearLayout) view.findViewById(R.id.dots_pager_frag1);
        //
        init();
        grid_adapter = new Frag1_GridView_Adapter(gridview_list, getActivity());
        grid_frag1.setAdapter(grid_adapter);

        nofi_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        news_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        grid_frag1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        
        return view;
    }

    public void init() {
        // 关于 下半部分
        gridview_list = new ArrayList<>();
        gridview_list.add(new Gridview_Item("早操", R.drawable.icon_exercise_normal));
        gridview_list.add(new Gridview_Item("成绩", R.drawable.icon_professional_normal));
        gridview_list.add(new Gridview_Item("课表", R.drawable.icon_table_normal));
        gridview_list.add(new Gridview_Item("校历", R.drawable.icon_transcript_normal));
        gridview_list.add(new Gridview_Item("添加", R.drawable.icon_transcript_normal));

        //关 于   上半部分
        try {
            InputStream is = getActivity().getAssets().open("viewpager_3json.txt");
            byte[] b = new byte[1024];
            int len = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
                baos.flush();
            }

            String json = baos.toString();
            Gson gson = new Gson();
            ViewP_News news = gson.fromJson(json, ViewP_News.class);
            view_bind(news);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void view_bind(ViewP_News news) {

        List<ViewP_News.DataBean> dataBeanList = news.getData();
        final List<ViewP_News.DataBean.TopicBean> topics = dataBeanList.get(0).getTopic();

        dots = new ImageView[topics.size()];
        currentPosition = 1;
        List<String> list_id = new ArrayList<>();
        for (int i = 0; i < dots.length; i++) {
            String id = topics.get(i).getId();
            list_id.add(id);
            //
            dots[i] = (ImageView) dots_pager_frag1.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);

            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image_pager_frag1.setCurrentItem(Integer.parseInt(v.getTag().toString())+1); // +1
                }
            });
        }

        dots[0].setEnabled(false);
        title_pager_frag1.setText(topics.get(0).getTitle());

        vps_list = new ArrayList<>();
        String[] imageurls = new String[topics.size() + 2];

        Log.i("Topics.size()", "大小：" + topics.size());

        for (int i = 0; i < imageurls.length; i++) {

            imageurls[0] = topics.get(5).getImage();
            imageurls[7] = topics.get(0).getImage();
            if (i>0 && i<7){
            imageurls[i] = topics.get(i-1).getImage();
            }
            ImageView view = new ImageView(getContext());
            Picasso.with(getContext()).load(imageurls[i]).into(view);
            vps_list.add(view);
        }

        vps_adapter = new Frag_Vps_Adapter(vps_list,getContext(),list_id);
        image_pager_frag1.setAdapter(vps_adapter);

        handler.sendEmptyMessageDelayed(0, 5000);


        image_pager_frag1.setCurrentItem(currentPosition);

        image_pager_frag1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (currentPosition == image_pager_frag1.getAdapter().getCount() - 1) {
                        image_pager_frag1.setCurrentItem(1, false);
                    } else if (currentPosition == 0) {
                        image_pager_frag1.setCurrentItem(image_pager_frag1.getAdapter().getCount() - 2, false);
                    }
                    //
                    for (int i = 0; i < dots.length; i++) {
                        dots[i].setEnabled(true);
                    }
                    if (currentPosition<7 && currentPosition>0) {
                        dots[currentPosition-1].setEnabled(false);
                    }else if (currentPosition == 0){
                        dots[5].setEnabled(false);
                    }else if (currentPosition == 7){
                        dots[0].setEnabled(false);
                    }
                    title_pager_frag1.setText(topics.get(currentPosition-1).getTitle());
                    //
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isrunning = false;
    }
}
