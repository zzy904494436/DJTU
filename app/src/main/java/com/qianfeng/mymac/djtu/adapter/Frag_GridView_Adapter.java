package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass.Gridview_Item;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mymac on 2016/11/17.
 */

public class Frag_GridView_Adapter extends BaseAdapter {

    private List<Gridview_Item> list;
    private Context context;

    public Frag_GridView_Adapter(List<Gridview_Item> list, Context context) {
        this.list = list;
        this.context = context;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item_layout_f1,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

            Gridview_Item item = list.get(position);
            holder.text.setText(item.TEXT);
            holder.image.setImageResource(item.ImageId);

        return convertView;
    }

    class ViewHolder {
        @ViewInject(R.id.gridview_item_text) TextView text;
        @ViewInject(R.id.gridview_item_image) ImageView image;

        public ViewHolder(View view) {
            x.view().inject(this,view);
        }
    }
}