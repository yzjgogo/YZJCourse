package com.yin.yzjcourse.Base.adapter;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.Utils;

import java.util.List;


public class VPTransAdapter extends PagerAdapter {
    private List<View> views;
    private Context context;

    public VPTransAdapter(List<View> views, Context context) {
        this.views = views;
        this.context = context;
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
        View view = views.get(position);
        ViewGroup.LayoutParams layoutParams =
                new ViewGroup.LayoutParams(Utils.dip2px(context,200),Utils.dip2px(context,400));
        view.setLayoutParams(layoutParams);
        container.addView(view);
        return view;//直接返回当前实例化出来的view作为key
    }
}
