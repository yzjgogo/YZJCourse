package com.yin.yzjcourse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yin.yzjcourse.Base.AndroidMessageActivity;
import com.yin.yzjcourse.Base.BaseContentActivity;
import com.yin.yzjcourse.BuilderMode.BuilderModeActivity;
import com.yin.yzjcourse.Coordinator.CoordinatorActivity;
import com.yin.yzjcourse.DataBindP.DataBindSimplesActivity;
import com.yin.yzjcourse.DiyWidget.DiyViewActivity;
import com.yin.yzjcourse.DiyWidget.DrawView.DrawViewActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.AnimatorSetActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimInterpolatorActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyValuesHolderActivity;
import com.yin.yzjcourse.DiyWidget.ViewAnimation.XmlAnimActivity;
import com.yin.yzjcourse.ForeService.ForeServiceActivity;
import com.yin.yzjcourse.MaterialDesign.MaterialDesignActivity;
import com.yin.yzjcourse.MathAbout.MathAboutActivity;
import com.yin.yzjcourse.OfficialWeight.OfficialWeightActivity;
import com.yin.yzjcourse.RxJava2.RxRelease2Activity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//第一次从家里的电脑提交代码
public class MainActivity extends BaseActivity {
//试试我的台式机
    @BindView(R.id.bt_fore_service)
    Button btForeService;
    @BindView(R.id.bt_dialog_fragment)
    Button btDialogFragment;
    @BindView(R.id.bt_rxjava)
    Button btRxjava;
    @BindView(R.id.bt_diy_view)
    Button btDiyView;
    @BindView(R.id.bt_xml_anim)
    Button btXmlAnim;
    @BindView(R.id.bt_property_anim)
    Button btPropertyAnim;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_fore_service, R.id.bt_dialog_fragment, R.id.bt_rxjava,
            R.id.bt_diy_view, R.id.bt_xml_anim, R.id.bt_property_anim,R.id.bt_property_anim_interpolator,R.id.bt_object_anim_holder
    ,R.id.bt_anim_set,R.id.bt_anim_draw,R.id.bt_builder_mode,R.id.bt_math,R.id.bt_data_bind,R.id.bt_weight,R.id.bt_material_design,
    R.id.bt_coordinator,R.id.bt_android_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_fore_service:
                Integer i = null;
                i.toString();
                Intent intent = new Intent(this, ForeServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_dialog_fragment:
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
        }
    }
}
