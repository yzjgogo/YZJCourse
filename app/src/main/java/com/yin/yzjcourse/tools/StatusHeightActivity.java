package com.yin.yzjcourse.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.NO_ID;

public class StatusHeightActivity extends BaseActivity {

    private static final String NAVIGATION = "navigationBarBackground";
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_height);
        ButterKnife.bind(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int statusHeight = getStatusBarHeight(this);
        boolean isHaveVirtualNav = isNavigationBarExist(this);
        //无论isHaveVirtualNav为true还是false，getNavigationBarHeight()都能获取到虚拟导航栏的高
        int virtualNavHeight = getNavigationBarHeight(this);
        if (isHaveVirtualNav) {
            tvContent.setText("状态栏的高：" + statusHeight + ",虚拟导航栏是否存在：" + isHaveVirtualNav + ",虚拟导航栏的高：" + virtualNavHeight);
        }else {
            tvContent.setText("状态栏的高：" + statusHeight + ",虚拟导航栏是否存在：" + isHaveVirtualNav);
        }
    }

    @OnClick({R.id.bt_status_height, R.id.bt_nav_height})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_status_height:
                int statusHeight = getStatusBarHeight(this);
                tvContent.setText("状态栏的高：" + statusHeight);
                break;
            case R.id.bt_nav_height:
                boolean isHaveVirtualNav = isNavigationBarExist(this);
                //无论isHaveVirtualNav为true还是false，getNavigationBarHeight()都能获取到虚拟导航栏的高
                int virtualNavHeight = getNavigationBarHeight(this);
                if (isHaveVirtualNav) {
                    tvContent.setText("虚拟导航栏是否存在：" + isHaveVirtualNav + ",虚拟导航栏的高：" + virtualNavHeight);
                }else {
                    tvContent.setText("虚拟导航栏是否存在：" + isHaveVirtualNav);
                }
                break;
        }
    }


    /**
     * 获取状态栏的高度
     *
     * @param context
     * @return
     */
    private int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 判断虚拟导航栏是否存在 该方法需要在View完全被绘制出来之后调用，否则判断不了
     * 在比如 onWindowFocusChanged（）方法中可以得到正确的结果
     *
     * @param activity
     * @return
     */
    private boolean isNavigationBarExist(Activity activity) {
        ViewGroup vp = (ViewGroup) activity.getWindow().getDecorView();
        for (int i = 0; i < vp.getChildCount(); i++) {
            vp.getChildAt(i).getContext().getPackageName();
            if (vp.getChildAt(i).getId() != NO_ID && NAVIGATION.equals(activity.getResources().getResourceEntryName(vp.getChildAt(i).getId()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 虚拟导航栏高度
     *
     * @param context
     * @return
     */
    private int getNavigationBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
