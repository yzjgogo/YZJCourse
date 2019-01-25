package com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P1需要被记录状态的对象;

import com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P2备忘录_被记录的状态的载体.MyMemento;

/**
 *
 * 简单模拟“使命召唤”游戏
 * 退出游戏是需要存档游戏的状态，因此提供createMemento()暴露出存档的方法；
 * 重新开始游戏时需要读取之前的游戏状态 ，因此提供restore(MyMemento memento)恢复状态；
 *
 */
public class MyOriginator {
    private int mCheckpoint = 1;//关卡
    private int mLiftValue = 100;//生命值
    private String mWeapon = "沙漠之鹰";//武器
    //只是为了改变mLiftValue、mCheckpoint,不是重点，不用关心
    public void play(){
        System.out.println("打游戏："+String.format("第%d关", mCheckpoint) + "奋战杀敌中");
        mLiftValue -= 10;
        System.out.println("进度升级了");
        mCheckpoint++;
        System.out.println("到达" + String.format("第%d关", mCheckpoint));
    }
    //退出游戏，不是重点，不用关心
    public void quit(){
        System.out.println("--------------");
        System.out.println("退出前的游戏属性：" + this.toString());
        System.out.println("退出游戏");
        System.out.println("--------------");
    }
    /**
     * 创建备忘录
     * 将游戏的各个状态mCheckpoint、mLiftValue、mWeapon等存储到备忘录memento里。
     * 可见memento是一个专门存放状态的对象；
     * 这里只是创建备忘录，之后MyCaretaker.archive(MyMemento memento)才真正将这个备忘录memento
     * 存储起来，存储到MyCaretaker。
     */
    public MyMemento createMemento(){
        MyMemento memento = new MyMemento();
        memento.mCheckpoint = mCheckpoint;
        memento.mLiftValue = mLiftValue;
        memento.mWeapon = mWeapon;
        return memento;
    }

    /**
     * 这是恢复之前存储的状态的方法，状态在memento里，而mementoMyCaretaker里。
     * @param memento 从MyCaretaker.getMemento()里取出的存储起来的备忘录。
     */
    public void restore(MyMemento memento){
        this.mCheckpoint = memento.mCheckpoint;
        this.mLiftValue = memento.mLiftValue;
        this.mWeapon = memento.mWeapon;
        System.out.println("恢复后的游戏属性：" + this.toString());
    }

    //省略getter和setter方法

    //不重要，不用关心
    @Override
    public String toString() {
        return "CallOfDuty [mCheckpoint=" + mCheckpoint + ",mLiftValue="
                + mLiftValue + ",mWeapon=" + mWeapon + "]";
    }
}
