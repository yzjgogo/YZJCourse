package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.具体的中介.MainBoard;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.CDDevice;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.CPU;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.GraphicsCard;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.SoundCard;

/**
 * 首先这个代码示例模拟用CD放电影的过程，主板MainBoard是中介者，
 * CDDevice(光驱)、CPU、GraphicsCard(显卡)、SoundCard(声卡)是一件事情的一类同事。
 * CDDevice加载"音视频"数据后通知中介者MainBoard，CPU将从CDDevice中读取的“音视频”数据解析成“音频数据”
 * 和“视频数据”后通知中介者MainBoard,然后SoundCard播放“音频数据”GraphicsCard播放“适配”数据。
 *
 * 中介者模式：
 * 中介者模式包装了一系列对象互相作用的方式，使得这些对象不必相互明显作用。从而使它们可以松散偶合。
 * 当某些对象之间的作用发生改变时，不会立即影响其他的一些对象之间的作用。保证这些作用可以彼此独立
 * 的变化。中介者模式将多对多的相互作用转化为一对多的相互作用。中介者模式将对象的行为和协作抽象化，
 * 把对象在小尺度的行为上与其他对象的相互作用分开处理。
 *
 * 关键点：
 * 1：将同事间的“多对多”作用，转化为中介对所有同事的“一对多”关系；
 * 2：将同事间的紧耦合，编程松耦合；
 *
 * 使用场景：
 * 当对象之间的交互操作很多且每个对象的行为操作都依赖彼此时，为防止在修改一个对象的行为时，同时涉及修改
 * 很多其他对象的行为，可采用中介者模式，来解决紧耦合问题。该模式将对象之间的多对多关系变成一对多关系，
 * 中介者对象将系统从网状结构变成以调停者为中心的星形结构，达到降低系统的复杂性，提高可扩展性的作用。
 *
 * 中介者模式UML：mediator_1.png
 */
public class MediatorTest {
    public static void main(String[] args) {
        //构造主板对象
        MainBoard mediator = new MainBoard();
        //分别构造各个零件
        CDDevice cd = new CDDevice(mediator);
        CPU cpu = new CPU(mediator);
        GraphicsCard gc = new GraphicsCard(mediator);
        SoundCard sc = new SoundCard(mediator);
        //将各个零件安装到主板
        mediator.setCDDevice(cd);
        mediator.setCPU(cpu);
        mediator.setGraphicsCard(gc);
        mediator.setSoundCard(sc);
        //播放电影，load()是入口，会通过中介者引发cpu、显卡、声卡联动
        cd.load();
    }

}
