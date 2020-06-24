package com.yin.yzjcourse.Base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.utils.DLog;

import java.util.List;

/**
 * 从instantiateItem方法的注释看起
 */

public class VPBaseAdapter extends PagerAdapter {
    private List<View> views;

    public VPBaseAdapter(List<View> views) {
        this.views = views;
    }

//    @Override
//    public void setPrimaryItem(@NonNull View container, int position, @NonNull Object object) {
//        super.setPrimaryItem(container, position, object);
//    }

    /**
     * 切换页面时，position就是当前页的位置
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    /**
     *
     * @param view
     * @param object 就是instantiateItem方法返回的key
     * @return 返回true时，ViewPager才会显示出来各个page，因此要注意逻辑判断
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        DLog.eLog("执行isViewFromObject");
        return view == object;//instantiateItem直接返回实例化出来的view作为key的处理方法
//        return view == views.get((int)Integer.parseInt(object.toString()));
        //instantiateItem返回实例化出来的view的位置作为key的处理方法
    }

    @Override
    public int getCount() {
        DLog.eLog("执行getCount");
        return views.size();
    }

    /**
     *
     * @param container
     * @param position
     * @param object 就是instantiateItem方法返回的key
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        DLog.eLog("执行destroyItem");
        container.removeView(views.get(position));
    }

    /**
     * @param container 存放各个page的父容器
     * @param position 要实例化的位置
     * @return 其实可以返回一个任意对象，这个对象可以理解为一个key，
     * 这个key唯一对应着这个实例化出来的page，因为ViewPager并不直接与每一个
     * page打交道，而是通过这个key来操作对应的page。
     * 因此我们的返回值要有逻辑意义
     *
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        DLog.eLog("执行instantiateItem");
        container.addView(views.get(position));
        return views.get(position);//直接返回当前实例化出来的view作为key
//        return position;返回当前实例化出来的view的位置作为key
    }



    /**************************   下面几个PagerAdapter的抽象方法不常用   **********************************/





//    /**
//     *
//     * @param object 就是instantiateItem方法返回的key
//     * @return
//     */
//    @Override
//    public int getItemPosition(Object object) {
//        return super.getItemPosition(object);
//    }


//    /**
//     *
//     * @param container
//     * @param position
//     * @param object 就是instantiateItem方法返回的key
//     */
//    @Override
//    public void setPrimaryItem(ViewGroup container, int position, Object object) {
//        super.setPrimaryItem(container, position, object);
//    }


//    @Override
//    public CharSequence getPageTitle(int position) {
//        return super.getPageTitle(position);
//    }


//    @Override
//    public float getPageWidth(int position) {
//        return super.getPageWidth(position);
//    }


//    @Override
//    public void finishUpdate(ViewGroup container) {
//        super.finishUpdate(container);
//    }


//    @Override
//    public void startUpdate(ViewGroup container) {
//        super.startUpdate(container);
//    }
}
