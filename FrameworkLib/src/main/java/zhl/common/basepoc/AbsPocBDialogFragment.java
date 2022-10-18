package zhl.common.basepoc;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.CachePolicy;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import zhl.common.base.ErrorDialogFragment;
import zhl.common.base.ProgressDialogFragment;
import zhl.common.base.ProgressDialogFragment.ProgressDialogFragmentBuilder;
import zhl.common.request.RequestListener;
import zhl.common.request.RequestManager;
import zhl.common.request.ZHLRequest;
import zhl.common.utils.Tools;

public class AbsPocBDialogFragment extends DialogFragment implements View.OnClickListener {


    private int loadingLayout; // 外部实现的loading方式
    protected Handler mBaseHandler = new Handler();

    /**
     * 设置外部定制的loading布局
     *
     * @param loadingLayout
     * @author zqs
     * @createTime 2015年3月5日 下午6:43:17
     */
    public void setLoadingLayout(int loadingLayout) {
        this.loadingLayout = loadingLayout;
    }

    @Override
    public void dismiss() {
        try {
            super.dismissAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public void show(FragmentActivity fragmentActivity) {
        try {
            show(fragmentActivity.getSupportFragmentManager(), getClass().getSimpleName());
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commitAllowingStateLoss();
//            super.show(manager, tag);

            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(this, tag);
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }


    protected void showDataErrorDialog(String msg, android.content.DialogInterface.OnClickListener onClickListener) {
        if (msg == null || msg.equals(""))
            msg = "网络异常";
        new ErrorDialogFragment.ErrorDialogFragmentBuilder(getActivity()).setMessage(msg).setOnClickListener(onClickListener).show();
    }

    protected void showLoadingDialog() {
        new ProgressDialogFragmentBuilder((FragmentActivity) getActivity(), loadingLayout).show();
    }

    protected void hideLoadingDialog() {
        ProgressDialogFragment.dismiss((FragmentActivity) getActivity());
    }

    /**
     * 带有loading的任务
     *
     * @param request
     * @param listener
     * @param policy
     */
    public void executeLoadingCanStop(final ZHLRequest request, RequestListener listener, CachePolicy policy) {
        new ProgressDialogFragment.ProgressDialogFragmentBuilder((FragmentActivity) getActivity(), loadingLayout).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                stop(request);
            }
        }).show();
        request.setTag(this.hashCode());
        RequestManager.execute(request, listener, policy);
    }

    /**
     * 带有loading的任务
     *
     * @param request
     * @param listener
     */
    public void executeLoadingCanStop(final ZHLRequest request, RequestListener listener) {
        new ProgressDialogFragmentBuilder((FragmentActivity) getActivity(), loadingLayout).setOnCancelListener(new android.content.DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(android.content.DialogInterface dialog) {
                stop(request);
            }
        }).show();
        request.setTag(this.hashCode());
        RequestManager.execute(request, listener);
    }

    /**
     * 发起请求
     *
     * @param request
     * @param listener
     * @param policy
     */
    public void execute(final ZHLRequest request, RequestListener listener, CachePolicy policy) {
        request.setTag(this.hashCode());
        RequestManager.execute(request, listener, policy);
    }

    /**
     * 发起请求
     *
     * @param request
     * @param listener
     */
    public void execute(ZHLRequest request, RequestListener listener) {
        request.setTag(this.hashCode());
        RequestManager.execute(request, listener);
    }

    /**
     * 发起请求
     *
     * @param request
     */
    public void execute(ZHLRequest request) {
        request.setTag(this.hashCode());
        RequestManager.execute(request, null);
    }

    /**
     * 取消请求
     *
     * @param request
     */
    public void stop(ZHLRequest request) {
        request.cancel();
    }

    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void toast(String text) {
        if (getActivity() != null && !Tools.isEmpty(text)) {
            Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    public void toast(int resId) {
        if (getActivity() != null) {
            Toast toast = Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    public void onClick(View v) {

    }


    public void initComponentEvent() {
    }


    public void initComponentValue() {
    }

    @Override
    public void onDestroy() {
        mBaseHandler.removeCallbacksAndMessages(null);
        RequestManager.cancleAllByTag(this.hashCode());
        super.onDestroy();
    }
}
