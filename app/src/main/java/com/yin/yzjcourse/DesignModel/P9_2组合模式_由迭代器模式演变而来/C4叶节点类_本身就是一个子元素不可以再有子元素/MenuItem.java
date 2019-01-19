package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C4叶节点类_本身就是一个子元素不可以再有子元素;

import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C5叶节点迭代器.NullIterator;
import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类.MenuComponent;

import java.util.Iterator;

/**
 * 所谓叶节点：位于树型结构的末梢，类似树叶，不再有分支(子元素)的节点，因此其内部没有集合类存放元素。
 */
public class MenuItem extends MenuComponent {
 
	String name;
	String description;
	boolean vegetarian;
	double price;
    
	public MenuItem(String name, 
	                String description, 
	                boolean vegetarian, 
	                double price) 
	{ 
		this.name = name;
		this.description = description;
		this.vegetarian = vegetarian;
		this.price = price;
	}
  
	public String getName() {
		return name;
	}
  
	public String getDescription() {
		return description;
	}
  
	public double getPrice() {
		return price;
	}
  
	public boolean isVegetarian() {
		return vegetarian;
	}

    /**
     * 正常来说，因为叶节点没有子元素，不需要遍历，但是为了保证和节点(Menu)的一致性，还是给叶节点提供了迭代器，只是这个迭代器的hasNext()用于返回false，next()
     * 永远返回null，我们称其为空迭代器。
     * 如果不给叶节点提供迭代器，则客户代码在遍历节点时，还需要判断是不是叶节点，这样耦合性太强，逻辑太乱，不易于维护。
     *
     * 注意NullIterator和CompositeIterator都是抽象的Iterator<MenuComponent>类型，因此对于客户代码来说，二者一样。
     *
     * @return
     */
	public Iterator<MenuComponent> createIterator() {
		return new NullIterator();
	}
 
	public void print() {
		System.out.print("  " + getName());
		if (isVegetarian()) {
			System.out.print("(v)");
		}
		System.out.println(", " + getPrice());
		System.out.println("     -- " + getDescription());
	}

}

