package com.yin.yzjcourse.DesignModel.P6命令模式.命令.具体的开关灯命令;

import com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者.LightReceiver;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令.抽象命令.Command;

/**
 * 一个具体的命令，内部维护了具体命令的接收者
 */
public class LightOffCommand implements Command {
	LightReceiver light;
 
	public LightOffCommand(LightReceiver light) {
		this.light = light;
	}

	//将命令交给接收者执行
	public void execute() {
		light.off();
	}
}
