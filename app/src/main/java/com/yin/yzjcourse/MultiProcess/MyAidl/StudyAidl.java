/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/yzjgogo/github/YZJCourse/app/src/main/aidl/com/yin/yzjcourse/IMyAidl.aidl
 */
package com.yin.yzjcourse.MultiProcess.MyAidl;

/**
 * aidl适用于同一个应用通过android:process属性实现的跨进程，也适用于两个完全不同的应用，
 * 如果是一个应用的不同进程，则其实aidl是定义在服务端，客户端复制一整套aidl接口的代码
 * 如果是两个应用，则要先在服务端定义aidl，然后客户端需要定义一份与服务端完全相同的一套aidl，包括包路径也要相同
 * ，都是服务端的包路径，这便于服务端识别。
 *
 * 这个文件是我们写的aidl接口生成的接口，该接口继承IInterface接口
 * 所有的接口要想在Binder中传输，必须继承IInterface接口。
 *
 * 该接口包括抽象内部类Stub,和aidl中定义的抽象方法。
 */
public interface StudyAidl extends android.os.IInterface {
    /**
     * Stub继承Binder，因此Stub是一个Binder
     * 也实现了外部接口StudyAidl
     */
    public static abstract class Stub extends android.os.Binder implements StudyAidl {
        //DESCRIPTOR是Binder的唯一标识
        private static final String DESCRIPTOR = "com.yin.yzjcourse.IMyAidl";

        /**
         * Construct the stub at attach it to the interface.
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * Cast an IBinder object into an com.yin.yzjcourse.IMyAidl interface,
         * generating a proxy if needed.
         *
         * 第一步：客户端绑定(远程或本地)服务成功后，调用IMyAidl.Stub.asInterface(service)
         * 用于将服务端的Binder对象Stub转换为客户端所需的AIDL接口类类型的对象StudyAidl，其实也是MyService中的mIBinder，
         * mIBinder已经实现了StudyAidl接口的addPerson(Person person)和getPersonList()方法，因此客户端ClientActivity获取到后
         * 可直接调用这两个方法。
         */
        public static StudyAidl asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            //客户端ClientActivity和服务端MyService位于同一进程，直接返回的是其实就是MyService的mIBinder
            //也就不存在跨进程通信，可直接使用
            if (((iin != null) && (iin instanceof StudyAidl))) {
                return ((StudyAidl) iin);
            }
            //客户端ClientActivity和服务端MyService位于不同进程，则返回远程Binder的代理Stub.Proxy.
            return new Stub.Proxy(obj);
        }

        //此方法用于返回当前的Binder对象。
        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        /**
         *  第三步：
         *  此方法运行在服务端的Binder线程池中。
         *  由客户端触发本地的Stub.Proxy的addPerson(Person person)和getPersonList()方法，
         *  这两个方法内部又会远程调用远程Binder的transact(),transact()最终调用onTransact()方法(同时本地线程在transact()调用处挂起)，
         *  onTransact()根据code确定客户端ClientActivity要调用服务端的哪个方法(addPerson(Person person)和getPersonList())，服务端调用
         *  目标方法后回到transact()调用处，本地线程恢复运行。
         * @param code 用于确定客户端所请求的目标方法，是addPerson(Person person)还是getPersonList()
         * @param data 存储着客户端调用的目标方法所需的参数，如果有的话，
         * @param reply onTransact()通过code确定了目标方法(addPerson(Person person)和getPersonList()),并执行完成后，将目标方法的返回值写入reply,如果有，
         * @param flags
         * @return false:请求失败，true：请求成功，可用于权限判断，毕竟我们不希望随便一个进程都能调用我们的服务。
         * @throws android.os.RemoteException
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                //确定客户单想调用addPerson()
                case TRANSACTION_addPerson: {
                    data.enforceInterface(descriptor);
                    Person _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = Person.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addPerson(_arg0);//传了data里的参数
                    reply.writeNoException();
                    //因为addPerson()无返回值，所以无需向reply中写入返回值
                    return true;
                }
                //确定客户端想调用getPersonList()
                case TRANSACTION_getPersonList: {
                    data.enforceInterface(descriptor);
                    java.util.List<Person> _result = this.getPersonList();//无参数
                    reply.writeNoException();
                    //因为getPersonList()有返回值，所以向reply中写入返回值
                    reply.writeTypedList(_result);
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        private static class Proxy implements StudyAidl {
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            /**
             * 第二步：
             * 此方法运行在客户端，由ClientActivity调用
             * transact()会发起远程调用同时挂起本地线程，因此如果远程方法很耗时则不能在UI线程发起远程调用。
             * @param person
             * @throws android.os.RemoteException
             */
            @Override
            public void addPerson(Person person) throws android.os.RemoteException {
                //_data用于接收addPerson()的参数person
                android.os.Parcel _data = android.os.Parcel.obtain();
                //_reply用于接收远程服务端的目标方法addPerson()执行后的返回值，如果有返回值的话。
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((person != null)) {
                        _data.writeInt(1);
                        person.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    //发起远程调用，跨进程调用远程服务端的onTransact(),同时本地线程挂起
                    mRemote.transact(Stub.TRANSACTION_addPerson, _data, _reply, 0);
                    //远程服务端的onTransact()执行完后，本地线程恢复
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            /**
             * 等价于，第二步
             * @return
             * @throws android.os.RemoteException
             */
            @Override
            public java.util.List<Person> getPersonList() throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                java.util.List<Person> _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    mRemote.transact(Stub.TRANSACTION_getPersonList, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.createTypedArrayList(Person.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        //用于表示目标方法的id
        static final int TRANSACTION_addPerson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getPersonList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    /**
     * 接口的抽象方法
     * @param person
     * @throws android.os.RemoteException
     */
    public void addPerson(Person person) throws android.os.RemoteException;

    public java.util.List<Person> getPersonList() throws android.os.RemoteException;
}
