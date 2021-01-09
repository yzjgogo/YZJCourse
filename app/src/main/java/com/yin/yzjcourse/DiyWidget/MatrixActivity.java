package com.yin.yzjcourse.DiyWidget;

import android.content.Intent;
import android.graphics.Matrix;
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
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

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

    @OnClick({R.id.bt_pre_post,R.id.bt_new_1,R.id.bt_new_2,R.id.bt_set_values,R.id.bt_get_values,R.id.bt_equals,R.id.bt_set,R.id.bt_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_pre_post:
                startActivity(new Intent(this,MatrixMulActivity.class));
                break;
            case R.id.bt_new_1:
                Matrix matrix = new Matrix();
                DLog.eLog("无参构造器："+matrix.toString());
                break;
            case R.id.bt_new_2:
                Matrix oriMatrix = new Matrix();
                oriMatrix.setValues(new float[]{11,22,33,44,55,66,77,88,99});
                Matrix matrix1 = new Matrix(oriMatrix);
                DLog.eLog("有参构造器："+matrix1.toString());
                break;
            case R.id.bt_set_values:
                Matrix sMatrix = new Matrix();
                sMatrix.setValues(new float[]{1,2,3,4,5,6,7,8,9,10,11});
                DLog.eLog("设置值："+sMatrix.toString());
                break;
            case R.id.bt_get_values:
                Matrix gMatrix = new Matrix();
                gMatrix.setValues(new float[]{1,2,3,4,5,6,7,8,9,10,11});
                float[] values = new float[9];
                gMatrix.getValues(values);
                DLog.eLog("获取值："+ Arrays.toString(values));
                break;
            case R.id.bt_equals:
                Matrix matrixA = new Matrix();
                matrixA.setValues(new float[]{1,2,3,4,5,6,7,8,9,10,11});
                Matrix matrixB = new Matrix();
                matrixB.setValues(new float[]{1,2,3,4,5,6,7,8,9});
                //比较9个值是否一一相等
                DLog.eLog("是否相等："+ matrixA.equals(matrixB));
                break;
            case R.id.bt_set:
                Matrix matrixC = new Matrix();
                matrixC.setValues(new float[]{1,2,3,4,5,6,7,8,9});

                Matrix matrixD = new Matrix();
                matrixD.setValues(new float[]{11,22,33,44,55,66,77,88,98});

                //将matrixC的9个值一次赋值给matrixD的9个值
                matrixD.set(matrixC);
                DLog.eLog("设置一个非空矩阵："+matrixD.toString());

                //将matrixD重置为单位矩阵，等价于reset(),进去看源码确实是这样
                matrixD.set(null);
                DLog.eLog("设置一个空矩阵："+matrixD.toString());

                break;
            case R.id.bt_reset:
                Matrix matrixE = new Matrix();
                matrixE.setValues(new float[]{1,2,3,4,5,6,7,8,9});
                matrixE.reset();
                DLog.eLog("调用reset:"+matrixE.toString());
                break;
        }
    }
}
