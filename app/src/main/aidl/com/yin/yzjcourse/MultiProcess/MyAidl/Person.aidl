// Person.aidl
//这个Person.aidl是java的com.yin.yzjcourse.MultiProcess.MyAidl.Person类的映射
//因为Person是跨进程真正被交换的数据，aidl不能直接使用java的类，所以做一个映射
//注意，包路径必须和被映射的java的类的包路径一致
//rebuild后这个Person.aidl不会再生成依次java文件，因为它本身就是从已存在的java文件中映射过来的。
package com.yin.yzjcourse.MultiProcess.MyAidl;


parcelable Person;
