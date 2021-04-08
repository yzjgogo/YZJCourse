package com.yin.yzjcourse.Base;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpanActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @BindView(R.id.tv_suojin)
    TextView tv_suojin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span);
        ButterKnife.bind(this);
        tv.setText(handleStyle(this,"012345","6789请点击这里试试，再进入里面看看"));
        suojin();
    }

    private SpannableStringBuilder handleStyle(Context context, String prescriptionName, String totalMedicines) {
        SpannableStringBuilder style = new SpannableStringBuilder(prescriptionName + totalMedicines);

//        style.setSpan(new TypefaceSpan());

        //设置不同的字体颜色，可调用多次
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.title_color)),
                0, prescriptionName.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.hint_text_color)),
                prescriptionName.length(), (prescriptionName + totalMedicines).length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);



        //设置不同的字体大小，可调用多次
        style.setSpan(new AbsoluteSizeSpan(Utils.dip2px(context, 14)),
                0, prescriptionName.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        style.setSpan(new AbsoluteSizeSpan(Utils.dip2px(context, 20)),
                prescriptionName.length(), (prescriptionName + totalMedicines).length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);



        //给文字设置图标，如果调用多测，则只有最后一次有效；将指定区间的文字替换为一个图片，如果这个指定区间包含多种span,则替换为多个图片
        Drawable myDrawable = ContextCompat.getDrawable(context,R.mipmap.ic_launcher);
        myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        ImageSpan imgSpan = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
//        style.setSpan(imgSpan, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);将[0,1]的整个返回换成一个图片
        style.setSpan(imgSpan, 4, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//将[4,5]换成一个图片，[6,7]也换成一个图片，因为[4,5]和[6,7]位于不同的span



        //设置第一个ClickableSpan
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( @NonNull View widget) {
                Utils.showToast(SpanActivity.this,"点击了1");
            }
        };
        style.setSpan(clickableSpan,13,15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置第二个ClickableSpan，因此可设置多个，但是要有多个ClickableSpan实例
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick( @NonNull View widget) {
                Utils.showToast(SpanActivity.this,"点击了2");
            }
        };
        style.setSpan(clickableSpan2,21,23,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置第一个UnderlineSpan
        UnderlineSpan underlineSpan = new UnderlineSpan(){
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#00ff00"));
            }
        };
        style.setSpan(underlineSpan,13,15,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置第二个UnderlineSpan,因此可设置多个
        UnderlineSpan underlineSpan2 = new UnderlineSpan(){
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#0000ff"));
            }
        };
        style.setSpan(underlineSpan2,21,23,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 不设置点击不生效，如果不设置这个则ClickableSpan区域点击无响应
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        // 去掉点击后文字的背景色,因为ClickableSpan区域点击后默认会显示相同颜色的背景色，一般，我们应该去掉，这里设置这个背景色为透明色就算去掉了
        tv.setHighlightColor(Color.parseColor("#00000000"));
//        tv.setHighlightColor(Color.parseColor("#0000ff"));我们可以随意设置一个颜色，验证一下点击后背景色是不是这个颜色

        return style;
    }

    /**
     * 首行缩进span
     */
    public void suojin(){
        String content = tv_suojin.getText().toString().trim();
        SpannableString spannableString = new SpannableString(content);
        //26是tv_suojin的sp字体大小，第一个参数是第一行的缩进，第二个参数是非第一行之外所有行的缩进，这里是首行缩进两个字符
        LeadingMarginSpan.Standard what = new LeadingMarginSpan.Standard(spTopx(this,26*2),0);
        spannableString.setSpan(what, 10, 10, SpannableString.SPAN_INCLUSIVE_INCLUSIVE);
        tv_suojin.setText(spannableString);
    }

    public int spTopx(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
