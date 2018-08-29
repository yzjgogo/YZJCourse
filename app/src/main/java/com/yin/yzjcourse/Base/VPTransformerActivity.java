package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.yin.yzjcourse.Base.adapter.VPTransAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VPTransformerActivity extends BaseActivity {
    private static final float MAX_ALPHA=0.5f;
    private static final float MAX_SCALE=0.9f;
    @BindView(R.id.vp)
    ViewPager vp;
    private VPTransAdapter transAdapter;
    private List<View> views;
    private View view1, view2, view3, view4, view5, view6, view7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp_trans);
        ButterKnife.bind(this);
        initVP();
    }

    private void initVP() {
        LayoutInflater inflater = getLayoutInflater();
        view1 = inflater.inflate(R.layout.vp_item_1, null);
        view1.setTag(1);

        view2 = inflater.inflate(R.layout.vp_item_2, null);
        view2.setTag(2);

        view3 = inflater.inflate(R.layout.vp_item_3, null);
        view3.setTag(3);

        view4 = inflater.inflate(R.layout.vp_item_4, null);
        view4.setTag(4);

        view5 = inflater.inflate(R.layout.vp_item_5, null);
        view5.setTag(5);

        view6 = inflater.inflate(R.layout.vp_item_6, null);
        view6.setTag(6);

        view7 = inflater.inflate(R.layout.vp_item_7, null);
        view7.setTag(7);

        views = new ArrayList<>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        views.add(view4);
        views.add(view5);
        views.add(view6);
        views.add(view7);
        transAdapter = new VPTransAdapter(views,this);
        vp.setOffscreenPageLimit(3);
//        vp.setPageMargin(30);
        vp.setAdapter(transAdapter);
        vp.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
//                DLog.eLog("此时的位置：" +page.getTag().toString()+","+ position);
                if(position<-1||position>1){
                    //不可见区域
                    page.setAlpha(MAX_ALPHA);
                    page.setScaleX(MAX_SCALE);
                    page.setScaleY(MAX_SCALE);
                }else {
                    //可见区域，透明度效果
                    if(position<=0){
                        //pos区域[-1,0)
                        page.setAlpha(MAX_ALPHA+MAX_ALPHA*(1+position));
                    }else{
                        //pos区域[0,1]
                        page.setAlpha(MAX_ALPHA+MAX_ALPHA*(1-position));
                    }
                    //可见区域，缩放效果
                    float scale=Math.max(MAX_SCALE,1-Math.abs(position));
                    page.setScaleX(scale);
                    page.setScaleY(scale);
                }
            }
        });
    }

}
