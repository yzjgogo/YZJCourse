package com.yin.yzjcourse.MaterialDesign;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2017/9/28.
 */

public class SimpleBottomDialog extends BottomSheetDialogFragment {
    private int statusBarHeight = 0;//状态栏的高度
    private int unUseHeight = 0;//bottomSheet显示默认高度时屏幕剩余的高
    private FrameLayout.MarginLayoutParams marginLayoutParams;
    private RelativeLayout rlBottomButton;

    public static SimpleBottomDialog newInstance(int topSize) {
        SimpleBottomDialog dialog = new SimpleBottomDialog();
        dialog.statusBarHeight = topSize;
        return dialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        unUseHeight = Utils.getScreenHeight(getContext()) - statusBarHeight - Utils.dip2px(getContext(), 200);
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.simple_bottom_dialog_layout, null);
        rlBottomButton = (RelativeLayout) view.findViewById(R.id.rl_bottom_button);
        dialog.setContentView(view);
        initBehavior(view, rlBottomButton);
        marginLayoutParams = (FrameLayout.MarginLayoutParams) rlBottomButton.getLayoutParams();
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
//        initBehavior();
    }

    /**
     * 获取BottomSheetBehavior的方式1
     * @param view
     * @param rlBottomButton
     */
    private void initBehavior(View view, final RelativeLayout rlBottomButton) {
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(Utils.dip2px(getContext(), 200));
        mBehavior.setHideable(false);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                int pxOffet = (int) (unUseHeight * slideOffset);
                DLog.eLog("顶部margin是多少："+marginLayoutParams.topMargin);
                marginLayoutParams.topMargin = Utils.dip2px(getContext(), 140) + pxOffet;//这个140在布局中可以看到，是BottomSheet显示默认高时的topMargin
                rlBottomButton.setLayoutParams(marginLayoutParams);
            }
        });
    }

    /**
     * 获取BottomSheetBehavior的方式2
     */
    private void initBehavior() {
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        FrameLayout bottomSheet = dialog.getDelegate().findViewById(R.id.design_bottom_sheet);
        if (bottomSheet!=null) {
            BottomSheetBehavior<FrameLayout> mBehavior = BottomSheetBehavior.from(bottomSheet);
            mBehavior.setPeekHeight(Utils.dip2px(getContext(), 200));
            mBehavior.setHideable(false);
            mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    int pxOffet = (int) (unUseHeight * slideOffset);
                    DLog.eLog("顶部margin是多少："+marginLayoutParams.topMargin);
                    marginLayoutParams.topMargin = Utils.dip2px(getContext(), 140) + pxOffet;//这个140在布局中可以看到，是BottomSheet显示默认高时的topMargin
                    rlBottomButton.setLayoutParams(marginLayoutParams);
                }
            });
        }
    }
}
