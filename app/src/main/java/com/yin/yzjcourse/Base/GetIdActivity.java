package com.yin.yzjcourse.Base;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.utils.DLog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetIdActivity extends BaseActivity {

    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_id);
        ButterKnife.bind(this);

        /**
         * 获取图片资源id
         *
         * 第一个参数：资源名称；
         * 第二个参数：资源类型，drawable-...统称为drawable、 mipmap-...统称为mipmap；
         * 第三个参数：包名；
         */
//        int imgResId = getResources().getIdentifier("zyjj_top_bg", "mipmap", getPackageName()); 也可以
        int imgResId = getResources().getIdentifier("zyjj_top_bg", "drawable", getPackageName());
        ivImg.setImageResource(imgResId);

        /**
         * 获取字符串资源id
         * 参数同理；
         */
        int stringId = getResources().getIdentifier("et_user", "string", getPackageName());
        tvContent.setText(stringId);

        /**
         * 获取布局文件的资源id
         * 参数同理；
         */
        int layoutId = getResources().getIdentifier("activity_canvas_rotate", "layout", getPackageName());
        DLog.eLog("布局资源id："+layoutId);
    }
}
