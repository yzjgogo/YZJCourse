package com.yin.yzjcourse.OfficialWeight.RCV.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.RcvBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by think on 2016/4/14.
 */
public class RcvBaseUseAdapter extends RecyclerView.Adapter<RcvBaseUseAdapter.ViewHolder> {
    private List<RcvBean> rcvBeanList;
    private Context mContext;

    public RcvBaseUseAdapter(List<RcvBean> rcvBeanList, Context context) {
        this.mContext = context;
        this.rcvBeanList = rcvBeanList;
    }

    @Override
    public int getItemCount() {
        return rcvBeanList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rcv_base_use_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        bindItemData(i, viewHolder);
    }

    private void bindItemData(final int position, final ViewHolder itemHolder) {
        RcvBean bean = rcvBeanList.get(position);
        itemHolder.tvContent.setText(bean.name);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
