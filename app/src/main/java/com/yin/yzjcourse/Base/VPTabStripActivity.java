package com.yin.yzjcourse.Base;

import android.os.Bundle;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPTabStripAdapter;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 用法与PagerTitleStrip类似
 * 不同的是PagerTabStrip可以与ViewPager交互，点击tab可以切换page
 */
public class VPTabStripActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pts)
    PagerTabStrip pts;
    private VPTabStripAdapter tabStripAdapter;
    private List<View> views;
    private List<String> titles;
    private View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vptab_strip);
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
        tabStripAdapter = new VPTabStripAdapter(views, titles);
        vp.setAdapter(tabStripAdapter);
    }
}
