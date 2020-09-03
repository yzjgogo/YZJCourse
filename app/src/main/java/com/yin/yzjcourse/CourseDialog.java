package com.yin.yzjcourse;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by think on 2017/3/9.
 */

public class CourseDialog extends DialogFragment {
    private Dialog dialog;
    public static CourseDialog getInstance() {
        CourseDialog dialog = new CourseDialog();
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (dialog == null && getActivity()!=null) {
            dialog = new Dialog(getActivity(), R.style.feedback_dialog);
            dialog.setContentView(R.layout.dialog_ppt_feedback_layout);
            Window dialogWindow = dialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setGravity(Gravity.CENTER);
                dialogWindow.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        }
        return dialog;
    }
}
