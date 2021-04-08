package com.yin.yzjcourse.Base.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * FragmentStatePagerAdapter的实现类至少重写getItem()和getCount()即可
 */

public class VPStateFragAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public VPStateFragAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
