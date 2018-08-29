package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.yin.yzjcourse.Base.adapter.VPFragAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 与普通view的用法区别，adapter换成FragmentPageAdapter,
 * FragmentPageAdapter的实现类至少重写getItem()和getCount()即可，
 * page集合不再是List<View> 而是List<Fragment>
 *
 *     FragmentPageAdapter不适用与Fragment无限个或者获取动态变化的情况，因为虽然不可见的Fragment
 *     可能会被销毁，但是只要用户访问过，就好将该Fragment实例的数据保存在内容中，因此如果Fragment
 *     过多，内存开销就会很大，此时建议使用FragmentStatePagerAdapter代替。
 *
 *     也就是说FragmentPageAdapter适用于Fragment个数较少，且偏静态的情况
 */
public class VPFragmentActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> fragments;
    private VPFragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpfragment);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new VPFragment1());
        fragments.add(new VPFragment2());
        fragments.add(new VPFragment3());
        adapter = new VPFragAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
    }
}
