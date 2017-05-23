package com.yin.yzjcourse.DiyWidget.ViewAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FourAnimInterpolatorActivity extends AppCompatActivity {

    @BindView(R.id.bt_scale)
    Button btScale;
    @BindView(R.id.bt_rotate)
    Button btRotate;
    @BindView(R.id.bt_alpha)
    Button btAlpha;
    @BindView(R.id.bt_trans)
    Button btTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_anim_interpolator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_scale, R.id.bt_rotate, R.id.bt_alpha, R.id.bt_trans})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.bt_scale:
                bundle.putString(Utils.ANIM_TYPE_KEY,Utils.ANIM_TYPE_SCALE);
                break;
            case R.id.bt_rotate:
                bundle.putString(Utils.ANIM_TYPE_KEY,Utils.ANIM_TYPE_ROTATE);
                break;
            case R.id.bt_alpha:
                bundle.putString(Utils.ANIM_TYPE_KEY,Utils.ANIM_TYPE_ALPHA);
                break;
            case R.id.bt_trans:
                bundle.putString(Utils.ANIM_TYPE_KEY,Utils.ANIM_TYPE_TRANSLATE);
                break;
        }
        Utils.startActivity(this,AnimInterpolatorActivity.class,bundle);
    }
}
