package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.qianfeng.mymac.djtu.activity_content.NewsContentActivity;

import java.util.List;

/**
 * Created by mymac on 2016/11/18.
 */

public class Frag_Vps_Adapter extends PagerAdapter {

    private List<ImageView> list;
    private List<String> list_id;
    private Context context;

    public Frag_Vps_Adapter(List<ImageView> list,Context context,List<String> list_id) {
        this.list = list;
        this.context = context;
        this.list_id = list_id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        View viewClick=list.get(position);
        viewClick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            //if(null!=onItemClickListener){onItemClickListener.onClick(position);}
                 //跳转到详情界面
                Intent intent = new Intent(context, NewsContentActivity.class);
                intent.putExtra("id",list_id.get(position-1));
                intent.putExtra("type","1");
                // http://news.djtu.edu.cn/list/detail.asp?m=3&id=60482
                context.startActivity(intent);
                Toast.makeText(context, "点击了:"+position, Toast.LENGTH_SHORT).show();
            }
        });
        container.addView(list.get(position));
        return list.get(position);
    }

}
