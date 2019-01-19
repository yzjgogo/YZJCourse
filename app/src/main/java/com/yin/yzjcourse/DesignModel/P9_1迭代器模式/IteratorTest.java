package com.yin.yzjcourse.DesignModel.P9_1迭代器模式;

import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类1_内部集合无自带迭代器需定义一个迭代器.DinerMenu;
import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类2_内部集合有自带迭代器.PancakeHouseMenu;
import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.访问聚合对象的客户代码.Waitress;

/**
 *  迭代器模式：
 *  提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露其内部的表示。
 *
 *  用处：如果某个类内部有集合，就可采用迭代器模式访问集合元素。
 *
 *  好处：
 *  你可以编写多态的代码，例如可以有各种不同的聚合类，只需这些聚合类有统一的接口(Menu)，且都暴露出迭代器即可。
 *  客户代码可以与聚合类完全结构，客户代码根本不知道聚合类时怎么实现的。
 *
 *
 *  注意点：
 *  1：这些聚合类有共同的父接口Menu；
 *  2：每个聚合类都暴露出迭代器用于遍历聚合类内部的元素；
 *
 *  设计原则：
 *  封装变化：每个聚合类内部可能用不同的集合存放元素，通过迭代器封装这些集合的遍历，使其统一；
 *  多用组合，少用集成：每个聚合类都是以组合的方式引入迭代器；
 *  针对接口编程，不针对实现编程：所有的聚合类都有公共的接口(Menu)，客户代码只关心Menu，不关心具体的聚合类。
 *  为交互对象之间的松耦合设计而努力：客户代码与聚合类时完全解耦的，客户代码根本不知道聚合类内部是是怎么实现的。
 *  类应该对扩展开放，对修改关闭(开闭原则)：
 *  依赖抽象，不依赖具体类(依赖倒置原则)：客户代码完全依赖抽象的接口(Menu)和迭代器。而不依赖具体的聚合类。
 *  只和朋友交谈(最少知识原则)：
 *  别找我，我会找你(好莱坞原则)：客户代码单项调用抽象接口的方法。
 *  类应该只有一个改变的理由(单一职责原则)：每一个类应该专注于做一件事情。解耦和增强内聚性（高内聚，低耦合）,类被修改的几率很大，
 *                  因此应该专注于单一的功能。如果你把多个功能放在同一个类中，功能之间就形成了关联，改变其中一个功能，有可能中止另一个功能，
 *                  这时就需要新一轮的测试来避免可能出现的问题。
 *
 * 参考图片：iterator_p1.png、iterator_p2.png
 */
public class IteratorTest {
	public static void main(String args[]) {
        /**
         * 创建两个菜单
         */
		PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
		DinerMenu dinerMenu = new DinerMenu();

		//服务员接收连个菜单，但是客户端代码服务员(Waitress)和各个聚合类(菜单PancakeHouseMenu，DinerMenu)是完全解耦的
		Waitress waitress = new Waitress(pancakeHouseMenu, dinerMenu);
		waitress.printMenu();
		waitress.printVegetarianMenu();

		System.out.println("\nCustomer asks, is the Hotdog vegetarian?");
		System.out.print("Waitress says: ");
		if (waitress.isItemVegetarian("Hotdog")) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		System.out.println("\nCustomer asks, are the Waffles vegetarian?");
		System.out.print("Waitress says: ");
		if (waitress.isItemVegetarian("Waffles")) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}
}
