package com.yin.yzjcourse.DiyWidget;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixMulActivity extends BaseActivity {
//    @BindView(R.id.bt_fore_service)
//    Button btForeService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_mul);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
//        float pivotX = 100;
//        float pivotY = 200;
//        float angle = 90;
//        Matrix matrix = new Matrix();
//        matrix.preTranslate(pivotX,pivotY);
//        matrix.preRotate(angle);
//        DLog.eLog("第一个："+matrix.toShortString());
//
//        Matrix matrix2 = new Matrix();
//        matrix2.postRotate(angle);
//        matrix2.postTranslate(pivotX,pivotY);
//        DLog.eLog("第2个："+matrix.toShortString());

        Matrix matrix = new Matrix();
        matrix.postScale(0.5f, 0.8f);
        matrix.preTranslate(1000, 1000);
        DLog.eLog("第2个："+matrix.toShortString());
    }

//    @OnClick({R.id.bt_pre_post})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.bt_pre_post:
//
//                break;
//        }
//    }
}
