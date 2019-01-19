package com.yin.yzjcourse.DesignModel.P9迭代器模式.访问聚合对象的客户代码;

import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象的公共接口_使所有聚合类属于同一类_使客户代码解耦.Menu;
import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象内部集合存放的元素类.MenuItem;

import java.util.Iterator;

/**
 * 客户端代码，访问聚合类的代码，说是访问聚合类，其实都变成的访问素有聚合类的公共接口Menu,这样就遵守了依赖抽象、面向接口编程的设计原则。
 * 而真正遍历这些聚合类内部的集合时，也没有直接依赖内部集合类型，而是通过这些聚合类暴露的迭代器来遍历，这样也使客户端代码与集合类逻辑解耦
 * 客户端根本不知道在操作哪个聚合类，也不知道聚合类内部是怎么实现的，更不知道聚合类内部用了什么集合类型来聚合元素。
 */
public class Waitress {
    /**
     * 客户端代码只关心抽象的接口类型
     */
	Menu pancakeHouseMenu;
	Menu dinerMenu;
 
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}

    /**
     * 虽然各个聚合类的实现不同，但是他们都有相同的遍历内部集合的迭代器。
     */
	public void printMenu() {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();

		System.out.println("MENU\n----\nBREAKFAST");
		printMenu(pancakeIterator);
		System.out.println("\nLUNCH");
		printMenu(dinerIterator);
	}
 
	private void printMenu(Iterator<MenuItem> iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			System.out.print(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}

    /**
     * 依赖迭代器操作即可
     */
	public void printVegetarianMenu() {
		System.out.println("\nVEGETARIAN MENU\n----\nBREAKFAST");
		printVegetarianMenu(pancakeHouseMenu.createIterator());
		System.out.println("\nLUNCH");
		printVegetarianMenu(dinerMenu.createIterator());
	}


    /**
     * 依赖迭代器操作即可
     */
	public boolean isItemVegetarian(String name) {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		if (isVegetarian(name, pancakeIterator)) {
			return true;
		}
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
		if (isVegetarian(name, dinerIterator)) {
			return true;
		}
		return false;
	}



    /**
     * 依赖迭代器操作即可
     */
	private void printVegetarianMenu(Iterator<MenuItem> iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			if (menuItem.isVegetarian()) {
				System.out.print(menuItem.getName());
				System.out.println("\t\t" + menuItem.getPrice());
				System.out.println("\t" + menuItem.getDescription());
			}
		}
	}


    /**
     * 依赖迭代器操作即可
     */
	private boolean isVegetarian(String name, Iterator<MenuItem> iterator) {
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			if (menuItem.getName().equals(name)) {
				if (menuItem.isVegetarian()) {
					return true;
				}
			}
		}
		return false;
	}
}
