package com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P2备忘录_被记录的状态的载体;

/**
 * 备忘录类
 * 被存储的状态的载体，被存储的状态就是以属性的形式放到MyMemento里。
 */
public class MyMemento {
    public int mCheckpoint;//武器
    public int mLiftValue;//生命
    public String mWeapon;//关卡
    @Override
    public String toString() {
        return "Memento [mCheckpoint=" + mCheckpoint + ",mLiftValue="
                + mLiftValue + ",mWeapon=" + mWeapon + "]";
    }
}
