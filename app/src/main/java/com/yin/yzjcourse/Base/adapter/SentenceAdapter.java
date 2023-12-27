package com.yin.yzjcourse.Base.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.EnglishSentenceEntity;

import java.util.List;

/**
 *
 */
public class SentenceAdapter extends BaseQuickAdapter<EnglishSentenceEntity, BaseViewHolder> {
    public SentenceAdapter(int layoutResId, @Nullable List<EnglishSentenceEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EnglishSentenceEntity item) {
//        helper.setText(R.id.tv_title, item.title);
//        helper.setText(R.id.tv_content, item.content);
//        if (item.title.equals(mData.get(mData.size() - 1).title)) { //最后一条
//            helper.getView(R.id.view_line).setVisibility(View.INVISIBLE);
//        } else {
//            helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
//        }
//        ImageView imageView = helper.getView(R.id.iv_content);
//        if (!TextUtils.isEmpty(item.image)) {
//            imageView.setVisibility(View.VISIBLE);
//            RequestOptions options = RequestOptions.bitmapTransform(new RoundedCorners(DisplayUtils.dp2px(mContext,
//                    4))).placeholder(R.drawable.default_head);
//            Glide.with(mContext).load(item.image).apply(options).into(imageView);
//        }else{
//            imageView.setVisibility(View.GONE);
//        }
        AppCompatTextView tvDate = helper.getView(R.id.tv_date);
        AppCompatButton btTranslate = helper.getView(R.id.bt_translate);
        AppCompatTextView tvEnglish = helper.getView(R.id.tv_english);
        AppCompatTextView tvChinese = helper.getView(R.id.tv_chinese);
        tvDate.setText((helper.getLayoutPosition()+1)+"->"+item.date);
        tvEnglish.setText(item.english);
        tvChinese.setText(item.chinese);
        btTranslate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(tvChinese.getVisibility() == View.VISIBLE){
                    tvChinese.setVisibility(View.GONE);
                }else {
                    tvChinese.setVisibility(View.VISIBLE);
                }
            }
        });

    }
//    public void clearAllData(){
//        mData.clear();
//        notifyDataSetChanged();
//    }
}
