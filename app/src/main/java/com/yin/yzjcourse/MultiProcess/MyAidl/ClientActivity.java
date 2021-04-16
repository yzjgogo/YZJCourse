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
//import com.yin.yzjcourse.IMyAidl;
import com.yin.yzjcourse.R;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientActivity extends BaseActivity {
    @BindView(R.id.tv_result)
    TextView tvResult;
//    private IMyAidl mAidl;
    /**
     * 这是与服务链接的回调
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        /**
         * 返回一个IMyAidl实例(从服务端拿到的)
         * 这个返回的IMyAidl是一个Proxy代理，即服务端IMyAidl接口的代理，这个代理的构造参数是服务端的IBinder
         *
         * 注意：
         * asInterface会做一个判断，如果你绑定服务的动作是在同一个线程(客户度和服务端位于同一个进程)，则不会返
         * 回所谓服务端代理，而是直接返回服务端的Stub对象；
         * 如果是跨进程调用，则才返回服务端IMyAidl接口的代理Stub.proxy。
         *
         * @param name
         * @param service
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            mAidl = IMyAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            mAidl = null;
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


    /**
     * 前提客户端的IMyAidl虽然是在客户端本地，但是服务端IMyAidl的代理（Stub.proxy）
     *
     * 以getPersonList()为例：
     * 客户端本地调用IMyAidl(Stub.proxy代理,在在客户端)的getPersonList()方法,该方法内部会调用mRemote.transact(Stub.TRANSACTION_getPersonList, _data, _reply, 0)
     * 这个mRemote指向服务端的IBinder，第一个参数是客户端要顶用的服务端的方法的id，第二个参数是客户端要传递的参数(如果有，addPerson(person)就有)，
     * 第三个参数是服务端执行完客户端要调用的服务端方法后，如果有返回值就将返回值写入这个reply。
     * mRemote.transact(Stub.TRANSACTION_getPersonList, _data, _reply, 0)执行完后从_reply取出服务端的返回值，
     * 由Stub.proxy的getPersonList()返回。
     * 在mRemote.transact(Stub.TRANSACTION_getPersonList, _data, _reply, 0)才真正远程调用服务端的
     * onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags)方法，这个方法运行在服务端的Binder线程池，
     * 这个方法是真正调用服务端的getPersonList()的位置。
     */
    private void call() {
        Random random = new Random();
        Person person = new Person("yzj" + random.nextInt(10));

//        try {
//            mAidl.addPerson(person);
//            List<Person> personList = mAidl.getPersonList();
//            tvResult.setText(personList.toString());
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        }
    }
}
