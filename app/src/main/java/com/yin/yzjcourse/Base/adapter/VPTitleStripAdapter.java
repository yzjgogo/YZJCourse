package com.yin.yzjcourse.Base.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class VPTitleStripAdapter extends PagerAdapter {
    private List<View> views;
    private List<String> titles;

    public VPTitleStripAdapter(List<View> views, List<String> titles) {
        this.views = views;
        this.titles = titles;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    /**
     * 就是在这里设置各个page的标题的
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
