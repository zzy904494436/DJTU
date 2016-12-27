package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.LvAdapter_Contact;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.ContactList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class ContactActivity extends Activity {

    //http://172.18.64.24:8080/CampusApp/contactshow.action?dep=理学院
    @ViewInject(R.id.spi_chooseDep)
    private Spinner spi_chooseDep;
    @ViewInject(R.id.lv_show)
    private ListView lv_show;
    @ViewInject(R.id.btn_back_contact)
    private ImageView btn_back_contact;

    private List<ContactList.ContactlistBean> list;
    private LvAdapter_Contact adapter_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        x.view().inject(this);
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.dep);
        // 建立Adapter并且绑定数据源
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spi_chooseDep.setAdapter(adapter);
        btn_back_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactActivity.this.finish();
            }
        });
        spi_chooseDep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] deps = getResources().getStringArray(R.array.dep);
                Toast.makeText(ContactActivity.this, "你点击的是:" + deps[position], Toast.LENGTH_LONG).show();
                //
                String dep = deps[position];
                String url = "http://"+ IpAddress.ip +":8080/CampusApp/contactshow.action";
                RequestParams request = new RequestParams(url);
                request.addQueryStringParameter("dep", dep);

                x.http().get(request,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                ContactList contactList = gson.fromJson(result, ContactList.class);
                                list = contactList.getContactlist();
                                adapter_lv = new LvAdapter_Contact(ContactActivity.this, list);
                                lv_show.setAdapter(adapter_lv);
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

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
