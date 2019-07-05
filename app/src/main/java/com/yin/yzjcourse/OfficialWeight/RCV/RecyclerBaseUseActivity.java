package com.yin.yzjcourse.OfficialWeight.RCV;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.OfficialWeight.RCV.adapter.RcvBaseUseAdapter;
import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.RcvBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerBaseUseActivity extends BaseActivity {

    @BindView(R.id.rcv)
    RecyclerView rcv;
    private List<RcvBean> rcvBeanList;
    private RcvBaseUseAdapter adapter;

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
        adapter = new RcvBaseUseAdapter(rcvBeanList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv.setLayoutManager(manager);
        rcv.setAdapter(adapter);
    }

    private void getListData() {
        for (int i=0;i<21;i++){
            rcvBeanList.add(new RcvBean("刘德华"+i));
        }
    }
}
