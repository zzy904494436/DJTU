package com.qianfeng.mymac.djtu.activity_content;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.MyApplication;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity.LoginActivity;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass.User;
import com.qianfeng.mymac.djtu.entityclass2.GetLogin;
import com.qianfeng.mymac.djtu.entityclass2.getchange1;
import com.qianfeng.mymac.djtu.entityclass2.getchange2;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class ChangeActivity extends Activity {
    @ViewInject(R.id.btn_back_pass)
    private ImageView btn_back_pass;
    @ViewInject(R.id.change_title)
    private TextView change_title;
    @ViewInject(R.id.iv_change1)
    private ImageView iv_change1;
    @ViewInject(R.id.iv_change2)
    private ImageView iv_change2;
    @ViewInject(R.id.iv_change3)
    private ImageView iv_change3;
    @ViewInject(R.id.change_text1)
    private TextView change_text1;
    @ViewInject(R.id.change_text2)
    private TextView change_text2;
    @ViewInject(R.id.change_text3)
    private TextView change_text3;

    @ViewInject(R.id.change_old)
    private EditText change_old;
    @ViewInject(R.id.change_new1)
    private EditText change_new1;
    @ViewInject(R.id.change_new2)
    private EditText change_new2;
    @ViewInject(R.id.btn_change)
    private Button btn_change;

    private Intent intent;
    private  MyApplication app;
    private User user;
    private String username ;
    private String what;
    public static  boolean judge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        x.view().inject(this);

        app = (MyApplication) getApplication();
        user = app.getUser();
        username = user.getSnumber()+"";
        Log.i("USERNAME",username);

        intent = getIntent();
         what = intent.getStringExtra("changewhat");
        change_title.setText(what);

        btn_back_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeActivity.this.finish();
            }
        });
        switch (what){
            case "手机号":
                iv_change1.setImageResource(R.mipmap.icon_passwd);
                iv_change2.setImageResource(R.drawable.icon_phone);
                iv_change3.setImageResource(R.drawable.icon_phone);
                change_text1.setText("密码");
                change_text2.setText("原手机号");
                change_text3.setText("新手机号");
                change_new1.setText(user.getSphone());
                change_new1.setFocusable(false);
                change_new1.setEnabled(false);
                break;
            case "密码":
                iv_change1.setImageResource(R.mipmap.icon_passwd);
                iv_change2.setImageResource(R.mipmap.icon_passwd);
                iv_change3.setImageResource(R.mipmap.icon_passwd);
                change_text1.setText("旧密码");
                change_text2.setText("新密码");
                change_text3.setText("新密码");
                break;
        }
        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String t1 =  change_old.getText().toString();
               String t3 =  change_new2.getText().toString();
                switch (what){
                    case "密码":
                        String t2 =  change_new1.getText().toString();
                        password(t1,t2,t3);
                        break;
                    case "手机号":
                       telphone(t1,t3);
                        break;
                }

            }
        });

    }

    private void telphone(String t1, final String t3) {
        String url = "http://"+ IpAddress.ip +":8080/CampusApp/userlogin.action";
        RequestParams request = new RequestParams(url);
        request.addQueryStringParameter("username",username);
        request.addQueryStringParameter("password",t1);

        x.http().get(request,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        GetLogin getLogin = gson.fromJson(result, GetLogin.class);
                        int flag = getLogin.getFlag();
                        Log.i("flag.....",flag+""+flag);
                        if (flag == 1){

                            String url = "http://"+ IpAddress.ip +":8080/CampusApp/phoneupdate.action";
                            RequestParams request = new RequestParams(url);
                            request.addQueryStringParameter("username",username);
                            request.addQueryStringParameter("stuTEL",t3);

                            x.http().get(request,
                                    new Callback.CommonCallback<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            Gson gson = new Gson();
                                            getchange2 gc2 = gson.fromJson(result, getchange2.class);
                                            int flag = gc2.getFlag();
                                            if (flag==1){
                                                Toast.makeText(ChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                ChangeActivity.this.finish();
                                            }else if (flag == 2){
                                                Toast.makeText(ChangeActivity.this, "手机号不能跟之前相同", Toast.LENGTH_SHORT).show();
                                                change_new2.setText("");
                                            }else {
                                                Toast.makeText(ChangeActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {

                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {

                                        }

                                        @Override
                                        public void onFinished() {

                                        }
                                    });


                        }else{
                            Toast.makeText(ChangeActivity.this, "密码错误,无法修改", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                    }

                });
        //
    }

    private void password(String t1, final String t2, final String t3) {
        final String url = "http://"+ IpAddress.ip +":8080/CampusApp/userlogin.action";
        RequestParams request = new RequestParams(url);
        request.addQueryStringParameter("username",username);
        request.addQueryStringParameter("password",t1);

        x.http().get(request,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        GetLogin getLogin = gson.fromJson(result, GetLogin.class);
                        int flag = getLogin.getFlag();
                        Log.i("flag.....",flag+""+flag);
                        if (flag == 1){
                            if (t2.equals(t3)){

                            }else {

                            }
                            String url = "http://"+ IpAddress.ip +":8080/CampusApp/changepass.action";
                            RequestParams request = new RequestParams(url);
                            request.addQueryStringParameter("username",username);
                            request.addQueryStringParameter("password",t3);

                            x.http().get(request,
                                    new Callback.CommonCallback<String>() {
                                        @Override
                                        public void onSuccess(String result) {
                                            Gson gson = new Gson();
                                            getchange1 gc1 = gson.fromJson(result, getchange1.class);
                                            int flag = gc1.getFlag();
                                            if (flag==1){
                                                Toast.makeText(ChangeActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                                ChangeActivity.this.finish();
                                            }else if (flag == 2){
                                                Toast.makeText(ChangeActivity.this, "密码不能与之前相同", Toast.LENGTH_SHORT).show();
                                                change_new1.setText("");
                                                change_new2.setText("");
                                            }else {
                                                Toast.makeText(ChangeActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        @Override
                                        public void onError(Throwable ex, boolean isOnCallback) {

                                        }

                                        @Override
                                        public void onCancelled(CancelledException cex) {

                                        }

                                        @Override
                                        public void onFinished() {

                                        }
                                    });


                        }else{
                            Toast.makeText(ChangeActivity.this, "原密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                    }

                });
    }

}
