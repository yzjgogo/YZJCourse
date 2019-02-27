package com.yin.yzjcourse.MultiProcess.SerializableAndParcelable;

import java.io.Serializable;

/**
 * Android Studio怎么自动生成serialVersionUID
 * 1、File -> Settings... -> Editor -> Inspections -> Serialization issues[在java类目下] -> Serializable class without 'serialVersionUID'（选中）
 * 2、进入实现了Serializable中的类，选中类名，Alt+Enter弹出提示，然后直接导入完成
 *
 * serialVersionUID的作用：
 * 序列化时，会把该serialVersionUID一并序列化，当反序列化会拿序列化文件里的serialVersionUID和现在类的serialVersionUID对比，
 * 如果发现不一样则序列化失败，系统报错；如果一样则可以正常反序列化。
 *
 * serialVersionUID也可以不指定，如果不指定则默认应该是hash值，如果反序列化时，类结构发生了变化(属性的增减，属性类型的变化等等)
 * 则反序列化时会重新结算hash值，如果发现新计算出的hash值和序列化文件里的不一样也会序列化是吧。
 *
 * 如果指定的化，则序列化前后都是一个定值，即使类解构发生了变化，则反序列化也会尽量进行，在很大程度上恢复数据，例如，你增减了属性，则
 * 原来还存在的属性可以得以恢复，如果你把类名都改了，那肯定恢复不了了。
 *
 * 不参与序列化的东西：
 * 1：静态成员变量属于类不属于具体的对象，所以不会参与；
 * 2：用transient修饰的成员也不参与序列化 。
 */
public class SerUser implements Serializable {
    //    private static final long serialVersionUID = 4237838323820257138L;
//    private static final long serialVersionUID = 1L;
    private int userId;
    private String userName;
    private boolean isMale;

    public SerUser(int userId, String userName, boolean isMale) {
        this.userId = userId;
        this.userName = userName;
        this.isMale = isMale;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    @Override
    public String toString() {
        return "SerUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", isMale=" + isMale +
                '}';
    }
}
