package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.客户代码;

import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类.MenuComponent;

import java.util.Iterator;

/**
 * 客户代码，可见只依赖了抽象类MenuComponent，和Iterator，与根本不关心是节点还是叶节点，完全解耦。
 */
public class Waitress {
	MenuComponent allMenus;
 
	public Waitress(MenuComponent allMenus) {
		this.allMenus = allMenus;
	}
 
	public void printMenu() {
		allMenus.print();
	}
  
	public void printVegetarianMenu() {
		Iterator<MenuComponent> iterator = allMenus.createIterator();

		System.out.println("\nVEGETARIAN MENU\n----");
		while (iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			//这里用了try，因为对于节点(Menu),isVegetarian()是没有意义的，在抽象类MenuComponent定义的默认实现是抛出异常，所有如果是节点(Menu)就会抛出异常。
			try {
				if (menuComponent.isVegetarian()) {
					menuComponent.print();
				}
			} catch (UnsupportedOperationException e) {}
		}
	}
}
