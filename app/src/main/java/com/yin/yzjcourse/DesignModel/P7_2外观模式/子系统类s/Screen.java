package com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s;

public class Screen {
	String description;

	public Screen(String description) {
		this.description = description;
	}

	public void up() {
		System.out.println(description + " going up");
	}

	public void down() {
		System.out.println(description + " going down");
	}


	public String toString() {
		return description;
	}
}
