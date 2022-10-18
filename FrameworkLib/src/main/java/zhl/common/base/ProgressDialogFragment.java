package zhl.common.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.R;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public final class ProgressDialogFragment extends DialogFragment {

    private static final String FRAGMENT_TAG = "com.fragment.dialogs.progressDialog";
    private static final String BUNDLE_MESSAGE = "message";
    private static final String BUNDLE_IS_CANCELABLE = "isCancelable";
    private static final String BUNDLE_LAYOUT_ID = "layout_id";

    private OnCancelListener mOnCancelListener;
    private DialogInterface.OnDismissListener onDismissListener;

    public static ProgressDialogFragment newInstance(int layout, String message, DialogInterface.OnDismissListener onDismissListener, boolean isCancelable) {
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.onDismissListener = onDismissListener;
        Bundle args = new Bundle();
        args.putString(BUNDLE_MESSAGE, message);
        args.putBoolean(BUNDLE_IS_CANCELABLE, isCancelable);
        args.putInt(BUNDLE_LAYOUT_ID, layout);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    public static ProgressDialogFragment newInstance(int layout, String message, OnCancelListener onCancelListener, boolean isCancelable) {
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.mOnCancelListener = onCancelListener;
        Bundle args = new Bundle();
        args.putString(BUNDLE_MESSAGE, message);
        args.putBoolean(BUNDLE_IS_CANCELABLE, isCancelable);
        args.putInt(BUNDLE_LAYOUT_ID, layout);
        dialogFragment.setArguments(args);
        return dialogFragment;
    }

    public static void dismiss(FragmentActivity activity) {
        if (activity != null && !activity.isFinishing()) {
            FragmentManager fragmentManager = activity.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment prev = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
            if (prev != null) {
                fragmentTransaction.remove(prev);
            }
            fragmentTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle args = getArguments();
        Dialog dialog = new Dialog(getActivity(), R.style.framework_loading_dialog);
        if(onDismissListener != null){
            dialog.setOnDismissListener(onDismissListener);
        }
        int layoutId = args.getInt(BUNDLE_LAYOUT_ID);
        if (layoutId != 0) {
            dialog.setContentView(layoutId);
        } else {
            dialog.setContentView(R.layout.sdk_framework_loading_dialog);
        }
        // 外部实现动画效果
        View view = dialog.getWindow().getDecorView().findViewById(R.id.sdk_framework_loading_anima_id);
        if (view != null) {
            AnimationDrawable spinner = (AnimationDrawable) view.getBackground();
            spinner.start();
            // Animation operatingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.sdk_framework_loading_anim);
            // LinearInterpolator lin = new LinearInterpolator();
            // operatingAnim.setInterpolator(lin);
            // view.startAnimation(operatingAnim);
        }
        // 设置自定义loading文字
        String message = args.getString(BUNDLE_MESSAGE);
        if (message != null && !"".equals(message)) {
            TextView text = (TextView) dialog.getWindow().getDecorView().findViewById(R.id.sdk_framework_loading_message_id);
            if (text != null) {
                text.setText(message);
            }
        }
        setCancelable(args.getBoolean(BUNDLE_IS_CANCELABLE));
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (mOnCancelListener != null) {
            mOnCancelListener.onCancel(dialog);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void show(FragmentActivity fActivity) {
        FragmentTransaction transaction = fActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(this, FRAGMENT_TAG);
        transaction.commitAllowingStateLoss();
    }

    public void show(FragmentActivity fActivity, String tag){
        FragmentTransaction transaction = fActivity.getSupportFragmentManager().beginTransaction();
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(onDismissListener != null){
            onDismissListener.onDismiss(dialog);
        }

    }

    public static class ProgressDialogFragmentBuilder {

        private FragmentActivity mActivity;
        private String mMessage = null;
        private OnCancelListener mOnCancelListener = null;
        private boolean mCancelable = true;
        private int layout;

        public ProgressDialogFragmentBuilder(FragmentActivity activity, int layout) {
            mActivity = activity;
            this.layout = layout;
        }

        public ProgressDialogFragmentBuilder setMessage(int resId) {
            mMessage = mActivity.getString(resId);
            return this;
        }

        public ProgressDialogFragmentBuilder setMessage(String text) {
            mMessage = text;
            return this;
        }

        public ProgressDialogFragmentBuilder setOnCancelListener(OnCancelListener onCancelListener) {
            mOnCancelListener = onCancelListener;
            return this;
        }

        public ProgressDialogFragmentBuilder setCancelable(boolean cancelable) {
            mCancelable = cancelable;
            return this;
        }

        public ProgressDialogFragment show() {
            if (mActivity != null && !mActivity.isFinishing()) {
                FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment prev = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
                if (prev != null) {
                    fragmentTransaction.remove(prev);
                }
                fragmentTransaction.commitAllowingStateLoss();
                ProgressDialogFragment pFragment = ProgressDialogFragment.newInstance(layout, mMessage, mOnCancelListener, mCancelable);
                pFragment.show(mActivity);
                return pFragment;
            }
            return null;
        }
    }
}
