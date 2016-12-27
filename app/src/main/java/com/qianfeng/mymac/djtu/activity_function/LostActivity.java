package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.LvAdapter_Lost;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.LostList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class LostActivity extends Activity {

    private ImageView btn_back_lost;
    private ListView lv_lost;

    private List<LostList.GoodlistBean> list;
    private LvAdapter_Lost adapter_lost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);

        btn_back_lost = (ImageView) findViewById(R.id.btn_back_lost);
        lv_lost = (ListView) findViewById(R.id.lv_lost);
        btn_back_lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LostActivity.this.finish();
            }
        });
        //
        String url = "http://"+ IpAddress.ip +":8080/CampusApp/goodshow.action";
        //String url2 = "http://"+"get()"+":8080/CampusApp/goodshow.action";
        RequestParams request = new RequestParams(url);

        x.http().get(request,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        LostList lostList = gson.fromJson(result, LostList.class);
                        list = lostList.getGoodlist();
                        adapter_lost = new LvAdapter_Lost(LostActivity.this, list);
                        lv_lost.setAdapter(adapter_lost);
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
