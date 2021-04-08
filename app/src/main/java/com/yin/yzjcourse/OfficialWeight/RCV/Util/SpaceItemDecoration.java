package com.yin.yzjcourse.OfficialWeight.RCV.Util;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;


public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    /**
     * 用来控制item的上下左右的空白空间
     * @param outRect 当前item所占矩形
     * @param view 当前item
     * @param parent RecyclerView
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;//当前item的左侧空白空间
        outRect.top = space;//当前item的顶部空白空间
        outRect.right = space;//当前item的右侧空白空间
        outRect.bottom = space;//当前item的底部空白空间
//        outRect.set(space,space,space,space);一次性设置上下左右四个空白空间
        parent.getChildLayoutPosition(view);//获取当前item所在的position
    }
}
