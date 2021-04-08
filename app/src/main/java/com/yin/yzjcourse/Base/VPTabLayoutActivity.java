package com.yin.yzjcourse.Base;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPTabLayoutAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VPTabLayoutActivity extends BaseActivity {

    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<View> views;
    private List<String> titles;
    private VPTabLayoutAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vptab_layout);
        ButterKnife.bind(this);
        initVP();
    }

    private void initVP() {
        views = new ArrayList<>();
        titles = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        views.add(inflater.inflate(R.layout.vp_item_1, null));
        titles.add("第1");
        views.add(inflater.inflate(R.layout.vp_item_2, null));
        titles.add("第2");
        views.add(inflater.inflate(R.layout.vp_item_3, null));
        titles.add("第3");
        views.add(inflater.inflate(R.layout.vp_item_4, null));
        titles.add("第4");
        views.add(inflater.inflate(R.layout.vp_item_5, null));
        titles.add("第5");
        views.add(inflater.inflate(R.layout.vp_item_6, null));
        titles.add("第6");
        views.add(inflater.inflate(R.layout.vp_item_7, null));
        titles.add("第7");
        views.add(inflater.inflate(R.layout.vp_item_8, null));
        titles.add("第8");
        views.add(inflater.inflate(R.layout.vp_item_9, null));
        titles.add("第9");
        views.add(inflater.inflate(R.layout.vp_item_10, null));
        titles.add("第10");
        adapter = new VPTabLayoutAdapter(views, titles);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);//TabLayout和ViewPager关联，只需这一行代码即可，标题列表不是在这设置的
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //setTabGravity 一般只有两三个tab时要用到，比如两个tab，fill：一个占一半，center;两个tab紧挨着放中间
        tab.setTabGravity(TabLayout.GRAVITY_FILL);
//        tab.setTabGravity(TabLayout.GRAVITY_CENTER);
        //设置下划线的颜色
        tab.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.blue));
        //设置下滑线的高
        tab.setSelectedTabIndicatorHeight(Utils.dip2px(this,5));
        //默认字体默认颜色和选中颜色
        tab.setTabTextColors(ContextCompat.getColor(this,R.color.red),
                ContextCompat.getColor(this,R.color.orange));
        //标题太多超出屏幕时可设置滑动
        tab.setTabMode(TabLayout.MODE_SCROLLABLE);//滚动
//        tab.setTabMode(TabLayout.MODE_FIXED);//不滚动
        //获取某个位置的tab
//        tab.getTabAt(index)
        //移除tab
//        tab.removeTab();
//        tab.removeTabAt();
//        tab.removeAllTabs();
        //设置tab的背景色
        tab.setBackgroundColor(ContextCompat.getColor(this,R.color.nav_bg));
    }
}
