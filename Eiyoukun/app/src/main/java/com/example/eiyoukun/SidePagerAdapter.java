package com.example.eiyoukun;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;


public abstract class SidePagerAdapter extends PagerAdapter {

    private List<Fragment> fragmentList;

public SidePagerAdapter(FragmentManager fm,List<Fragment> fragmentList){
    super(fm);
    this.fragmentList = fragmentList;
}

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }



}
