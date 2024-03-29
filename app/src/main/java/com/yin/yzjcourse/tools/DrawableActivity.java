package com.yin.yzjcourse.tools;

import android.graphics.drawable.ClipDrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Android开发艺术探索
 */
public class DrawableActivity extends BaseActivity {
//    @BindView(R.id.progress_loading)
//    ProgressBar progress_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_clip,R.id.bt_state,R.id.bt_level})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clip:
                //参考：clip_img.xml 了解剪裁方向
                ImageView ivClip = (ImageView) findViewById(R.id.iv_clip);
                ClipDrawable clipDrawable = (ClipDrawable) ivClip.getDrawable();
                //level的值的范围是[0,10000],表示被剪裁出来的部分(保留的可见的部分)占万分之几，0代表完全剪裁完全不可见，10000代表不剪裁完全可见；
                //因此通过动态改变level的值，可以实现进度条效果
                clipDrawable.setLevel(4000);
                break;
            case R.id.bt_state:
                StateListDrawable stateListDrawable = (StateListDrawable) findViewById(R.id.view_state).getBackground();
                DLog.eLog("获取状态："+ Arrays.toString(stateListDrawable.getState()));
                findViewById(R.id.view_state2).setBackgroundResource(stateListDrawable.getState()[2]);
                break;
            case R.id.bt_level:
//                LevelListDrawable listDrawable = (LevelListDrawable) findViewById(R.id.view_level).getBackground();
//                listDrawable.setLevel(1);

                ImageView ivLevel = (ImageView) findViewById(R.id.iv_level);
                ivLevel.setImageLevel(1);
                break;
        }
    }
}
