package com.yin.yzjcourse;

import android.app.Dialog;
import android.os.Bundle;
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

    public static CourseDialog getInstance() {
        CourseDialog dialog = new CourseDialog();
        return dialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 上下左右无边界，即没有半透明的背景，全部被dialog填充，无法被弹窗覆盖的区域仍然会有遮罩层
         * 比如你希望dialog的左右侧和底部到达屏幕边界；
         */
//        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.shareDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);//取出dialog的默认标题
        //如果dialog中有EditText，则弹出dialog时自动弹出软键盘，否则软键盘默认不弹出
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        View view = inflater.inflate(R.layout.course_dialog_layout, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        initDialogStyle();
    }

    private void initDialogStyle() {
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        //写死整个dialog弹窗的大小，如果你在布局文件中写死，你会发现弹出后并不是按照你给的定值显示
        wlp.width = Utils.dip2px(getContext(), 285);
        wlp.height = Utils.dip2px(getContext(), 431);
//        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
    }
}
