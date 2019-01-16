package com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者;

public class LightReceiver {

	public LightReceiver() {
	}

	public void on() {
		System.out.println("LightReceiver is on");
	}

	public void off() {
		System.out.println("LightReceiver is off");
	}
}
