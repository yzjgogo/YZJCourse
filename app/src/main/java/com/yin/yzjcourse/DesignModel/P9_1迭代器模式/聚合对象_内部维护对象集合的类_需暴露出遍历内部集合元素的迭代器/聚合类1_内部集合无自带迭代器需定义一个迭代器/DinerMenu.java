package com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类1_内部集合无自带迭代器需定义一个迭代器;

import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类1_内部集合无自带迭代器需定义一个迭代器.迭代器.DinerMenuIterator;
import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象内部集合存放的元素类.MenuItem;
import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象的公共接口_使所有聚合类属于同一类_使客户代码解耦.Menu;

import java.util.Iterator;

/**
 * 晚餐菜单，内部有各个子项(各个菜MenuItem)
 * 这是一个聚合类，内部维护这对象集合menuItems,这里的集合是泛指一堆对象。
 *
 */
public class DinerMenu implements Menu {
	static final int MAX_ITEMS = 6;
	int numberOfItems = 0;
    /**
     * 数组menuItems
     * 这就是该聚合类DinerMenu内部维护的对象集合(泛指一堆对象)
     * 这个对象集合是用数组维护的，而PancakeHouseMenu内部的对象集合是用ArrayList维护的。
     * 这也看出迭代器的作用了，迭代器不管你用什么维护的对象集合，只需hasNext(),next()遍历即可。
     */
	MenuItem[] menuItems;
  
	public DinerMenu() {
		menuItems = new MenuItem[MAX_ITEMS];
 
		addItem("Vegetarian BLT",
			"(Fakin') Bacon with lettuce & tomato on whole wheat", true, 2.99);
		addItem("BLT",
			"Bacon with lettuce & tomato on whole wheat", false, 2.99);
		addItem("Soup of the day",
			"Soup of the day, with a side of potato salad", false, 3.29);
		addItem("Hotdog",
			"A hot dog, with saurkraut, relish, onions, topped with cheese",
			false, 3.05);
		addItem("Steamed Veggies and Brown Rice",
			"Steamed vegetables over brown rice", true, 3.99);
		addItem("Pasta",
			"Spaghetti with Marinara Sauce, and a slice of sourdough bread",
			true, 3.89);
	}
  
	public void addItem(String name, String description, 
	                     boolean vegetarian, double price) 
	{
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		if (numberOfItems >= MAX_ITEMS) {
			System.err.println("Sorry, menu is full!  Can't add item to menu");
		} else {
			menuItems[numberOfItems] = menuItem;
			numberOfItems = numberOfItems + 1;
		}
	}



    /**
     * 这个方法不再需要了，因为这个方法只返回该聚合类内部的集合类型，而不同的聚合类内部可能用了不同的集合类型。
     * 例如DinerMenu内部用数组维护对象,则返回数组，而PancakeHouseMenu内部用ArrayList维护对象则返回ArrayList，这样客户代码就要依赖不同的集合类型。
     * 这也看出迭代器的作用了，迭代器不管你用什么维护的对象集合，客户代码只需hasNext(),next()遍历即可。
     *
     * @return
     */
//	public MenuItem[] getMenuItems() {
//		return menuItems;
//	}

    /**
     * 暴露出迭代器的方法，继承自所有聚合类的公共接口Menu,这个迭代器可以遍历menuItems内部的元素。
     * 因为这个聚合类内部维护对象的集合是一个数组menuItems，而数组没有用到迭代器，所以我们自定义一个迭代器，专门用于迭代数组
     * 这与{@link DinerMenuIterator}不同,因为这个DinerMenuIterator内部维护对象的集合是一个ArrayList，而ArrayList内部自带迭代器，
     * 所以只需取出该迭代器返回即可，而无需自定义迭代器。
     * 但是无论自定义迭代器，还是集合内部有默认的迭代器，这两个迭代器都是同一个类型的迭代器，这里都是java.util.Iterator
     * @return
     */
	public Iterator<MenuItem> createIterator() {
		return new DinerMenuIterator(menuItems);
		//return new AlternatingDinerMenuIterator(menuItems);
	}
 
	// other menu methods here
}
