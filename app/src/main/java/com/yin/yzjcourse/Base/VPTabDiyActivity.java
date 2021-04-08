package com.yin.yzjcourse.Base;

import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPTabDiyAdapter;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 第一：设置tab下划线的颜色PagerTabStrip的setTabIndicatorColorResource()方法；
 * <p>
 * 第二：设置标题字体大小，颜色，图标，在PageAdapter的getPageTitle()方法中通过
 * SpannableStringBuilder设置。
 */
public class VPTabDiyActivity extends AppCompatActivity {
    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.pts)
    PagerTabStrip pts;
    private VPTabDiyAdapter diyAdapter;
    private List<View> views;
    private List<String> titles;
    private View view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vptab_diy);
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
        diyAdapter = new VPTabDiyAdapter(views, titles, this);
        vp.setAdapter(diyAdapter);

        //修改tab下划线的颜色
        pts.setTabIndicatorColorResource(R.color.orange);
        //取消tab的下划线？
//        pts.setDrawFullUnderline(false);
//        //设置tab的背景色
//        pts.setBackgroundResource(R.color.bg);
//
        pts.setTextSpacing(400);
    }
}
