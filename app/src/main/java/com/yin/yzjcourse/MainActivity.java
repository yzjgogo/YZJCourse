package com.yin.yzjcourse;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.yin.yzjcourse.Base.BaseContentActivity;
import com.yin.yzjcourse.Base.DailyEnglishActivity;
import com.yin.yzjcourse.Base.bluetooth.MyBlueToothActivity;
import com.yin.yzjcourse.BuilderMode.BuilderModeActivity;
import com.yin.yzjcourse.Coordinator.CoordinatorActivity;
import com.yin.yzjcourse.DataBindP.DataBindSimplesActivity;
import com.yin.yzjcourse.DesignModel.DesignModelListActivity;
import com.yin.yzjcourse.DiyWidget.DiyViewActivity;
import com.yin.yzjcourse.DiyWidget.DrawView.DrawViewActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.AnimatorSetActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimInterpolatorActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyValuesHolderActivity;
import com.yin.yzjcourse.DiyWidget.ViewAnimateActivity;
import com.yin.yzjcourse.DiyWidget.ViewAnimation.XmlAnimActivity;
import com.yin.yzjcourse.DiyWidget.ViewCompatAnimateActivity;
import com.yin.yzjcourse.DiyWidget.ViewGroupAnimActivity;
import com.yin.yzjcourse.ForeService.ForeServiceActivity;
import com.yin.yzjcourse.Jetpack.JetpackHomeActivity;
import com.yin.yzjcourse.MaterialDesign.MaterialDesignActivity;
import com.yin.yzjcourse.MathAbout.MathAboutActivity;
import com.yin.yzjcourse.MultiProcess.AndroidMulti.MultiMainActivity;
import com.yin.yzjcourse.MultiProcess.MultiActivity;
import com.yin.yzjcourse.Net.JsonHp;
import com.yin.yzjcourse.Net.NetActivity;
import com.yin.yzjcourse.OfficialWeight.OfficialWeightActivity;
import com.yin.yzjcourse.Optimize.OptimizeActivity;
import com.yin.yzjcourse.RxJava2.RxRelease2Activity;
import com.yin.yzjcourse.Window.MyWindowActivity;
import com.yin.yzjcourse.mykt.MyKtActivity;
import com.yin.yzjcourse.structure.DataStructureActivity;
import com.yin.yzjcourse.tools.ToolsActivity;
import com.yin.yzjcourse.utils.PlayService;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;

//从公司提交代码
public class MainActivity extends BaseActivity {
    //试试我的台式机
//    @BindView(R.id.bt_fore_service)
//    Button btForeService;
//    @BindView(R.id.bt_dialog_fragment)
//    Button btDialogFragment;
//    @BindView(R.id.bt_rxjava)
//    Button btRxjava;
//    @BindView(R.id.bt_diy_view)
//    Button btDiyView;
//    @BindView(R.id.bt_xml_anim)
//    Button btXmlAnim;
//    @BindView(R.id.bt_property_anim)
//    Button btPropertyAnim;
//    @BindView(R.id.activity_main)
//    LinearLayout activityMain;
//    @BindView(R.id.sv)
//    ScrollView sv;

    @BindView(R.id.sv)
    ScrollView sv;

    @BindView(R.id.et_time)
    AppCompatEditText et_time;

