package com.yin.yzjcourse.OfficialWeight.RCV;

import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.OfficialWeight.RCV.Util.SpaceItemDecoration;
import com.yin.yzjcourse.OfficialWeight.RCV.adapter.RcvGridAdapter;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.Utils;
import com.yin.yzjcourse.bean.RcvBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerGridActivity extends BaseActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    private List<RcvBean> rcvBeanList;
    private RcvGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_base_use);
        ButterKnife.bind(this);
        initRcv();
    }

    private void initRcv() {
        rcvBeanList = new ArrayList<>();
        getListData();
        adapter = new RcvGridAdapter(rcvBeanList,this);
        //控制item的上下左右的空白空间
        rcv.addItemDecoration(new SpaceItemDecoration(Utils.dip2px(this, 20)));
        //orientation:水平或者垂直滚动
        //reverseLayout:是否反向布局
        GridLayoutManager manager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        rcv.setLayoutManager(manager);
        rcv.setAdapter(adapter);
    }

    private void getListData() {
        for (int i=0;i<51;i++){
            rcvBeanList.add(new RcvBean("刘德华"+i));
        }
    }
}
