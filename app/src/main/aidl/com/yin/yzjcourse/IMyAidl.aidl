// IMyAidl.aidl
package com.yin.yzjcourse;

//我最早是在这个博客看的：https://blog.csdn.net/u011240877/article/details/72765136

//这个import 指向用到的Person.aidl的位置
import com.yin.yzjcourse.MultiProcess.MyAidl.Person;



//写好aidl后，可以rebuild或Build->Make Project一下，就会在
//app->build->generated->source->aidl->debug->报名-> 下生成对应的java文件

//aidl是通过Binder实现跨进程通信中客户端和服务端都认可的一个接口，编译阶段，会把aidl编译成继承了Binder的java类
//这个java类才是客户端和服务端共同使用的一样代码的两份副本。

//aidl适用于同一个应用通过android:process属性实现的跨进程，也适用于两个完全不同的应用，
//如果是一个应用的不同进程，则其实adil是定义在服务端，客户端复制一整套aidl接口的代码
//如果是两个应用，则要先在服务端定义aidl，然后客户端需要定义一份与服务端完全相同的一套aidl，包括包路径也要相同，都是服务端的包路径，这便于服务端识别





//aidl支持的数据类型：

//1：Java的基本数据类型

//2：List和Map ,元素必须是AIDL支持的数据类型；Server端具体的类实例则必须是ArrayList或者HashMap
    //这个案例的List就是ArryList:com.yin.yzjcourse.MultiProcess.MyAidl.MyService.mPersons

//3：其他 AIDL 生成的接口
//4：实现 Parcelable 的实体

interface IMyAidl {
    void addPerson(in Person person);

        List<Person> getPersonList();
}
