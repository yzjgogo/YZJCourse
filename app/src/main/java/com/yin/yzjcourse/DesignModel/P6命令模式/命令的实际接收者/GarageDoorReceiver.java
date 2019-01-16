package com.yin.yzjcourse.DesignModel.P6命令模式.命令的实际接收者;

public class GarageDoorReceiver {

	public GarageDoorReceiver() {
	}

	public void up() {
		System.out.println("Garage Door is Open");
	}

	public void down() {
		System.out.println("Garage Door is Closed");
	}

	public void stop() {
		System.out.println("Garage Door is Stopped");
	}

	public void lightOn() {
		System.out.println("Garage light is on");
	}

	public void lightOff() {
		System.out.println("Garage light is off");
	}
}
