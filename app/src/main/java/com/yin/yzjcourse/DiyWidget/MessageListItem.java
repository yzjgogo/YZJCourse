package com.yin.yzjcourse.DiyWidget;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

public class MessageListItem extends RelativeLayout {

    private static final int[] STATE_MESSAGE_READED = {R.attr.state_message_readed};
    private boolean mMessgeReaded = false;

    public MessageListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setMessageReaded(boolean readed) {
        DLog.eLog("执行setMessageReaded-1");
        if (this.mMessgeReaded != readed) {
            DLog.eLog("执行setMessageReaded-2");
            mMessgeReaded = readed;
            refreshDrawableState();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        DLog.eLog("执行onCreateDrawableState");
        if (mMessgeReaded) {
            final int[] drawableState = super
                    .onCreateDrawableState(extraSpace + 1);
            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);
            return drawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }

}
