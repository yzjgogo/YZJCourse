package com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento;

import com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P1需要被记录状态的对象.MyOriginator;
import com.yin.yzjcourse.DesignModel.P17备忘录模式_Memento.P3存放备忘录的对象.MyCaretaker;

/**
 *  备忘录模式：
 *  在不破坏封闭的前提下，捕获一个对象(Originator)的内部状态(放Memento里)，并在该对象之外(Caretaker)保护这个状态，
 *  这样，以后就可将该对象恢复到原先保存的状态。
 *
 *  关键点：
 *  1：被保存的状态要在Originator之外保存，因为防止该状态被外部对象访问。
 *
 *  组成：
 *  Originator：负责创建一个备忘录可以记录、恢复自身的内部状态。同时Originator还可以根据需要决定Memento存储自身的那些内部状态；
 *  Memento：备忘录角色，就想像把状态都写到笔记本里面一样。用于存储Originator的内部状态，并且可以防止Originator以外的对象访问Memento。
 *  Caretaker：负责存储备忘录，不能对备忘录的内容进行操作和访问，只能够将备忘录传递给其它对象。
 *
 *  备忘录模式UML:memento_1.png
 *
 */
public class TestMemento {
    public static void main(String[] args) {
        //构建游戏对象
        MyOriginator game = new MyOriginator();
        //1.打游戏,随便操作一下，改变一下状态，不用关心
        game.play();
        MyCaretaker caretaker = new MyCaretaker();//备忘录存储的地方
        //2.游戏存档，将游戏的状态放到备忘录里，再把备忘录放到caretaker里
        caretaker.archive(game.createMemento());
        //3.退出游戏，在这 之前已经 存档了
        game.quit();
        //4.恢复游戏，从caretaker取出备忘录，用于恢复状态
        MyOriginator newGame = new MyOriginator();
        newGame.restore(caretaker.getMemento());
    }

}
