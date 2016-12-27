package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass2.NewsList;
import com.squareup.picasso.Picasso;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class LvAdapter_news extends BaseAdapter {

    private Context context;
    private List<NewsList.NnlistBean> list;
    private LayoutInflater inflater;

    public LvAdapter_news(Context context, List<NewsList.NnlistBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_news_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        NewsList.NnlistBean bean = list.get(position);
        holder.news_title.setText(bean.getTitle());
        holder.news_content.setText(bean.getContent());
        holder.news_from.setText(bean.getFrom());
        holder.news_time.setText(bean.getTime());
        Picasso.with(context).load(bean.getCover()).into(holder.news_cover);
        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.news_title)TextView news_title;
        @ViewInject(R.id.news_content)TextView news_content;
        @ViewInject(R.id.news_from)TextView news_from;
        @ViewInject(R.id.news_time)TextView news_time;
        @ViewInject(R.id.news_cover)ImageView news_cover;
        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
