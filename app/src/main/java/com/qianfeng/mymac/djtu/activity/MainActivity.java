package com.qianfeng.mymac.djtu.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mymac.djtu.MyApplication;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass.User;
import com.qianfeng.mymac.djtu.fragment.campusFragment;
import com.qianfeng.mymac.djtu.fragment.lifeFragment;
import com.qianfeng.mymac.djtu.fragment.mineFragment;
import com.qianfeng.mymac.djtu.fragment.studyFragment;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.showislogin_main)
    private TextView showislogin_main;
    @ViewInject(R.id.btn_drawerlayout_main)
    private ImageView btn_drawerlayout_main;

    //radiogroup
    @ViewInject(R.id.radiogroup_main)
    private RadioGroup radiogroup_main;
    // 侧滑
    @ViewInject(R.id.activity_main)
    private DrawerLayout drawer_main;

    private FragmentManager manager ;
    private FragmentTransaction transaction;

    private MyApplication app;
    private User user;
    private  String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        x.view().inject(this);

        app = (MyApplication) getApplication();
        user = app.getUser();
        name = user.getSname();

        //11
        if (name!= null){
            showislogin_main.setText(user.getSname()+"已登陆");
        }else {
            showislogin_main.setText("未登陆");
        }

        //12
        btn_drawerlayout_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_main.openDrawer(Gravity.RIGHT);
            }
        });
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentcontainer_main, new studyFragment());
        transaction.commit();
        //333
        radiogroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb1_main:

                        transaction.replace(R.id.fragmentcontainer_main,new studyFragment());
                        break;
                    case R.id.rb2_main:
                        transaction.replace(R.id.fragmentcontainer_main,new lifeFragment());
                        break;
                    case R.id.rb3_main:
                        //activity 跳转
                        Toast.makeText(MainActivity.this, "发现窗体 哦～～还没完成", Toast.LENGTH_SHORT).show();
                        //
                        break;
                    case R.id.rb4_main:
                        transaction.replace(R.id.fragmentcontainer_main,new campusFragment());
                        break;
                    case R.id.rb5_main:
                        if (name != null){
                            transaction.replace(R.id.fragmentcontainer_main,new mineFragment());
                        }else {
                            //PopupWindow popupWindow =new PopupWindow();
                            Toast.makeText(MainActivity.this, "未登陆，请登录", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                transaction.commit();
            }
        });


    }
}
