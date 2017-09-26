/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yin.yzjcourse.Coordinator.designDemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.ArrayList;
import java.util.List;

/**
 AppBarLayout中子控件的layout_scrollFlags：
 SCROLL_FLAG_SCROLL：设置了该属性则该view会跟随滚动
 Child1会跟着滚动，Child2到顶部固定
 <CoordinatorLayout>
 <AppBarLayout>
 <Child1 app:layout_scrollFlags="scroll" />
 <Child2 />
 </AppBarLayout>
 <ScrollableView />
 </CoordinatorLayout>

 Child1和Child2都不会跟着滚动，而是始终固定在顶部
 <CoordinatorLayout>
 <AppBarLayout>
 <Child1/>
 <Child2  app:layout_scrollFlags="scroll"  />
 </AppBarLayout>
 <ScrollableView />
 </CoordinatorLayout>

 Child1和Child2都会跟着滚动，不会固定在顶部
 <CoordinatorLayout>
 <AppBarLayout>
 <Child1 app:layout_scrollFlags="scroll" />
 <Child2  app:layout_scrollFlags="scroll" />
 </AppBarLayout>
 <ScrollableView />
 </CoordinatorLayout>


 SCROLL_FLAG_SNAP：根据滚动停止时的状态，如果这个view只有部分可见则这个view会立刻自动滚动到最近的边缘；
 即该view不会停留在中间状态，要不全部显示要么全部隐藏。

 SCROLL_FLAG_ENTER_ALWAYS：无论当前在什么状态，滚动到了什么位置，只要手指向下滑都是先将该view拉出来(全部显示)，
 然后可滚动的veiw才可以向下滚动；只要手指向上滑都是先将该view推出去(全部隐藏)，然后可滚动的view才可以向上滚动

 SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED：enterAlways的附加值。要和enterAlways一起使用，
 这里涉及到Child View的高度和最小高度，向下滚动时，Child View先向下滚动最小高度值，
 然后Scrolling View开始滚动，到达边界时，Child View再向下滚动，直至显示完全。向上滚动没有这种效果只
 有enterAlways的效果。c参考file/enterAlwaysCollapsed.gif
 <CoordinatorLayout>
 <AppBarLayout>
 <android.support.v7.widget.Toolbar
 android:id="@+id/toolbar"
 android:layout_width="match_parent"
 android:minHeight="?attr/actionBarSize"
 android:layout_height="150dp"
 android:background="?attr/colorPrimary"
 app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
 app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed" />
 <Child2 />
 </AppBarLayout>
 <ScrollableView />
 </CoordinatorLayout>

 SCROLL_FLAG_EXIT_UNTIL_COLLAPSED：这里也涉及到最小高度。发生向上滚动事件时，Child View向上滚动退出直至
 最小高度，然后Scrolling View开始滚动。也就是，Child View不会完全退出屏幕。参考file/exitUntilCollapsed.gif
 <CoordinatorLayout>
 <AppBarLayout>
 <android.support.v7.widget.Toolbar
 android:id="@+id/toolbar"
 android:layout_width="match_parent"
 android:minHeight="?attr/actionBarSize"
 android:layout_height="150dp"
 android:background="?attr/colorPrimary"
 app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
 app:layout_scrollFlags="scroll|exitUntilCollapsed" />
 <Child2 />
 </AppBarLayout>
 <ScrollableView />
 </CoordinatorLayout>

 */
public class CheeseActivity extends BaseActivity implements View.OnClickListener{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         * 设置左上角的返回图标并显示
         */
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        /**
         * 设置navigationView
         * 被点击的item会高亮显示在抽屉菜单中，让用户知道当前是哪个菜单被选中
         */
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.addHeaderView();
//        navigationView.inflateMenu();
        navigationView.getHeaderView(0).findViewById(R.id.head_iv).setOnClickListener(this);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        /**
         * 设置ViewPager
         */
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }
        /**
         * 设置FloatingActionButton
         * 正常情况下有填充色，有阴影，按下去时有波纹效果(rippleColor)颜色也可以有变化；且这些都可以自定义；
         * 默认的填充颜色取的是，theme中的colorAccent，所以你可以在style中定义colorAccent;
         * rippleColor默认取的是theme中的colorControlHighlight
         * 你也可以通过下面两个属性设置这两个颜色
         * app:backgroundTint="#ff87ffeb"
         * app:rippleColor="#33728dff"
         *
         * app:elevation:正常显示的阴影大小；
         * app:pressedTranslationZ：按下去时的阴影大小
         * app:fabSize="mini"或"normal"
         *
         * 如果想在某个控件的指定位置放置该按钮则可以用到这两个属性：
         * app:layout_anchor="@id/appbar"//固定所依赖的控件
         * app:layout_anchorGravity="bottom|right|end"//所在控件的具体位置
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "弹出Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("确定",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Log.i("yin","点击了一下");
                            }
                        }).setActionTextColor(Color.parseColor("#EE82EE")).show();
            }
        });
        /**
         * Snackbar是design support library中另一个组件，使用Snackbar我们可以在屏幕底部(大多时候)快速弹出消息，它和Toast非常相似，但是它更灵活一些;
         * 可以自定义可选操作(添加一个单击事件)；
         * 同一时间只能显示一个snackbar；
         * Snackbar使用的时候需要一个控件容器用来容纳Snackbar.官方推荐使用CoordinatorLayout,因为使用这个控件，可以保证Snackbar可以让用户通过向右滑动退出,否则向右滑动无效，
         *
         */
        findViewById(R.id.fab_mini).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Snackbar.make(v ,"弹出Snackbar", Snackbar.LENGTH_SHORT).show();
            }
        });

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        switch (AppCompatDelegate.getDefaultNightMode()) {
//            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
//                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_AUTO:
//                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_YES:
//                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
//                break;
//            case AppCompatDelegate.MODE_NIGHT_NO:
//                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
//                break;
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//实现点击左上角的返回按钮可以打开或隐藏侧边栏
                return true;
            case R.id.other:
                Intent intent = new Intent(this,OtherActivity.class);
                startActivity(intent);
                break;
//            case R.id.menu_night_mode_system:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                break;
//            case R.id.menu_night_mode_day:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                break;
//            case R.id.menu_night_mode_night:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                break;
//            case R.id.menu_night_mode_auto:
//                setNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setNightMode(@AppCompatDelegate.NightMode int nightMode) {
        AppCompatDelegate.setDefaultNightMode(nightMode);

        if (Build.VERSION.SDK_INT >= 11) {
            recreate();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new CheeseListFragment(), "订单");
        adapter.addFragment(new CheeseListFragment(), "仍然");
        adapter.addFragment(new CheeseListFragment(), "王五");
        adapter.addFragment(new CheeseListFragment(), "哟发");
        adapter.addFragment(new CheeseListFragment(), "请求");
        adapter.addFragment(new CheeseListFragment(), "那你");
        adapter.addFragment(new CheeseListFragment(), "密码");
        adapter.addFragment(new CheeseListFragment(), "匹配");
        adapter.addFragment(new CheeseListFragment(), "考虑");
        adapter.addFragment(new CheeseListFragment(), "方法");
        adapter.addFragment(new CheeseListFragment(), "订单");
        viewPager.setAdapter(adapter);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.head_iv){
            Toast.makeText(this,"点击头部", Toast.LENGTH_SHORT).show();
        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