    @BindView(R.id.et_interval)
    AppCompatEditText et_interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int readStorage = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeStorage = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int callLog = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG);
        int coarseLocation = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int fineLocation = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(readStorage!= PackageManager.PERMISSION_GRANTED
                || writeStorage!=PackageManager.PERMISSION_GRANTED
                || callLog!=PackageManager.PERMISSION_GRANTED
                || coarseLocation!=PackageManager.PERMISSION_GRANTED
                || fineLocation!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                Toast.makeText(MainActivity.this,"权限申请成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 打开当前APP
     */
    public static void openApp(Context context, String app_package, String strToken, String strData) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(app_package);
            if (intent != null) {
                intent.putExtra("token", strToken);
                intent.putExtra("data", strData);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.bt_fore_service, R.id.bt_dialog_fragment, R.id.bt_rxjava,
            R.id.bt_diy_view, R.id.bt_xml_anim, R.id.bt_property_anim, R.id.bt_property_anim_interpolator, R.id.bt_object_anim_holder
            , R.id.bt_anim_set, R.id.bt_anim_draw, R.id.bt_builder_mode, R.id.bt_math, R.id.bt_data_bind, R.id.bt_weight, R.id.bt_material_design,
            R.id.bt_coordinator, R.id.bt_android_message, R.id.bt_anim_group, R.id.bt_optimize, R.id.bt_tools, R.id.bt_net,R.id.bt_kotlin,
    R.id.bt_model,R.id.bt_multi_process,R.id.bt_window,R.id.bt_jetpack,R.id.bt_view_animate_1,R.id.bt_view_animate_2,R.id.bt_data_structure,R.id.bt_bluetooth,R.id.bt_english,R.id.bt_english_service})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_fore_service:
//                startActivity(new Intent(this, DraftActivity.class));
//                Integer i = null;
//                i.toString();
//                Intent intent = new Intent(this, ForeServiceActivity.class);
//                startActivity(intent);
                TokenEntity tokenEntity = new TokenEntity();
//                tokenEntity.access_token = "426e7baab1b276bcfd6fe403fa2917415997c52295e013e81fdb76ab88c2a9f9";
                tokenEntity.access_token = "f5c197d8447a02bdaeadf1ff3ae6f6b509da9f24d21fdec868bd5d410db2cace";
//                tokenEntity.refresh_token = "f16bf55b5a19e36c6a777f13ef1d08 c05997c52295e013e81fdb76ab88c2a9f9";
                tokenEntity.refresh_token = "6fd68bd984c21ea36881457a78d1e4d709da9f24d21fdec868bd5d410db2cace";
                tokenEntity.token_type = "Bearer";
                tokenEntity.expires_in = 5183999;
                tokenEntity.account = "18117843721";
                String strToken = JsonHp.getGsonConverter().toJson(tokenEntity);

                String strData = "{\"op_type\":100001,\"remark\":\"https://zhledu.cdn.bcebos.com/ktb/usercourse/a5c89bf2-5cfd-428a-b727-f5e4d3195612\",\"remark_one\":\"https://zhledu.cdn.bcebos.com/ktb/usercourse/609557f0-15b3-464e-bb0c-d16d9edef043\",\"source_id\":7795}";

                openApp(MainActivity.this,"com.zhl.enteacher.tv",strToken,strData);

                break;
            case R.id.bt_dialog_fragment:
//                Toast.makeText(this,"status code:502,reason phrase:Bad Gateway",Toast.LENGTH_SHORT).show();
                CourseDialog dialog = CourseDialog.getInstance();
                dialog.show(getSupportFragmentManager(), "CourseDialog");
                break;
            case R.id.bt_rxjava:
                Intent intent1 = new Intent(this, RxRelease2Activity.class);
                startActivity(intent1);
                break;
            case R.id.bt_diy_view:
                Intent intent2 = new Intent(this, DiyViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_xml_anim:
                Intent intent3 = new Intent(this, XmlAnimActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_property_anim:
                startActivity(new Intent(this, PropertyAnimActivity.class));
                break;
            case R.id.bt_property_anim_interpolator:
                startActivity(new Intent(this, PropertyAnimInterpolatorActivity.class));
                break;
            case R.id.bt_object_anim_holder:
                startActivity(new Intent(this, PropertyValuesHolderActivity.class));
                break;
            case R.id.bt_anim_set:
                startActivity(new Intent(this, AnimatorSetActivity.class));
                break;
            case R.id.bt_anim_group:
                startActivity(new Intent(this, ViewGroupAnimActivity.class));
                break;
            case R.id.bt_view_animate_1:
                startActivity(new Intent(this, ViewAnimateActivity.class));
                break;
            case R.id.bt_view_animate_2:
                startActivity(new Intent(this, ViewCompatAnimateActivity.class));
                break;
            case R.id.bt_anim_draw:
                startActivity(new Intent(this, DrawViewActivity.class));
                break;
            case R.id.bt_builder_mode:
                startActivity(new Intent(this, BuilderModeActivity.class));
                break;
            case R.id.bt_math:
                startActivity(new Intent(this, MathAboutActivity.class));
                break;
            case R.id.bt_data_bind:
                startActivity(new Intent(this, DataBindSimplesActivity.class));
                break;
            case R.id.bt_weight:
                startActivity(new Intent(this, OfficialWeightActivity.class));
                break;
            case R.id.bt_material_design:
                startActivity(new Intent(this, MaterialDesignActivity.class));
                break;
            case R.id.bt_coordinator:
                startActivity(new Intent(this, CoordinatorActivity.class));
                break;
            case R.id.bt_android_message:
                startActivity(new Intent(this, BaseContentActivity.class));
                break;
            case R.id.bt_optimize:
                startActivity(new Intent(this, OptimizeActivity.class));
                break;
            case R.id.bt_tools:
                startActivity(new Intent(this, ToolsActivity.class));
                break;
            case R.id.bt_net:
                startActivity(new Intent(this, NetActivity.class));
//                ARouter.getInstance().build("/test/net").navigation();
                break;
            case R.id.bt_kotlin:
//                ARouter.getInstance().build("/test/push").navigation();
                startActivity(new Intent(this, MyKtActivity.class));
                break;
            case R.id.bt_model:
//                ARouter.getInstance().build("/test/learn").navigation();
                startActivity(new Intent(this, DesignModelListActivity.class));
                break;
            case R.id.bt_multi_process:
                startActivity(new Intent(this, MultiActivity.class));
                break;
            case R.id.bt_window:
                startActivity(new Intent(this, MyWindowActivity.class));
                break;
            case R.id.bt_jetpack:
                startActivity(new Intent(this, JetpackHomeActivity.class));
                break;
            case R.id.bt_data_structure:
                startActivity(new Intent(this, DataStructureActivity.class));
                break;
            case R.id.bt_bluetooth:
                startActivity(new Intent(this, MyBlueToothActivity.class));
                break;
            case R.id.bt_english:
                startActivity(new Intent(this, DailyEnglishActivity.class));
                break;
            case R.id.bt_english_service:
                MyApplication.startTimes = System.currentTimeMillis();
                MyApplication.durationTime = Integer.parseInt(et_time.getText().toString().trim());
                MyApplication.intervalTime = Integer.parseInt(et_interval.getText().toString().trim());
                Intent mIntent = new Intent(getApplicationContext(), PlayService.class);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    startForegroundService(mIntent);
                }else{
                    startService(mIntent);
                }
                break;
        }
    }

    /**
     * jie1()和jie2()没有调用关系是兄弟关系
     */
    private void startTrace() {
        jie1();
        jie2();
    }

    /**
     * jie2()中两次调用jie3()，其中jie3(0)直接return，不产生递归也不会调用jie4()
     * jie3(3)会先调用一次jie4()再产生3次递归调用
     */
    private void jie2() {
        jie3(0);
        jie3(3);
    }

    private void jie3(int count) {
        if (count == 3) {
            jie4();
        }
        if (count == 0) {
            return;
        } else {
            jie3(count - 1);
        }
    }

    /**
     * 故意做比较耗时的操作：用于区分Excl和Incl的关系
     */
    private void jie4() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int k = i + j;
            }
        }
    }

    private void jie1() {

    }

    public class TokenEntity implements Serializable {
        public String access_token;// 验证的token
        public String token_type;//token类型
        public long expires_in;//超时时间
        public String refresh_token;//刷新token
        public String account;//当前账户
    }
}
