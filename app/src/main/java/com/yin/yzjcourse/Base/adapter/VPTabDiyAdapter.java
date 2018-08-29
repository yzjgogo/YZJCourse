package com.yin.yzjcourse.Base.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;

import java.util.List;


public class VPTabDiyAdapter extends PagerAdapter {
    private List<View> views;
    private List<String> titles;
    private Context context;
    public VPTabDiyAdapter(List<View> views, List<String> titles,Context context) {
        this.views = views;
        this.titles = titles;
        this.context = context;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    /**
     * 在这里设置标题的样式，主要是利用CharSequence的特性进行修改
     * 注意SpannableStringBuilder实现了CharSequence
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return handleStyle(context,titles.get(position));
    }

    private SpannableStringBuilder handleStyle(Context context, String title) {
        SpannableStringBuilder style = new SpannableStringBuilder(" " + title);
        //设置字体颜色
        style.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context,R.color.no_authenticate_text_color)),
                1, title.length()+1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);

        //设置字体大小
        style.setSpan(new AbsoluteSizeSpan(Utils.dip2px(context, 18)),
                1, title.length()+1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        //给文字设置图标
        Drawable myDrawable = ContextCompat.getDrawable(context,R.mipmap.ic_launcher);
        myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
        ImageSpan imgSpan = new ImageSpan(myDrawable, ImageSpan.ALIGN_BASELINE);
        style.setSpan(imgSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }
}
