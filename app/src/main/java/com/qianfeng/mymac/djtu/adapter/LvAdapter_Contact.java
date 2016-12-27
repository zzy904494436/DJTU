package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass2.ContactList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by mymac on 2016/12/24.
 */

public class LvAdapter_Contact extends BaseAdapter {
    private Context context;
    private List<ContactList.ContactlistBean> list;
    private LayoutInflater inflater;

    public LvAdapter_Contact(Context context, List<ContactList.ContactlistBean> list) {
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
            convertView = inflater.inflate(R.layout.item_contact_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ContactList.ContactlistBean bean = list.get(position);
        holder.tv_dep.setText(bean.getDep());
        holder.tv_room.setText(bean.getRoom());
        holder.tv_tel.setText(bean.getTel());
        return convertView;
    }
     class ViewHolder{
        @ViewInject(R.id.tv_dep)TextView tv_dep;
        @ViewInject(R.id.tv_room)TextView tv_room;
        @ViewInject(R.id.tv_tel)TextView tv_tel;
        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
