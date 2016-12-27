package com.qianfeng.mymac.djtu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass2.GradeList;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;
import java.util.List;

/**
 * Created by mymac on 2016/12/24.
 */

public class LvAdapter_Grade extends BaseAdapter {
    private Context context;
    private List<GradeList.GradelistBean> list;
    private LayoutInflater inflater;

    public LvAdapter_Grade(Context context, List<GradeList.GradelistBean> list) {
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
            convertView = inflater.inflate(R.layout.item_grade_layout,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        GradeList.GradelistBean bean = list.get(position);
        holder.tv_class.setText(bean.getCource());
        holder.tv_grade.setText(bean.getGrade());
        int fen = Integer.parseInt(bean.getGrade());
        String ping = "";
        if (fen>90){
            ping = "优秀";
        }else if (fen>80){
            ping = "良好";
        }else if (fen>70){
            ping = "中等";
        }else if (fen>60){
            ping = "及格";
        }else {
            ping = "不及格";
        }
        holder.tv_ping.setText(ping);
        return convertView;
    }
    class ViewHolder{
        @ViewInject(R.id.tv_class) TextView tv_class;
        @ViewInject(R.id.tv_grade)TextView tv_grade;
        @ViewInject(R.id.tv_ping)TextView tv_ping;
        public ViewHolder(View view){
            x.view().inject(this,view);
        }
    }
}
