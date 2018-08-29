package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPBaseAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 看VPadapter的详解

 用法可类比listView

 第一：根据需要在布局任意位置添加ViewPager控件，（既然是控件就可以是任意位置，任意大小）；
 第二：定义一个adapter继承PageAdapter，重写需要的方法，具体看注释；
 第三：准备多个page布局；
 第四：将多个page对应的view组成的list传入adapter；
 */
public class VPBaseActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    private VPBaseAdapter baseAdapter;
    private List<View> views;
    private View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        ButterKnife.bind(this);
        initVP();
    }

    private void initVP() {
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.vp_item_1, null);
        view2 = inflater.inflate(R.layout.vp_item_2, null);
        view3 = inflater.inflate(R.layout.vp_item_3, null);
        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        baseAdapter = new VPBaseAdapter(views);
        vp.setAdapter(baseAdapter);
//        baseAdapter.notifyDataSetChanged();
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

}
