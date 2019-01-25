package com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P3存放备忘录的对象;

import com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P2备忘录_被记录的状态的载体.MyMemento;

/**
 * Caretaker，负责管理Memento
 * 状态 放到备忘录MyMemento里后，这个备忘录需要存放到MyCaretaker里。
 *
 */
public class MyCaretaker {
    MyMemento mMemento; //备忘录
    /**
     * 存档
     * 存储备忘录
     */
    public void archive(MyMemento memento){
        this.mMemento = memento;
    }
    /**
     * 获取存档
     * 读取备忘录
     */
    public MyMemento getMemento(){
        return mMemento;
    }
}
