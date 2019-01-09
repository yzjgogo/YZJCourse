package com.yin.yzjcourse.MultiProcess.MyAidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.IMyAidl;
import com.yin.yzjcourse.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientActivity extends BaseActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
    private IMyAidl mAidl;
    /**
     * 这是与服务链接的回调
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //连接成功后可获取接口，这个接口是客户端和服务端都认可的，可通过该接口通信
            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidl = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_bind, R.id.bt_call})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_bind:
                bind();
                break;
            case R.id.bt_call:
                call();
                break;
        }
    }

    private void bind() {

        Intent intent1 = new Intent(getApplicationContext(), MyService.class);
        bindService(intent1, mConnection, BIND_AUTO_CREATE);

//        Intent intent = new Intent();
//        intent.setAction("com.yin.yzjcourse.remote_service");
//        intent.setPackage("com.yin.yzjcourse");
//        bindService(intent,mConnection,BIND_AUTO_CREATE);
    }


    private void call() {
        Random random = new Random();
        Person person = new Person("yzj" + random.nextInt(10));

        try {
            mAidl.addPerson(person);
            List<Person> personList = mAidl.getPersonList();
            tvResult.setText(personList.toString());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
