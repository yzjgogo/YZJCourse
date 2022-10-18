package com.flexiblecalendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by HB on 2017/7/27.
 */

public class NoScollerGridView extends GridView {
    public NoScollerGridView(Context context) {
        super(context);
    }

    public NoScollerGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScollerGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
