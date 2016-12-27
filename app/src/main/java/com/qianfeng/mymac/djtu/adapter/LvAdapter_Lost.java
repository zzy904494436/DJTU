package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass2.LostList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class LvAdapter_Lost extends BaseAdapter {
    private Context context;
    private List<LostList.GoodlistBean> list;
    private LayoutInflater inflater;

    public LvAdapter_Lost(Context context, List<LostList.GoodlistBean> list) {
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
            convertView = inflater.inflate(R.layout.item_layout_lost,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        LostList.GoodlistBean bean = list.get(position);
        holder.lost_good.setText(bean.getGoods());
        holder.lost_time.setText(bean.getTime());
        holder.lost_place.setText(bean.getPlace());
        holder.lost_from.setText(bean.getFrom());
        holder.lost_contact.setText(bean.getTel());
        return convertView;
    }

    class ViewHolder{
        @ViewInject(R.id.lost_good)
        TextView lost_good;
        @ViewInject(R.id.lost_time)
        TextView lost_time;
        @ViewInject(R.id.lost_place)
        TextView lost_place;
        @ViewInject(R.id.lost_from)
        TextView lost_from;
        @ViewInject(R.id.lost_contact)
        TextView lost_contact;
        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
