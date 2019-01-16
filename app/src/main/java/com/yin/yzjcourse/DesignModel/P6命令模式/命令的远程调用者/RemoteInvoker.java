package com.yin.yzjcourse.DesignModel.P6命令模式.命令的远程调用者;

import com.yin.yzjcourse.DesignModel.P6命令模式.命令.抽象命令.Command;

//
// This is the invoker
//
public class RemoteInvoker {
	Command slot;
 
	public RemoteInvoker() {}

	//可通过该方法设置或更换命令，通过抽象对象接收，松耦合，无需关心具体的命令对象是谁
	public void setCommand(Command command) {
		slot = command;
	}

	//类似遥控器按钮，按下去远程发起命令，由命令的实际接收者最终执行，例如开灯、关灯，开门等命令。
	public void buttonWasPressed() {
		slot.execute();
	}
}
