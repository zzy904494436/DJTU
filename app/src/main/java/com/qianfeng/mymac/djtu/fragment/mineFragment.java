package com.qianfeng.mymac.djtu.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mymac.djtu.MyApplication;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity_content.ChangeActivity;
import com.qianfeng.mymac.djtu.entityclass.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class mineFragment extends Fragment {
    private MyApplication app;
    private User user;
    private boolean state;

    private TextView XueYuan_frag5;
    private TextView stuName_frag5;
    private TextView stuID_frag5;
    private TextView class_frag5;
    private TextView tel_frag5;
    //2
    private LinearLayout change_tel;
    private LinearLayout change_pass;

    private Intent intent;
    public mineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);

        app = (MyApplication) getActivity().getApplication();
        user = app.getUser();
        //1
        XueYuan_frag5 = (TextView) view.findViewById(R.id.XueYuan_frag5);
        stuName_frag5 = (TextView) view.findViewById(R.id.stuName_frag5);
        stuID_frag5 = (TextView) view.findViewById(R.id.stuID_frag5);
        class_frag5 = (TextView) view.findViewById(R.id.class_frag5);
        tel_frag5 = (TextView) view.findViewById(R.id.tel_frag5);
        //2
        change_tel = (LinearLayout) view.findViewById(R.id.change_tel);
        change_pass = (LinearLayout) view.findViewById(R.id.change_pass);

        XueYuan_frag5.setText(user.getXUEYUAN());
        stuName_frag5.setText(user.getSname());
        stuID_frag5.setText(user.getSnumber()+"");
        class_frag5.setText(user.getMajorAndClass());
        tel_frag5.setText(user.getSphone());
        //2
        change_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(getActivity(), ChangeActivity.class);
                intent.putExtra("changewhat","手机号");
                startActivity(intent);
            }
        });
        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent();
                intent.setClass(getActivity(), ChangeActivity.class);
                intent.putExtra("changewhat","密码");
                startActivity(intent);
            }
        });

        return view ;
    }

}
