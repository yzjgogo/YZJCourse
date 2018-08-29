package com.yin.yzjcourse.Base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpanActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        ButterKnife.bind(this);
        tv.setText(handleStyle(this,"*我叫","刘德华"));
    }

    private SpannableStringBuilder handleStyle(Context context, String prescriptionName, String totalMedicines) {
        SpannableStringBuilder style = new SpannableStringBuilder(prescriptionName + totalMedicines);
        //设置不同的字体颜色，可调用多次
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.title_color)),
                0, prescriptionName.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.hint_text_color)),
                prescriptionName.length(), (prescriptionName + totalMedicines).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置不同的字体大小，可调用多次
        style.setSpan(new AbsoluteSizeSpan(Utils.dip2px(context, 16)),
                0, prescriptionName.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(Utils.dip2px(context, 14)),
                prescriptionName.length(), (prescriptionName + totalMedicines).length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //给文字设置图标
        Drawable myDrawable = ContextCompat.getDrawable(context,R.mipmap.ic_launcher);
        myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        ImageSpan imgSpan = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
        style.setSpan(imgSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }
}
