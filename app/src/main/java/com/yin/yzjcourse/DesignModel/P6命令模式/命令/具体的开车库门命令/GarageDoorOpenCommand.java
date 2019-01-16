package com.yin.yzjcourse.DesignModel.P6命令模式.命令.具体的开车库门命令;

import com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者.GarageDoorReceiver;
import com.yin.yzjcourse.DesignModel.P6命令模式.命令.抽象命令.Command;

public class GarageDoorOpenCommand implements Command {
	GarageDoorReceiver garageDoor;

	public GarageDoorOpenCommand(GarageDoorReceiver garageDoor) {
		this.garageDoor = garageDoor;
	}

	public void execute() {
		garageDoor.up();
	}
}
