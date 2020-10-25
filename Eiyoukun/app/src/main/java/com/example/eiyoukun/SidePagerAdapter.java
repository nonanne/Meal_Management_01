package com.example.eiyoukun;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;


public class SidePagerAdapter extends FragmentStatePagerAdapter {

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
