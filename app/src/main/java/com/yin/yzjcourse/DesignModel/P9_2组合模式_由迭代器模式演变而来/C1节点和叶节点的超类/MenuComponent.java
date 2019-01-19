package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类;

import java.util.*;

/**
 * 整个树形结构中，所有的节点和叶节点都需要集成该抽象类；
 * 这个抽象类中有很多方法，且都有默认的实现(抛出异常)，有些适合节点去重写，不适合节点去实现的方法保持默认异常即可，有些适合叶节点去实现，不适合叶节点
 * 去实现的方法同样保持默认即可.
 * 也就是说节点和叶节点所有可能用到的方法都定义到这里面了，这样方便节点和叶节点属于同一抽象类型，也避免了客户代码去进行节点和叶节点的判断。
 */
public abstract class MenuComponent {
   
	public void add(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public void remove(MenuComponent menuComponent) {
		throw new UnsupportedOperationException();
	}
	public MenuComponent getChild(int i) {
		throw new UnsupportedOperationException();
	}
  
	public String getName() {
		throw new UnsupportedOperationException();
	}
	public String getDescription() {
		throw new UnsupportedOperationException();
	}
	public double getPrice() {
		throw new UnsupportedOperationException();
	}
	public boolean isVegetarian() {
		throw new UnsupportedOperationException();
	}

    /**
     * 与迭代器模式一样，也定义了暴露迭代器的方法。
     * @return
     */
	public abstract Iterator<MenuComponent> createIterator();
 
	public void print() {
		throw new UnsupportedOperationException();
	}
}
