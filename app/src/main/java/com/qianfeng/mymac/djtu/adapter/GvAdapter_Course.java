package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass2.CourseList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class GvAdapter_Course extends BaseAdapter {
    private Context context;
    private List<CourseList.CourselistBean> list;
    private LayoutInflater inflater;

    public GvAdapter_Course(Context context, List<CourseList.CourselistBean> list) {
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
            convertView = inflater.inflate(R.layout.item_course_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        CourseList.CourselistBean bean = list.get(position);
        holder.tv_teacher.setText(bean.getTeacher());
        holder.tv_cname.setText(bean.getCourse());
        holder.tv_place.setText(bean.getPlace());
        return convertView;
    }

        class ViewHolder{
            @ViewInject(R.id.tv_teacher)TextView tv_teacher;
            @ViewInject(R.id.tv_place)TextView tv_place;
            @ViewInject(R.id.tv_cname)TextView tv_cname;
            public ViewHolder(View view){
                x.view().inject(this,view);
            }

    }
}
