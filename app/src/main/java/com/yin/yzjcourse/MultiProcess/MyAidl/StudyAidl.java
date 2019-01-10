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
         */
        public static StudyAidl asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof StudyAidl))) {
                return ((StudyAidl) iin);
            }
            return new Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }

        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
            String descriptor = DESCRIPTOR;
            switch (code) {
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_addPerson: {
                    data.enforceInterface(descriptor);
                    Person _arg0;
                    if ((0 != data.readInt())) {
                        _arg0 = Person.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    this.addPerson(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPersonList: {
                    data.enforceInterface(descriptor);
                    java.util.List<Person> _result = this.getPersonList();
                    reply.writeNoException();
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

            @Override
            public void addPerson(Person person) throws android.os.RemoteException {
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((person != null)) {
                        _data.writeInt(1);
                        person.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    mRemote.transact(Stub.TRANSACTION_addPerson, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

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

        static final int TRANSACTION_addPerson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
        static final int TRANSACTION_getPersonList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    }

    public void addPerson(Person person) throws android.os.RemoteException;

    public java.util.List<Person> getPersonList() throws android.os.RemoteException;
}
