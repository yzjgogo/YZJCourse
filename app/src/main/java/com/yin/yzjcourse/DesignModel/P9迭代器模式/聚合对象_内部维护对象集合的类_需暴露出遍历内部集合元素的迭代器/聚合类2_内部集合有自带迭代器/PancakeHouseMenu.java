package com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类2_内部集合有自带迭代器;

import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类1_内部集合无自带迭代器需定义一个迭代器.迭代器.DinerMenuIterator;
import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象内部集合存放的元素类.MenuItem;
import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象的公共接口_使所有聚合类属于同一类_使客户代码解耦.Menu;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 煎饼店菜单，内部有各个子项(各个菜)
 *
 * 这也是一个聚合类，内部维护一个对象集合menuItems，(这里的对象集合泛指一堆对象，不一定是某个集合类包裹对象)
 */
public class PancakeHouseMenu implements Menu {
    /**
     * 这就是这个聚合类内部维护的对象集合，可见这个对象集合是用ArrayList维护的，而DinerMenu内部的对象集合使用数组维护的
     * 这也看出迭代器的作用了，迭代器不管你用什么维护的对象集合，只需hasNext(),next()遍历即可。
     */
	ArrayList<MenuItem> menuItems;
 
	public PancakeHouseMenu() {
		menuItems = new ArrayList<MenuItem>();
    
		addItem("K&B's Pancake Breakfast", 
			"Pancakes with scrambled eggs, and toast", 
			true,
			2.99);
 
		addItem("Regular Pancake Breakfast", 
			"Pancakes with fried eggs, sausage", 
			false,
			2.99);
 
		addItem("Blueberry Pancakes",
			"Pancakes made with fresh blueberries, and blueberry syrup",
			true,
			3.49);
 
		addItem("Waffles",
			"Waffles, with your choice of blueberries or strawberries",
			true,
			3.59);
	}

	public void addItem(String name, String description,
	                    boolean vegetarian, double price)
	{
		MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
		menuItems.add(menuItem);
	}

    /**
     * 这个方法不再需要了，因为这个方法只返回该聚合类内部的集合类型，而不同的聚合类内部可能用了不同的集合类型。
     * 例如DinerMenu内部用数组维护对象,则返回数组，而PancakeHouseMenu内部用ArrayList维护对象则返回ArrayList，这样客户代码就要依赖不同的集合类型。
     * 这也看出迭代器的作用了，迭代器不管你用什么维护的对象集合，客户代码只需hasNext(),next()遍历即可。
     *
     * @return
     */
//	public ArrayList<MenuItem> getMenuItems() {
//		return menuItems;
//	}


    /**
     * 暴露出迭代器的方法，继承自所有聚合类的公共接口Menu,这个迭代器可以遍历menuItems内部的元素。
     * 因为这个聚合类内部维护对象的集合是一个ArrayList，而ArrayList内部自带迭代器，所以只需取出该迭代器返回即可，而无需自定义迭代器。
     * 这与DinerMenu不同，因为DinerMenu内部用的数组，数组内部没有迭代器，所以需自定义迭代器，但是无论自定义迭代器，还是集合内部有
     * 默认的迭代器，这两个迭代器都是同一个类型的迭代器，这里都是java.util.Iterator
     * @return
     */
	public Iterator<MenuItem> createIterator() {
		return menuItems.iterator();
	}
  
	// other menu methods here
}
