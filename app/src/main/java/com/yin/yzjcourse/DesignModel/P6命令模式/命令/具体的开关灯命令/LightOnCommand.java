package com.yin.yzjcourse.DesignModel.P6命令模式.命令.具体的开关灯命令;

import com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者.LightReceiver;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令.抽象命令.Command;

public class LightOnCommand implements Command {
	LightReceiver light;
  
	public LightOnCommand(LightReceiver light) {
		this.light = light;
	}
 
	public void execute() {
		light.on();
	}
}
