package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.LvAdapter_Grade;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.GradeList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

public class GradeActivity extends Activity {
    @ViewInject(R.id.spi_chooseterm)
    private Spinner spi_chooseterm;
    @ViewInject(R.id.lv_showfen)
    private ListView lv_showfen;
    @ViewInject(R.id.btn_back_grade)
    private ImageView btn_back_grade;

    private List<GradeList.GradelistBean> list;
    private LvAdapter_Grade adapter_lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        x.view().inject(this);
        //
        // 建立数据源
        String[] mItems = getResources().getStringArray(R.array.term);
        // 建立Adapter并且绑定数据源
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spi_chooseterm.setAdapter(adapter);
        btn_back_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradeActivity.this.finish();
            }
        });
        spi_chooseterm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] terms = getResources().getStringArray(R.array.term);
                //
                String term = terms[position];
                switch (term){
                    case "大一上学期":
                        term = "1";
                        break;
                    case "大一下学期":
                        term = "2";
                        break;
                }
                String url = "http://"+ IpAddress.ip +":8080/CampusApp/gradeshow.action";
                RequestParams request = new RequestParams(url);
                request.addQueryStringParameter("term", term);

                x.http().get(request,
                        new Callback.CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                Gson gson = new Gson();
                                GradeList gradeList = gson.fromJson(result, GradeList.class);
                                list = gradeList.getGradelist();

                                adapter_lv = new LvAdapter_Grade(GradeActivity.this, list);
                                lv_showfen.setAdapter(adapter_lv);
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
