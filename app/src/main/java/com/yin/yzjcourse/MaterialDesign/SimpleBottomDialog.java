package com.yin.yzjcourse.MaterialDesign;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/9/28.
 */

public class SimpleBottomDialog extends BottomSheetDialogFragment {
    public static SimpleBottomDialog newInstance() {
        return new SimpleBottomDialog();
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.simple_bottom_dialog_layout, null);
        dialog.setContentView(view);
        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBehavior.setPeekHeight(300);
        return dialog;
    }
}
