package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity_content.NewsContentActivity;
import com.qianfeng.mymac.djtu.adapter.LvAdapter_news;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.NewsList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class NewsActivity extends Activity {

    private ImageView btn_back_news;
    private ListView lv_news;
    private TextView show_bar;
    //http://172.18.64.24:8080/CampusApp/nonewshow.action?type=1 2
    private List<NewsList.NnlistBean> list; //
    private LvAdapter_news adapter_news;

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        // type = ?
        intent = getIntent();
        final String type = intent.getStringExtra("type");

        btn_back_news = (ImageView) findViewById(R.id.btn_back_news);
        show_bar = (TextView)findViewById(R.id.show_bar);
        lv_news = (ListView) findViewById(R.id.lv_news);////////

        btn_back_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsActivity.this.finish();
            }
        });
        if (type.equals("1")){
            show_bar.setText("通  知");
        }else{
            show_bar.setText("新  闻");
        }

        String url = "http://"+ IpAddress.ip +":8080/CampusApp/nonewshow.action";
        RequestParams request = new RequestParams(url);
        request.addQueryStringParameter("type",type);

        x.http().get(request,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        NewsList newsList = gson.fromJson(result, NewsList.class);
                        list = newsList.getNnlist();
                        adapter_news = new LvAdapter_news(NewsActivity.this, list);
                        lv_news.setAdapter(adapter_news);
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
        lv_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mId = list.get(position).getUrl().toString();
                int judge = list.get(position).getId();
                Intent intentx = new Intent();
                intentx.setClass(NewsActivity.this, NewsContentActivity.class);
                if (judge==201){
                    // 201
                    intentx.putExtra("type","0");
                }else{
                    intentx.putExtra("type",type); //1 新闻 m=3 //2通知 m=4
                }
                intentx.putExtra("id",mId);
                startActivity(intentx);
            }
        });
    }
}
