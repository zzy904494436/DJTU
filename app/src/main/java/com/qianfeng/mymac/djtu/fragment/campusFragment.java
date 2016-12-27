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
import com.qianfeng.mymac.djtu.activity_function.ContactActivity;
import com.qianfeng.mymac.djtu.activity_function.IntroActivity;
import com.qianfeng.mymac.djtu.activity_function.LeaderActivity;
import com.qianfeng.mymac.djtu.activity_function.LogoActivity;
import com.qianfeng.mymac.djtu.activity_function.MajorActivity;
import com.qianfeng.mymac.djtu.activity_function.NewsActivity;
import com.qianfeng.mymac.djtu.activity_function.SceneActivity;
import com.qianfeng.mymac.djtu.adapter.Frag_GridView_Adapter;
import com.qianfeng.mymac.djtu.adapter.Frag_Vps_Adapter;
import com.qianfeng.mymac.djtu.entityclass.Gridview_Item;
import com.qianfeng.mymac.djtu.entityclass.ViewP_News;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class campusFragment extends Fragment {
    private GridView grid_frag4;

    private List<Gridview_Item> gridview_list;
    private Frag_GridView_Adapter grid_adapter;

    private ViewPager image_pager_frag4;
    private List<ImageView> vps_list;
    private Frag_Vps_Adapter vps_adapter;
    private int currentPosition;

    private TextView title_pager_frag4;

    private LinearLayout dots_pager_frag4;
    private ImageView[] dots;

    private TextView nofi_frag4;
    private TextView news_frag4;
    private Intent intent;

    private boolean isrunning = true;
    private Handler handler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            //让ViewPager滑到下一页
            image_pager_frag4.setCurrentItem(image_pager_frag4.getCurrentItem()+1);
            //延时，循环调用handler
            if(isrunning){
                handler.sendEmptyMessageDelayed(0, 5000);
            }
        }
    };

    public campusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_campus, container, false);
        //
        nofi_frag4 = (TextView) view.findViewById(R.id.nofi_frag4);
        news_frag4 = (TextView) view.findViewById(R.id.news_frag4);

        grid_frag4 = (GridView) view.findViewById(R.id.grid_frag4);
        image_pager_frag4 = (ViewPager) view.findViewById(R.id.image_pager_frag4);
        title_pager_frag4 = (TextView) view.findViewById(R.id.title_pager_frag4);
        dots_pager_frag4 = (LinearLayout) view.findViewById(R.id.dots_pager_frag4);
        //
        init();
        grid_adapter = new Frag_GridView_Adapter(gridview_list, getActivity());
        grid_frag4.setAdapter(grid_adapter);
        intent = new Intent();
        nofi_frag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type","2");
                intent.setClass(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        news_frag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("type","1");
                intent.setClass(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        grid_frag4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(getContext(), IntroActivity.class);
                        break;
                    case 1:
                        intent.setClass(getContext(), LogoActivity.class);
                        break;
                    case 2:
                        intent.setClass(getContext(), LeaderActivity.class);
                        break;
                    case 3:
                        intent.setClass(getContext(), SceneActivity.class);
                        break;
                    case 4:
                        intent.setClass(getContext(), MajorActivity.class);
                        break;
                    case 5:
                        intent.setClass(getContext(), ContactActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });

        return view;
    }
    public void init() {
        // 关于 下半部分
        gridview_list = new ArrayList<>();
        gridview_list.add(new Gridview_Item("学校简介", R.drawable.icon_introduction_normal));
        gridview_list.add(new Gridview_Item("学校标识", R.drawable.icon_logo_normal));
        gridview_list.add(new Gridview_Item("现任领导", R.drawable.icon_leader_normal));
        gridview_list.add(new Gridview_Item("学校风光", R.drawable.icon_scene_normal));
        gridview_list.add(new Gridview_Item("专业设置", R.drawable.icon_professional_normal));
        gridview_list.add(new Gridview_Item("联系方式", R.drawable.icon_contact_normal));

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
            dots[i] = (ImageView) dots_pager_frag4.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setTag(i);

            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    image_pager_frag4.setCurrentItem(Integer.parseInt(v.getTag().toString())+1); // +1
                }
            });
        }

        dots[0].setEnabled(false);
        title_pager_frag4.setText(topics.get(0).getTitle());

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
        image_pager_frag4.setAdapter(vps_adapter);

        handler.sendEmptyMessageDelayed(0, 5000);


        image_pager_frag4.setCurrentItem(currentPosition);

        image_pager_frag4.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
                    if (currentPosition == image_pager_frag4.getAdapter().getCount() - 1) {
                        image_pager_frag4.setCurrentItem(1, false);
                    } else if (currentPosition == 0) {
                        image_pager_frag4.setCurrentItem(image_pager_frag4.getAdapter().getCount() - 2, false);
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
                    title_pager_frag4.setText(topics.get(currentPosition-1).getTitle());
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
