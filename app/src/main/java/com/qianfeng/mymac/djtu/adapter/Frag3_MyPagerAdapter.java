package com.qianfeng.mymac.djtu.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by mymac on 2016/12/22.
 */

public class Frag3_MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> frag_list;
    private List<String> name_list;

    public Frag3_MyPagerAdapter(FragmentManager fm, List<Fragment> frag_list, List<String> name_list) {
        super(fm);
        this.frag_list = frag_list;
        this.name_list = name_list;
    }

    @Override
    public Fragment getItem(int position) {
        return frag_list.get(position);
    }

    @Override
    public int getCount() {
        return frag_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return name_list.get(position%name_list.size());
    }
}
