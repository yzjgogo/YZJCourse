package com.yin.yzjcourse.Base;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.yin.yzjcourse.Base.adapter.VPStateFragAdapter;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 与普通view的用法区别，adapter换成FragmentStatePagerAdapter,
 * FragmentStatePagerAdapter的实现类至少重写getItem()和getCount()即可，
 * page集合不再是List<View> 而是List<Fragment>
 *
 * FragmentStatePagerAdapter只保留当前页面，当页面离开视线后，就会被消除，释放其资源；
 * 而在页面需要显示时，再生成新的页面。这样实现的最大好处在于当拥有大量的页面时，
 * 不必在内存中占用大量的内存。
 *
 * 对比FragmentPagerAdapter
 */
public class VPStateFragmentActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> fragments;
    private VPStateFragAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vpfragment_state);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        fragments.add(new VPFragment1());
        fragments.add(new VPFragment2());
        fragments.add(new VPFragment3());
        adapter = new VPStateFragAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
    }
}
