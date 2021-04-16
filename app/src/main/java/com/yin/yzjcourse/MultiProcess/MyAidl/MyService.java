package com.yin.yzjcourse.MultiProcess.MyAidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

//import com.yin.yzjcourse.IMyAidl;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
//    private ArrayList<Person> mPersons;

    /**
     * 为了实现跨进程通信，我在清单文件中给这个Service设置了android:Process属性，使这个服务运行在另一个进程中
     * 这样，我就可以在该应用的原始进程实现跨进程访问了，当然，你完全可以重新写一个应用，让我们的应用远程绑定你
     * 重新写的应用的服务，原理都是一样的，但是这两个应用要有完全一样的aidl，且以服务端的aidl为准，我说的完全一样
     * 包括包路径都要一样。这样客户端和服务端才能达成一致。
     *
     *
     * 这个IMyAidl.java就是IMyAidl.aidl生成的,IMyAidl.Stub自动继承了Binder，Binder又实现了IBinder.
     *
     * 这里以匿名类的方式获取一个IBinder实例，也可以以内部类的方式。
     *
     * 重写这个IMyAidl的接口方法，之后客户端与该服务建立连接后，客户端会获取该IBinder实例，
     * 当然可以访问该实例的方法，例如访问addPerson()getPersonList()等等，这样也就实现了跨进程通信。
     */
//    private IBinder mIBinder = new IMyAidl.Stub() {
//
//        @Override
//        public void addPerson(Person person) throws RemoteException {
//            mPersons.add(person);
//        }
//
//        @Override
//        public List<Person> getPersonList() throws RemoteException {
//            return mPersons;
//        }
//    };

    /**
     * 当有远程客户端绑定这个服务时，就把mIBinder返回给客户端，客户端可通过这个mIBinder调用服务端的方法，
     * 实现跨进程通信。
     * @param intent
     * @return
     */
    @Override
    public IBinder onBind(Intent intent) {
//        mPersons = new ArrayList<>();
        return null;
    }
}
