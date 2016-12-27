package com.qianfeng.mymac.djtu.activity_function;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qianfeng.mymac.djtu.R;
import com.qianfeng.mymac.djtu.adapter.Frag3_MyPagerAdapter;
import com.qianfeng.mymac.djtu.fragment.discoverFragment;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity {

    private TabLayout tabs;

    private List<String> name_list;
    private List<Fragment> frag_list;

    private ViewPager viewpager;
    private Frag3_MyPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        tabs = (TabLayout) findViewById(R.id.tabs);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        frag_list = new ArrayList<>();

        discoverFragment fragment_1 = new discoverFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cate_list","4");
        fragment_1.setArguments(bundle);

        discoverFragment fragment_2 = new discoverFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cate_list","16");
        fragment_2.setArguments(bundle2);

        frag_list.add(fragment_1);
        frag_list.add(fragment_2);

        name_list = new ArrayList<>();
        name_list.add("你发现");
        name_list.add("发现你");

        for (int i = 0; i < name_list.size(); i++) {
            tabs.addTab(tabs.newTab().setText(name_list.get(i)));
        }

        adapter = new Frag3_MyPagerAdapter(getSupportFragmentManager(), frag_list, name_list);
        viewpager.setAdapter(adapter);

        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewpager);
    }
}
