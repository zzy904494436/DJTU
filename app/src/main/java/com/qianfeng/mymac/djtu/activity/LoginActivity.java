package com.qianfeng.mymac.djtu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.MyApplication;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass.User;
import com.qianfeng.mymac.djtu.entityclass2.GetLogin;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class LoginActivity extends AppCompatActivity {
    @ViewInject(R.id.edi_username)
    private EditText edi_username;
    @ViewInject(R.id.edi_password)
    private EditText edi_password;
    @ViewInject(R.id.tv_register)
    private TextView tv_register;
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    @ViewInject(R.id.btn_cannel)
    private Button btn_cannel;

    private MyApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        x.view().inject(this);

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edi_username.getText().toString();
                String password = edi_password.getText().toString();
                //http://172.18.64.24:8080/CampusApp/userlogin.action?username=1218130123&password=1010
                String url = "http://"+ IpAddress.ip +":8080/CampusApp/userlogin.action";
                RequestParams request = new RequestParams(url);
                request.addQueryStringParameter("username",username);
                request.addQueryStringParameter("password",password);

                x.http().get(request,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                GetLogin getLogin = gson.fromJson(result, GetLogin.class);
                                int flag = getLogin.getFlag();
                                getLogin.getUserInfo().getStuID();
                                if (flag==1){
                                    app = (MyApplication) getApplication();
                                    User user = app.getUser();
                                    user.setLoginstate(true);
                                    user.setSnumber(Integer.parseInt(getLogin.getUserInfo().getStuID()));
                                    user.setSname(getLogin.getUserInfo().getStuNAME());
                                    user.setSphone(getLogin.getUserInfo().getStuTEL());
                                    user.setMajorAndClass(getLogin.getUserInfo().getStuCLASS());
                                    user.setXUEYUAN(getLogin.getUserInfo().getStuYUAN());
                                    app.setUser(user);//已经登陆
                                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                    LoginActivity.this.finish();
                                }else{
                                    //失败
                                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                    edi_password.setText("");
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
        });
        btn_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edi_username.setText("");
                edi_password.setText("");
                LoginActivity.this.finish();
            }
        });


    }
}
