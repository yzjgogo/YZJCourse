package com.flexiblecalendar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.flexiblecalendar.infiniteviewpager.InfiniteViewPager;


/**
 * @author p-v
 */
class MonthViewPager extends InfiniteViewPager {

    private int rowHeight = 0;
    private int numOfRows;

    public MonthViewPager(Context context) {
        super(context);
    }

    public MonthViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        boolean wrapHeight =
                View.MeasureSpec.getMode(heightMeasureSpec) == View.MeasureSpec.AT_MOST;

        int height = getMeasuredHeight();

        if (wrapHeight && rowHeight == 0) {
            int width = getMeasuredWidth();

            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width,
                    View.MeasureSpec.EXACTLY);

            if (getChildCount() > 0) {
                View firstChild = getChildAt(0);

                firstChild.measure(widthMeasureSpec, View.MeasureSpec
                        .makeMeasureSpec(height, View.MeasureSpec.AT_MOST));

                height = firstChild.getMeasuredHeight();
                rowHeight = numOfRows == 6 ? height : (int) Math.ceil(((float) height * 6) / 5);
            }
        }

        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(rowHeight,
                View.MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    void setNumOfRows(int numOfRows) {
        this.numOfRows = numOfRows;
    }
}
