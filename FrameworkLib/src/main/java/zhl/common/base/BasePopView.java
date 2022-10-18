package zhl.common.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

public abstract class BasePopView extends PopupWindow {

    private final int[] mLocation = new int[2];
    private final Rect mRect = new Rect();
    /**
     * 初始化属性
     */
    // protected abstract void prepareProperty();

    public OnChangeListener mChangeListener;
    protected Context mContext;
    private Boolean mIsShowAtTargetView = false;

    public BasePopView(Context context) {
        super(context);
        this.mContext = context;
        // 默认设置属性
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(false);
        setWidth(LayoutParams.WRAP_CONTENT);
        setHeight(LayoutParams.WRAP_CONTENT);
        setAnimationStyle(android.R.style.Animation_Dialog);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                if (mChangeListener != null)
                    mChangeListener.onDismiss();

            }
        });
        // prepareProperty();

    }

    public void setShowAtTargetView(Boolean mIsShowAtTargetView) {
        this.mIsShowAtTargetView = mIsShowAtTargetView;
    }

    public void setContentView(int layoutId) {
        setContentView(LayoutInflater.from(mContext).inflate(layoutId, null));
        initUI(getContentView());
    }

    /**
     * @param anchor
     */
    public void show(View anchor) {

        final View contentView = getContentView();

        if (contentView == null) {
            throw new IllegalStateException("You need to set the content view using the setContentView method");
        }
        // Replaces the background of the popup with a cleared background

        final int[] loc = mLocation;
        if (anchor != null) {
            anchor.getLocationOnScreen(loc);
            mRect.set(loc[0], loc[1], loc[0] + anchor.getWidth(), loc[1] + anchor.getHeight());
        }
        int[] location = onMeasureAndLayout(mRect, contentView);
        if (!mIsShowAtTargetView)
            showAtLocation(((Activity) mContext).getWindow().getDecorView().findViewById(android.R.id.content).getRootView(), Gravity.NO_GRAVITY, location[0], location[1]);
        else
            showAtLocation(anchor, Gravity.NO_GRAVITY, location[0], location[1]);

        if (mChangeListener != null) {
            mChangeListener.onShow();
        }
    }

    /**
     * 初始化ui组件
     */
    protected abstract void initUI(View v);

    /**
     * 计算显示的xy位置
     *
     * @param anchorRect
     * @param contentView
     */
    protected abstract int[] onMeasureAndLayout(Rect anchorRect, View contentView);

    public void setOnChangeListener(OnChangeListener mChangeListener) {
        this.mChangeListener = mChangeListener;
    }
}
