/**
 * @author ylf
 * @createTime 2014-5-27 下午5:21:52
 */
package zhl.common.basepoc;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import  androidx.fragment.app.Fragment;
import  androidx.fragment.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.CachePolicy;
import com.lidroid.xutils.R;

import zhl.common.base.ErrorDialogFragment;
import zhl.common.base.ProgressDialogFragment;
import zhl.common.base.ProgressDialogFragment.ProgressDialogFragmentBuilder;
import zhl.common.request.RequestListener;
import zhl.common.request.RequestManager;
import zhl.common.request.ZHLRequest;
import zhl.common.utils.Tools;
//import zhl.common.utils.analytics.ManAnalyticsPageHit;
import zhl.common.utils.analytics.PageNameUtil;

/**
 * @author ylf
 * @createTime 2014-5-27 下午2:18:49
 */

public class AbsPocBFragment extends Fragment implements View.OnClickListener {

    private static int loadingLayout; // 外部实现的loading方式
    protected Handler mBaseHandler = new Handler();
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static void setLoadingLayout(int loadingLayout) {
        AbsPocBFragment.loadingLayout = loadingLayout;
    }

    protected void showDataErrorDialog(String msg, android.content.DialogInterface.OnClickListener onClickListener) {
        if (msg == null || msg.equals(""))
            msg = "网络异常";
        new ErrorDialogFragment.ErrorDialogFragmentBuilder(getActivity()).setMessage(msg).setOnClickListener(onClickListener).show();
    }

    protected void showLoadingDialog() {
        new ProgressDialogFragmentBuilder(getActivity(), loadingLayout).setCancelable(false).show();
    }

    protected void showLoadingDialog(String message) {
        new ProgressDialogFragment.ProgressDialogFragmentBuilder(getActivity(), loadingLayout).setMessage(message).setCancelable(false).show();
    }

    protected void hideLoadingDialog() {
        ProgressDialogFragment.dismiss(getActivity());
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


    @Override
    public void onDestroy() {
        mBaseHandler.removeCallbacksAndMessages(null);
        RequestManager.cancleAllByTag(this.hashCode());
        super.onDestroy();
    }


    public void initComponentEvent() {
    }


    public void initComponentValue() {
    }


    public void toast(String text) {
        if (getActivity() != null && !Tools.isEmpty(text)) {
            toastSimpleMessage(getActivity(), text);
//            Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
        }
    }

    public void toast(int resId) {
        if (getActivity() != null) {
            Toast toast = Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 简单的toast
     *
     * @param context
     * @param text
     */
    public static void toastSimpleMessage(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_toast, null);
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPause() {
        ALAnalytics();
        super.onPause();
//        ManAnalyticsPageHit.getInstance().pageDisAppear(getClass().getSimpleName(), getActivity().getClass().getSimpleName());
    }

    @Override
    public void onResume() {
        super.onResume();
//        ManAnalyticsPageHit.getInstance().pageAppear();
    }

    protected boolean isVisible;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {

    }

    protected void onInvisible() {
    }

    /**
     * 阿里事件统计额外参数配置，
     */
    public void ALAnalytics() {
        String pageName = PageNameUtil.getPageName(getClass().getSimpleName());
//        ManAnalyticsPageHit.getInstance().updatePageProperties("pageName", pageName);
    }
}
