package com.yin.yzjcourse.tools;

import android.graphics.drawable.ClipDrawable;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrawableActivity extends BaseActivity {
//    @BindView(R.id.progress_loading)
//    ProgressBar progress_loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_clip})
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
        }
    }
}
