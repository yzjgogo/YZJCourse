package com.yin.yzjcourse.MaterialDesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.R;

/**
 * Created by think on 2017/9/28.
 */

public class SimpleBottomDialog extends BottomSheetDialogFragment {
    public static SimpleBottomDialog newInstance() {
        return new SimpleBottomDialog();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.simple_bottom_dialog_layout, container, false);
        return v;
    }
}
