package com.qianfeng.mymac.djtu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.activity_content.Discover1_ContentActivity;
import com.qianfeng.mymac.djtu.adapter.ListChoiceAdapter;
import com.qianfeng.mymac.djtu.entityclass.CommonData;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mymac on 2016/12/22.
 */

public class discoverFragment extends Fragment {
    private View view;

    private String url = "";
    private CommonData commonData;
    private List<CommonData.DataBean.ListBean> listBean;
    private ListChoiceAdapter adapter;
    private PullToRefreshListView listView;
    private String nextSign = "";
    private String nextTimer = "";
    private int indexListView = 0;
    private String key;
    private String URL_BEGIN = "http://app.lerays.com/stream/view?";


    public discoverFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null)
            return view;

        Bundle bundle = getArguments();
        key = bundle.getString("cate_list");

        view = inflater.inflate(R.layout.fragment_discover12, container, false);
        listView = (PullToRefreshListView) view.findViewById(R.id.listViewCommon);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listBean = new ArrayList<>();
        url = "http://app.lerays.com/api/stream/list";
        urlUtil();
        //
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url_end = listBean.get(position - 1).getQuery_string();
                Intent intent = new Intent(getContext(), Discover1_ContentActivity.class);

                intent.putExtra("url", URL_BEGIN + url_end + url_end);
                intent.putExtra("title", listBean.get(position - 1).getTitle());
                intent.putExtra("pic", listBean.get(position - 1).getImg_src());
                startActivity(intent);
            }
        });

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                urlUtil();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                nextSign = commonData.getData().getNextsign();
                nextTimer = commonData.getData().getNexttime() + "";

                url = "http://app.lerays.com/api/stream/list?cate_sign="
                        + nextSign
                        + "&cate_list=4&cate_type=cate&pubtime="
                        + nextTimer;
                Log.i("_(:з」∠)_ 下一页：", "=--------" + url);
                indexListView = listBean.size();

                urlUtilRefresh();
            }
        });

        return view;
    }

    //    if ("".equals("新闻")) {
//
//        //oneORtwo.setText(getKey());
//
//        //new MyAsyncTask_2(this).execute(url2);
//    } else {
//
//        //oneORtwo.setText("出版");
//
//        //new MyAsyncTask_1(this).execute(url1);
//    }
    public void urlUtilRefresh() {
        RequestParams requestParams = new RequestParams(url);
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                CommonData datafresh = gson.fromJson(result, CommonData.class);
                listBean.addAll(datafresh.getData().getList());

                adapter = new ListChoiceAdapter(listBean, getActivity());
                listView.setAdapter(adapter);

                listView.getRefreshableView().setSelection(indexListView);
                listView.onRefreshComplete();
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

    public void urlUtil() {
        RequestParams requestParams = new RequestParams(url);
        requestParams.addQueryStringParameter("cate_sign","null");
        requestParams.addQueryStringParameter("cate_list",key);
        requestParams.addQueryStringParameter("cate_type","cate");
        requestParams.addQueryStringParameter("pubtime","0");
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                commonData = gson.fromJson(result, CommonData.class);
                listBean = commonData.getData().getList();
                // Log.i("listBean Info :","+++++++++++"+listBean.get(0).getTitle());
                //
                adapter = new ListChoiceAdapter(listBean, getActivity());
                listView.setAdapter(adapter);

                listView.onRefreshComplete();


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
