package com.yin.yzjcourse.DiyWidget;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://www.jianshu.com/p/f131a390eb52
 */
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
        DLog.eLog("容器："+ Arrays.toString(location)+",screenWidth:"+ Utils.getScreenWidth(this)+",screenHeight:"+(Utils.getScreenHeight(this)+location[1]));

        /**
         * view.getLocalVisibleRect(localRect),返回true，说明view没有完全离开可滚动区域；返回false说明view离开可滚动区域了。
         * 一般是针对view的祖辈viewGroup中有可滚动的viewGroup的情况，或者view的某个方向的margin有负值的情况，例如marginLeft=-100px。
         * 如果view没有完全离开可滚动区域，则返回view在可滚动区域内的部分相对自身的坐标系的矩形区域localRect,注意坐标系是相对view自身的，因此view的真实左上角为坐标系的(0,0)，返回值为true。
         * 所谓真实左上角就是view的实际左上角，而不是view在可滚动区域内部的左上角；
         * 所谓可滚动区域就是可滚动控件的可见区域,例如ScrollView的可见区域。
         * 如果view完全离开可滚动区域，则localRect是以可滚动的viewGroup的左上角为坐标系的矩形区域(view所占的整个区域)，而不再是以view自身的左上角为坐标系了。此时返回false。
         */
        Rect localRect = new Rect();
        boolean localBool = testView.getLocalVisibleRect(localRect);
        DLog.eLog("local："+localRect.toString()+","+localBool);

        /**
         * view.getGlobalVisibleRect(rect)，返回true，说明view没有完全离开可滚动区域；返回false说明view离开可滚动区域了。
         * 一般是针对view的祖辈viewGroup中有可滚动的viewGroup的情况，或者view的某个方向的margin有负值的情况，例如marginLeft=-100px。
         * 如果view没有完全离开可滚动区域，则返回view在可滚动区域内的部分相对屏幕的坐标系的矩形区域rect,注意坐标系是相对屏幕的，因此屏幕的左上角为坐标系的(0,0)，返回值为true。
         * 如果view完全离开可滚动区域，则rect是以可滚动的viewGroup的左上角为坐标系的矩形区域(view所占的整个区域)，而不再是以屏幕的左上角为坐标系了。此时返回false。
         */
        Rect globalRect = new Rect();
        boolean golobalBool = testView.getGlobalVisibleRect(globalRect);
        int[] globalLocation = new int[2];
        testView.getLocationOnScreen(globalLocation);
        DLog.eLog("global："+globalRect.toString()+","+Arrays.toString(globalLocation)+","+golobalBool);
    }
}
