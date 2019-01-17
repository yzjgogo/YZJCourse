package com.yin.yzjcourse.DesignModel.P6命令模式.远程控制客户端;

import com.yin.yzjcourse.DesignModel.P6命令模式.命令.具体的开关灯命令.LightOnCommand;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令.具体的开车库门命令.GarageDoorOpenCommand;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者.GarageDoorReceiver;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者.LightReceiver;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令的远程调用者.RemoteInvoker;

/**
 * 设计原则：
 * 封装变化：这里的变化是各种各样的命令，远程可能发起不同的命令，将这些命令封装成全部实现Commend接口，然后实现
 *          大量的具体命令，例如LightOnCommand、LightOffCommand、GarageDoorOpenCommand，这些命令 都通过
 *          execute()执行。
 *  多用组合，少用继承：这里命令的实际接收者都是以组合的方式进入到具体的命令中。另外，RemoteInvoker也将Commend
 *                    接口组合进来。
 *  针对接口编程，不针对实现编程：这里，所有具体的命令都实现Commend接口，切RemoteInvoker只关心抽象接口对象，不关心
 *                              具体是 什么对象。
 *  为交互对象之间的松耦合：设计而努力：RemoteInvoker和具体的命令对象例如LightOnCommand等是完全解耦的，不管你命令
 *                      怎么变化，你只需实现Commend接口即可。
 *  类应该对扩展开放，对修改关闭：这里，你可以任意增减命令，而无需修改Commend接口。切可通过RemoteInvoker的
 *                              setCommand()方法随时更改RemoteInvoker的功能。
 *  依赖抽象，不要依赖具体类(依赖倒置)：RemoteInvoker依赖抽象的接口Commend。
 *  别主动找我，我会叫你的(好莱坞原则)：命令接口的方法会主动调用命令的实际执行者的方法，这是单向调用的。
 *
 *
 *  命令模式：将请求(接收者)封装成命令对象，客户可通过命令对象松耦合执行请求。
 *
 *  参考图片:commend_p.png
 *
 */
public class RemoteControlClient {
    public static void main(String[] args) {
        //创建一个命令的远程调用者
        RemoteInvoker invoker = new RemoteInvoker();

        //创建一个灯,拥有on()开灯、off()关灯方法，命令的最终执行者
        LightReceiver light = new LightReceiver();

        //创建一个车库门，拥有up()开门等方法，命令的最终执行者
        GarageDoorReceiver garageDoor = new GarageDoorReceiver();

        //开灯命令，将‘灯light’的实例组合进开灯命令，这行命令的execute()时就执行light.on()
        LightOnCommand lightOn = new LightOnCommand(light);

        //开车库门的命令，同理开灯的命令
        GarageDoorOpenCommand garageOpen =
                new GarageDoorOpenCommand(garageDoor);

        //给远程调用者执行命令
        invoker.setCommand(lightOn);
        //远程调用者发起命令
        invoker.buttonWasPressed();

        //可以灵活的给远程调用 者更换命令
        invoker.setCommand(garageOpen);
        invoker.buttonWasPressed();
    }

}
