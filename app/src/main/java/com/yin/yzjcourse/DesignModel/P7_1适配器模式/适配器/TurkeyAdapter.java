package com.yin.yzjcourse.DesignModel.P7_1适配器模式.适配器;

import com.yin.yzjcourse.DesignModel.P7_1适配器模式.被适配者.抽象被适配者.Turkey;
import com.yin.yzjcourse.DesignModel.P7_1适配器模式.目标接口.Duck;

/**
 * 将一个火鸡turkey适配成鸭子。
 */
public class TurkeyAdapter implements Duck {
	Turkey turkey;//这里依赖抽象，是火鸡的接口
 
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}

	//看似鸭子叫，其实是火鸡在叫
	public void quack() {
		turkey.gobble();
	}

	//看似是鸭子非，其实是火鸡在飞。
	public void fly() {
		for(int i=0; i < 5; i++) {
			turkey.fly();
		}
	}
}
