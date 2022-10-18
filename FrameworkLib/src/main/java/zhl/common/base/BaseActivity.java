package zhl.common.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.CachePolicy;
import com.lidroid.xutils.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import zhl.common.request.RequestListener;
import zhl.common.request.RequestManager;
import zhl.common.request.ZHLRequest;
import zhl.common.utils.Tools;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    protected ViewGroup mContentView;
    protected Context mContext;
    protected BaseActivity mActivity;
    protected Handler mBaseHandler;
    public boolean openPkPush;
    /**
     * 状态栏文字图标颜色是否加深
     */
    protected boolean useLightStatusBarColor = true;
    protected boolean shouldFitSystemWindow = true;

    /**
     * 是否用系统尺寸
     */
    protected boolean isSystem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mActivity = this;
        AppManager.getInstance().addActivity(this); //添加到栈中
        addScreenAdapter();
        mBaseHandler = new Handler();

        setContentView(R.layout.activity_base_title);
        fullScreen();
    }

    public void setTitle(String title) {
        setTitle(title, "", 0);
    }

    private void setTitle(String title, String titleRight, @DrawableRes int rightIcon) {
        FrameLayout frameLayout = findViewById(R.id.layout_title);
        if (frameLayout != null) {
            View back = findViewById(R.id.iv_back);
            back.setOnClickListener(this);
            TextView textTitle = findViewById(R.id.title);
            textTitle.setText(title);
            textTitle = findViewById(R.id.title_right);
            textTitle.setOnClickListener(this);

            if (rightIcon != 0) {
                textTitle.setBackgroundResource(rightIcon);
            } else {
                textTitle.setText(titleRight);
            }
        }
    }

    public void setTitle(String title, @DrawableRes int rightIcon) {
        setTitle(title, "", rightIcon);
    }

    public void setTitle(String title, String titleRight) {
        setTitle(title, titleRight, 0);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (mContentView == null && R.layout.activity_base_title == layoutResID) {
            super.setContentView(layoutResID);
            mContentView = (FrameLayout) findViewById(R.id.fl_content);
        } else if (layoutResID != R.layout.activity_base_title) {
            View contentChildView = LayoutInflater.from(this).inflate(layoutResID, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                contentChildView.setFitsSystemWindows(shouldFitSystemWindow);
            }
            mContentView.removeAllViews();
            mContentView.addView(contentChildView, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        addScreenAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        addScreenAdapter();
    }

    /**
     * 屏幕适配方案
     */
    public void addScreenAdapter() {
        // 横竖屏对应的适配标准不一样
//        if (isHorizontal()) {
//            DeviceUtils.setCustomDensity(mActivity, mActivity.getApplication(), true, isSystem);
//        } else {
//            DeviceUtils.setCustomDensity(mActivity, mActivity.getApplication(), false, isSystem);
//        }
    }

    private boolean isHorizontal() {
        Configuration mConfiguration = this.getResources().getConfiguration(); //获取设置的配置信息
        int ori = mConfiguration.orientation; //获取屏幕方向
        if (ori == mConfiguration.ORIENTATION_LANDSCAPE) {
            //横屏
            return true;
        } else if (ori == mConfiguration.ORIENTATION_PORTRAIT) {
            //竖屏
            return false;
        }
        return false;
    }

    /**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     *
     * @param context
     * @return 平板返回 True，手机返回 False
     */
    public static boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private static int loadingLayout; // 外部实现的loading方式

    /**
     * 设置外部定制的loading布局 </br>
     * 如果布局中包含逐帧动画，请将View的id设置成SDK配置的：sdk_framework_loading_anima_id</br>
     * 逐帧动画应该配置在:该view的background中[@anim/xxxx]
     *
     * @param loadingLayout
     * @author zqs
     * @createTime 2015年3月5日 下午6:43:17
     */
    public static void setLoadingLayout(int loadingLayout) {
        BaseActivity.loadingLayout = loadingLayout;
    }


    public void showDataErrorDialog(String msg, android.content.DialogInterface.OnClickListener onClickListener) {
        if (msg == null || msg.equals(""))
            msg = "网络异常";
        new ErrorDialogFragment.ErrorDialogFragmentBuilder(this).setMessage(msg).setOnClickListener(onClickListener).show();
    }

    public void showLoadingDialog() {
        new ProgressDialogFragment.ProgressDialogFragmentBuilder(this, loadingLayout).show();
    }

    public void showLoadingDialog(String message) {
        new ProgressDialogFragment.ProgressDialogFragmentBuilder(this, loadingLayout).setMessage(message).show();
    }

    public void hideLoadingDialog() {
        ProgressDialogFragment.dismiss(this);
    }

    /**
     * 带有loading的任务
     *
     * @param request
     * @param listener
     * @param policy
     */
    public void executeLoadingCanStop(final ZHLRequest request, RequestListener listener, CachePolicy policy) {
        new ProgressDialogFragment.ProgressDialogFragmentBuilder(this, loadingLayout).setOnCancelListener(new DialogInterface.OnCancelListener() {
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
        new ProgressDialogFragment.ProgressDialogFragmentBuilder(this, loadingLayout).setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
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
    protected void onDestroy() {
        mBaseHandler.removeCallbacksAndMessages(null);
        RequestManager.cancleAllByTag(this.hashCode());
        AppManager.getInstance().finishActivity(this);
        super.onDestroy();
    }


    public void toast(String text) {
        if (!Tools.isEmpty(text)) {
            toastSimpleMessage(this, text);
//            Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
//            toast.show();
        }
    }

    public void toast(int resId) {
        Toast toast = Toast.makeText(this, resId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 简单的toast
     *
     * @param context
     * @param text
     */
    public static void toastSimpleMessage(Context context, String text) {
        Toast toast = getToast(context, text);
        toast.show();
    }

    public static Toast getToast(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.simple_toast, null);
        TextView title = view.findViewById(R.id.tv_title);
        title.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        return toast;
    }

    public static Toast getToastStyle2(Context context, String text) {
        View view = LayoutInflater.from(context).inflate(R.layout.toast_select_agreement_layout, null);
        TextView title = view.findViewById(R.id.tv_content);
        title.setText(text);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        return toast;
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_back) {
            finish();
        }

    }

    public void initComponentEvent() {
    }


    public void initComponentValue() {
    }


    protected void fullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
            if (useLightStatusBarColor) {
                //android6.0以后可以对状态栏文字颜色和图标进行修改
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.setSystemUiVisibility(option | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else if (!flymeSetStatusBarLightMode(window, true) &&
                        !mIUISetStatusBarLightMode(window, true)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        try {
                            //黑色状态栏
                            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(Color.BLACK);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void setStatusBarColor(int color) {
        Window window = getWindow();
        //After LOLLIPOP not translucent status bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //Then call setStatusBarColor.
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(color));
    }

    protected void setUseLightStatusBarColor(boolean isLight) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//                window.setStatusBarColor(Color.TRANSPARENT);
//            } else {
//                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            }
            if (isLight) {
                //android6.0以后可以对状态栏文字颜色和图标进行修改
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.setSystemUiVisibility(option | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                } else if (!flymeSetStatusBarLightMode(window, true)) {
                    mIUISetStatusBarLightMode(window, true);
                }
            }
        }
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */

    public static boolean flymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @param window 需要设置的窗口
     * @param dark   是否把状态栏字体及图标颜色设置为深色
     * @return boolean 成功执行返回true
     */
    public static boolean mIUISetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);//清除黑色字体
                }
                result = true;
            } catch (Exception e) {

            }
        }
        return result;
    }

    public ViewGroup getmContentView() {
        return mContentView;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove掉保存的Fragment
            outState.remove(FRAGMENTS_TAG);
        }
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        if (resources != null && resources.getConfiguration().fontScale != 1.0f) {
            android.content.res.Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }
}
