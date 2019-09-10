package com.yin.yzjcourse.DiyWidget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewLocalRectActivity extends BaseActivity {

    @BindView(R.id.test_view)
    View testView;
    @BindView(R.id.container)
    RelativeLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_local_rect);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_get)
    public void onViewClicked() {
        int[] location = new int[2];
        container.getLocationOnScreen(location);//目的是为了知道状态栏的高度
        DLog.eLog("容器："+ Arrays.toString(location));

        /**
         * view.getLocalVisibleRect(localRect)
         * 返回view的可视区域相对自身的坐标系的矩形区域localRect,注意坐标系是相对view自身的，因此view的真实左上角为坐标系的(0,0);
         * 所谓真实左上角就是view的实际左上角，而不是view可视区域的左上角；
         * 可视区域与invisible无关，可以理解成有效区域，并不是说invisible返回的矩形就没有了，例如layout_marginLeft="-100px",那么这溢出去的100px就不是可视区域。剩下的才是可视区域
         */
        Rect localRect = new Rect();
        testView.getLocalVisibleRect(localRect);
        DLog.eLog("local："+localRect.toString());

        /**
         * view.getGlobalVisibleRect(rect)
         * 返回view的可视区域在屏幕中的矩形区域，注意是相对屏幕的，因此rect所在的坐标系是屏幕，原点就是屏幕的左上角(0,0)
         * 可视区域与invisible无关，可以理解成有效区域，并不是说invisible返回的矩形就没有了，例如layout_marginLeft="-100px",那么这溢出去的100px就不是可视区域。剩下的才是可视区域
         */
        Rect globalRect = new Rect();
        testView.getGlobalVisibleRect(globalRect);
        DLog.eLog("global："+globalRect.toString());
    }
}
