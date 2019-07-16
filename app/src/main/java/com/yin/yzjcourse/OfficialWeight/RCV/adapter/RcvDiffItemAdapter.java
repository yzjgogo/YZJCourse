package com.yin.yzjcourse.OfficialWeight.RCV.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
public class RcvDiffItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_ITEM = 2;
    private List<RcvBean> rcvBeanList;
    private Context mContext;
    private String headData;

    public RcvDiffItemAdapter(List<RcvBean> rcvBeanList, Context context, String headData) {
        this.mContext = context;
        this.rcvBeanList = rcvBeanList;
        this.headData = headData;
    }

    @Override
    public int getItemCount() {
        return rcvBeanList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        }
        return TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View rootView = null;
        if (viewType == TYPE_HEAD) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_base_use_head, parent, false);
            viewHolder = new HeadViewHolder(rootView);

        } else if (viewType == TYPE_ITEM) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_base_use_item, parent, false);
            viewHolder = new ItemViewHolder(rootView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (getItemViewType(position) == TYPE_HEAD) {
            bindHead((HeadViewHolder) viewHolder, position);
        } else if (getItemViewType(position) == TYPE_ITEM) {
            bindItem((ItemViewHolder) viewHolder, position);
        }
    }

    private void bindHead(HeadViewHolder viewHolder, int position) {
        viewHolder.tvHead.setText(headData);
    }

    private void bindItem(ItemViewHolder viewHolder, int position) {
        RcvBean bean = rcvBeanList.get(position - 1);
        viewHolder.tvContent.setText(bean.name);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.ll_item)
        LinearLayout llItem;

        ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_head)
        TextView tvHead;
        @BindView(R.id.ll_head)
        LinearLayout llHead;

        HeadViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
