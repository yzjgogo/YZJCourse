package com.yin.yzjcourse.Base;

import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPTitleStripAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 设置标题
 * 每个page都要配一个标题
 * page是一个集合，因此标题对应的也是一个集合
 *
 * 第一：不居中将PagerTitleStrip作为ViewPager的子View，为layout_gravity是top或者bottom；
 * 第二：将标题集合传入PageAdapter，通过getPageTitle(int position)方法设置标题；
 *
 * PagerTitleStrip的不足：不能通过标题切换page,即不能与ViewPager交互；PagerTabStrip就可以弥补这个缺点
 */
public class VPTitleStripActivity extends BaseActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pts)
    PagerTitleStrip pts;
    private VPTitleStripAdapter titleStripAdapter;
    private List<View> views;
    private List<String> titles;
    private View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vptitle_strip);
        ButterKnife.bind(this);
        initVP();
    }

    private void initVP() {
        //初始化views
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.vp_item_1, null);
        view2 = inflater.inflate(R.layout.vp_item_2, null);
        view3 = inflater.inflate(R.layout.vp_item_3, null);
        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        //初始化titles
        titles = new ArrayList<>();
        titles.add("红色");
        titles.add("绿色");
        titles.add("蓝色");
        titleStripAdapter = new VPTitleStripAdapter(views, titles);
        vp.setAdapter(titleStripAdapter);
//        pts.setTextColor(ContextCompat.getColor(this, R.color.red));
//        titleStripAdapter.notifyDataSetChanged();
    }
}
