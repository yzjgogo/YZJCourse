package com.yin.yzjcourse.Base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.yin.yzjcourse.Base.ThreadPool.TPActivity;
import com.yin.yzjcourse.Base.mysurface.SurfaceActivity;
import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_base_content);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_android_message, R.id.bt_title_menu,R.id.bt_vp,R.id.bt_span,R.id.bt_ht,R.id.bt_is,R.id.bt_tp,
            R.id.bt_surface,R.id.bt_iterator,R.id.bt_get_id,R.id.bt_sc_scroll,R.id.bt_act_dialog_xml,
            R.id.bt_act_dialog_dynamic,R.id.bt_phone_listen,R.id.bt_get_size})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_android_message:
                startActivity(new Intent(this, AndroidMessageActivity.class));
                break;
            case R.id.bt_title_menu:
                startActivity(new Intent(this, TitleMenuActivity.class));
                break;
            case R.id.bt_vp:
                startActivity(new Intent(this, VPFirstActivity.class));
                break;
            case R.id.bt_span:
                startActivity(new Intent(this, SpanActivity.class));
                break;
            case R.id.bt_ht:
                startActivity(new Intent(this, HThreadActivity.class));
                break;
            case R.id.bt_is:
                startActivity(new Intent(this, ISActivity.class));
                break;
            case R.id.bt_tp:
                startActivity(new Intent(this, TPActivity.class));
                break;
            case R.id.bt_surface:
                startActivity(new Intent(this, SurfaceActivity.class));
                break;
            case R.id.bt_iterator:
                startActivity(new Intent(this, IteratorActivity.class));
                break;
            case R.id.bt_get_id:
                startActivity(new Intent(this, GetIdActivity.class));
                break;
            case R.id.bt_sc_scroll:
                startActivity(new Intent(this, SvScrollActivity.class));
                break;
            case R.id.bt_act_dialog_xml:
                startActivity(new Intent(this, XMLDialogActivity.class));
                break;
            case R.id.bt_act_dialog_dynamic:
                startActivity(new Intent(this, DynamicDialogActivity.class));
                break;
            case R.id.bt_phone_listen:
                startActivity(new Intent(this, PhoneListenActivity.class));
                break;
            case R.id.bt_get_size:
                startActivity(new Intent(this, GetSizeActivity.class));
                break;
        }
    }

//    @OnClick(R.id.bt_android_message,R.id.bt_)
//    public void onClickMessage() {
//        startActivity(new Intent(this, AndroidMessageActivity.class));
//    }
}
