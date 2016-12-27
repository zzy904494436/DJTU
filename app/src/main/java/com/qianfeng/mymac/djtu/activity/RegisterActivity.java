package com.qianfeng.mymac.djtu.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.GetRegister;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class RegisterActivity extends Activity {
    @ViewInject(R.id.edi_username)
    private EditText edi_username;
    @ViewInject(R.id.edi_password)
    private EditText edi_password;
    @ViewInject(R.id.edi_name)
    private EditText edi_name;
    @ViewInject(R.id.edi_phone)
    private EditText edi_phone;

    @ViewInject(R.id.btn_regis)
    private Button btn_regis;
    @ViewInject(R.id.btn_cannel)
    private Button btn_cannel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        x.view().inject(this);

        btn_cannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edi_username.setText("");
                edi_password.setText("");
                edi_name.setText("");
                edi_phone.setText("");
                RegisterActivity.this.finish();
            }
        });
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edi_username.getText().toString();
                String password = edi_password.getText().toString();
                String name = edi_name.getText().toString();
                String phone = edi_phone.getText().toString();
                //
                if (~username.length()==10){
                    Toast.makeText(RegisterActivity.this, "学号必须是十位数字", Toast.LENGTH_SHORT).show();
                }else if (username.matches("^1[2-6][0-9]{9}$")){
                    Toast.makeText(RegisterActivity.this, "学号格式不正确", Toast.LENGTH_SHORT).show();
                }else {

                }
                //http://172.18.64.24:8080/CampusApp/userregister.action?username=&password=000&stuNAME=秦欢&stuTEL=18840812345&stuYUAN=外国语学院&stuCLASS=R日语125

                String url = "http://"+ IpAddress.ip +":8080/CampusApp/userregister.action";
                RequestParams request = new RequestParams(url);
                request.addQueryStringParameter("username",username);
                request.addQueryStringParameter("password",password);
                request.addQueryStringParameter("stuNAME",name);
                request.addQueryStringParameter("stuTEL",phone);

                String jie = username.substring(0,2);//届
                String ban = username.substring(7);//班
                String z = "";
                String y = "";
                if (username.substring(5,7)=="30"){
                    z = "计算";
                    y = "理学院";
                }else if(username.substring(5,7)=="40"){
                    z = "设计";
                    y = "艺术学院";
                }else if(username.substring(5,7)=="00"){
                    z = "营销";
                    y = "管理学院";
                }else if(username.substring(5,7)=="80"){
                    z = "日语";
                    y = "外语学院";
                }else {
                    z = "软工";
                    y = "软件学院";
                }
                String classname = "R"+z+" "+jie+"-"+ban+"班";

                request.addQueryStringParameter("stuYUAN",y);
                request.addQueryStringParameter("stuCLASS",classname);

                x.http().get(request,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                GetRegister getRegister = gson.fromJson(result,GetRegister.class);
                                int flag = getRegister.getFlag();
                                switch (flag){
                                    case 1 :
                                        Toast.makeText(RegisterActivity.this, "注册成功，请再次登陆", Toast.LENGTH_SHORT).show();
                                        RegisterActivity.this.finish();
                                        break;
                                    case 3 :
                                        Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                                        edi_password.setText("");
                                        break;
                                    case 4 :
                                        Toast.makeText(RegisterActivity.this, " 用户名已经存在，请重新注册", Toast.LENGTH_SHORT).show();
                                        edi_username.setText("");
                                        edi_password.setText("");
                                        break;
                                    default:
                                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                                        break;
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

    }
}
