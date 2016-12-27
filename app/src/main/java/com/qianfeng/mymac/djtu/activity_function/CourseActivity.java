package com.qianfeng.mymac.djtu.activity_function;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.GvAdapter_Course;
import com.qianfeng.mymac.djtu.adapter.LvAdapter_Contact;
import com.qianfeng.mymac.djtu.entityclass.IpAddress;
import com.qianfeng.mymac.djtu.entityclass2.ContactList;
import com.qianfeng.mymac.djtu.entityclass2.CourseList;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

public class CourseActivity extends Activity {

    private ImageView btn_back_course;
    private GridView class_show;
    private List<CourseList.CourselistBean> list;
    private GvAdapter_Course adapter_gv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        btn_back_course = (ImageView) findViewById(R.id.btn_back_course);
        class_show = (GridView)findViewById(R.id.class_show);
        btn_back_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseActivity.this.finish();
            }
        });
        //
        String url = "http://"+ IpAddress.ip +":8080/CampusApp/courseshow.action";
        RequestParams request = new RequestParams(url);

        x.http().get(request,
                new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Gson gson = new Gson();
                        CourseList courseList = gson.fromJson(result, CourseList.class);
                        list = courseList.getCourselist();
                        adapter_gv = new GvAdapter_Course(CourseActivity.this,list);
                        class_show.setAdapter(adapter_gv);
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
