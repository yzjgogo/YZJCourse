package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.yin.yzjcourse.Base.BaseContentActivity;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.BuilderMode.BuilderModeActivity;
import com.yin.yzjcourse.Coordinator.CoordinatorActivity;
import com.yin.yzjcourse.CourseDialog;
import com.yin.yzjcourse.DataBindP.DataBindSimplesActivity;
import com.yin.yzjcourse.DesignModel.DesignModelListActivity;
import com.yin.yzjcourse.DiyWidget.DrawView.DrawViewActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.AnimatorSetActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyAnimInterpolatorActivity;
import com.yin.yzjcourse.DiyWidget.PropertyAnimation.PropertyValuesHolderActivity;
import com.yin.yzjcourse.DiyWidget.ViewAnimation.XmlAnimActivity;
import com.yin.yzjcourse.ForeService.ForeServiceActivity;
import com.yin.yzjcourse.Jetpack.JetpackHomeActivity;
import com.yin.yzjcourse.MaterialDesign.MaterialDesignActivity;
import com.yin.yzjcourse.MathAbout.MathAboutActivity;
import com.yin.yzjcourse.MultiProcess.MultiActivity;
import com.yin.yzjcourse.Net.NetActivity;
import com.yin.yzjcourse.OfficialWeight.OfficialWeightActivity;
import com.yin.yzjcourse.Optimize.OptimizeActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.RxJava2.RxRelease2Activity;
import com.yin.yzjcourse.Window.MyWindowActivity;
import com.yin.yzjcourse.mykt.MyKtActivity;
import com.yin.yzjcourse.structure.DataStructureActivity;
import com.yin.yzjcourse.tools.ToolsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixActivity extends BaseActivity {
//    @BindView(R.id.bt_fore_service)
//    Button btForeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }

    @OnClick({R.id.bt_pre_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pre_post:
                startActivity(new Intent(this,MatrixMulActivity.class));
                break;
        }
    }
}
