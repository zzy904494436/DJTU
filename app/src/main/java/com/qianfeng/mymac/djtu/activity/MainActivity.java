package com.qianfeng.mymac.djtu.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.mymac.djtu.MyApplication;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity_function.DiscoverActivity;
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

    @ViewInject(R.id.ce_button_back)
    private ImageView ce_button_back;
    @ViewInject(R.id.ce_tv_info)
    private TextView ce_tv_info;
    @ViewInject(R.id.ce_tv_about)
    private TextView ce_tv_about;

    private FragmentManager manager;
    private FragmentTransaction transaction;

    private MyApplication app;
    private User user;
    private Boolean state;

    private Fragment s ;
    @Override
    protected void onPostResume() {
        super.onPostResume();
        state = user.getLoginstate();
        if (state) {
            showislogin_main.setText(user.getSname());
        } else {
            showislogin_main.setText("未登陆");
            //12
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //xxxxxxxxx
        x.view().inject(this);

        app = (MyApplication) getApplication();
        user = app.getUser();
        state = user.getLoginstate();
        //改变

        //11

        btn_drawerlayout_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer_main.openDrawer(Gravity.RIGHT);
                }
            });
        showislogin_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state){
                    //对话框
                    //退出 切换账号 取消
                    dialog1_1();
                }else {
                    //未登录
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        //
        Bundle b = new Bundle();
        b.putBoolean("key",state);
        s = new studyFragment();
        s.setArguments(b);
        //
            manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentcontainer_main, s);
        transaction.commit();
            //333
            ce_button_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    drawer_main.closeDrawers();
                }
            });
            ce_tv_info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///
                }
            });
            ce_tv_about.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ///
                }
            });
            radiogroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    transaction = manager.beginTransaction();
                    switch (checkedId) {
                        case R.id.rb1_main:
                            //
                            Bundle b = new Bundle();
                            b.putBoolean("key",state);
                            s = new studyFragment();
                            s.setArguments(b);
                            transaction.replace(R.id.fragmentcontainer_main, s);
                            break;
                        case R.id.rb2_main:
                            transaction.replace(R.id.fragmentcontainer_main, new lifeFragment());
                            break;
                        case R.id.rb3_main:
                            //activity 跳转
                            if (!state){
                                Toast.makeText(MainActivity.this, "请先登录...", Toast.LENGTH_SHORT).show();
                            }else {
                                Intent intentX = new Intent();
                                intentX.setClass(MainActivity.this, DiscoverActivity.class);
                                startActivity(intentX);
                            }
                            break;
                        case R.id.rb4_main:
                            transaction.replace(R.id.fragmentcontainer_main, new campusFragment());
                            break;
                        case R.id.rb5_main:
                            if (state) {

                                transaction.replace(R.id.fragmentcontainer_main, new mineFragment());
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            break;
                    }
                    transaction.commit();
                }
            });


        }
    private void dialog1_1(){
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case Dialog.BUTTON_POSITIVE:
                        Toast.makeText(MainActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                        user.setLoginstate(false);
                        onPostResume();
                        transaction = manager.beginTransaction();
                        transaction.replace(R.id.fragmentcontainer_main, s);
                        transaction.commit();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        //dialog参数设置
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("退出账户"); //设置标题
        builder.setMessage("是否确认退出?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定",dialogOnclicListener);
        builder.setNegativeButton("取消", dialogOnclicListener);
        builder.setNeutralButton("切换账户", dialogOnclicListener);
        builder.create().show();
    }

}

