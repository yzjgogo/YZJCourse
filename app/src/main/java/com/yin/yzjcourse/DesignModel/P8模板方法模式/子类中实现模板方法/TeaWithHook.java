package com.yin.yzjcourse.DesignModel.P8模板方法模式.子类中实现模板方法;

import com.yin.yzjcourse.DesignModel.P8模板方法模式.抽象类中定义模板方法.CaffeineBeverageWithHook;

import java.io.*;

public class TeaWithHook extends CaffeineBeverageWithHook {
 
	public void brew() {
		System.out.println("Steeping the tea");
	}
 
	public void addCondiments() {
		System.out.println("Adding Lemon");
	}
 
	public boolean customerWantsCondiments() {

		String answer = getUserInput();

		if (answer.toLowerCase().startsWith("y")) {
			return true;
		} else {
			return false;
		}
	}
 
	private String getUserInput() {
		// get the user's response
		String answer = null;

		System.out.print("Would you like lemon with your tea (y/n)? ");

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			answer = in.readLine();
		} catch (IOException ioe) {
			System.err.println("IO error trying to read your answer");
		}
		if (answer == null) {
			return "no";
		}
		return answer;
	}
}
