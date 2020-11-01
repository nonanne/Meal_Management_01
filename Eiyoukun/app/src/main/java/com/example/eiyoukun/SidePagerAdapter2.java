package com.example.eiyoukun;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.eiyoukun.calenderFragments.thirdCalenderFragment;
import com.example.eiyoukun.calenderFragments.thirdWeightFragment;

public class SidePagerAdapter2 extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    public SidePagerAdapter2(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.mNoOfTabs = NumberOfTabs;
        return;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                thirdWeightFragment tab1 = new thirdWeightFragment();
                return tab1;
            case 1:
                thirdCalenderFragment tab2 = new thirdCalenderFragment();
                return tab2;
            default:
        return null;
    }

}
    @Override
    public int getCount() {
        return mNoOfTabs;
    }
}
