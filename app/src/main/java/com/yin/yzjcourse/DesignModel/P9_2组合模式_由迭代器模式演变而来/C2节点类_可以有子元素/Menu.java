package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C2节点类_可以有子元素;

import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C3节点迭代器.CompositeIterator;
import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类.MenuComponent;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * 所谓节点类，在整个树形结构中，节点不是最末梢的叶节点，它还可以分叉有很多其它的节点和叶节点。
 * 因此它背内部有一个集合menuComponents存放属于它的所有节点和叶节点，注意，无论是节点还是叶节点都是泛型MenuComponent类型，所以menuComponents里可能有
 * 节点也可能有叶节点。
 */
public class Menu extends MenuComponent {
	Iterator<MenuComponent> iterator = null;
	ArrayList<MenuComponent> menuComponents = new ArrayList<MenuComponent>();
	String name;
	String description;

    /**
     * 不同的节点有不同的名字和描述
     * @param name
     * @param description
     */
	public Menu(String name, String description) {
		this.name = name;
		this.description = description;
	}

    /**
     * 对于节点，抽象类MenuComponent中的add()方法是有意义的，用于向其中添加子节点或子叶节点。
     * @param menuComponent
     */
	public void add(MenuComponent menuComponent) {
		menuComponents.add(menuComponent);
	}


    /**
     * 对于节点，抽象类MenuComponent中的remove()方法是有意义的，用于删除其中的子节点或子叶节点。
     * @param menuComponent
     */
	public void remove(MenuComponent menuComponent) {
		menuComponents.remove(menuComponent);
	}



    /**
     * 对于节点，抽象类MenuComponent中的getChild()方法是有意义的，用于获取该节点中的子节点或子叶节点。
     */
	public MenuComponent getChild(int i) {
		return menuComponents.get(i);
	}



    /**
     * 对于节点，抽象类MenuComponent中的getChild()方法是有意义的，用于获取该节点中的名字
     */
	public String getName() {
		return name;
	}



    /**
     * 对于节点，抽象类MenuComponent中的getChild()方法是有意义的，用于获取该节点中的描述
     */
	public String getDescription() {
		return description;
	}

    /**
     * 暴露迭代器的接口，用于遍历当前节点，注意当前节点可能有很多子节点和子叶节点，因此需要用到递归。
     * @return
     */
	public Iterator<MenuComponent> createIterator() {
		if (iterator == null) {
			iterator = new CompositeIterator(menuComponents.iterator());
		}
		return iterator;
	}
 
 
	public void print() {
		System.out.print("\n" + getName());
		System.out.println(", " + getDescription());
		System.out.println("---------------------");
  
		Iterator<MenuComponent> iterator = menuComponents.iterator();
		while (iterator.hasNext()) {
			MenuComponent menuComponent = iterator.next();
			//如果menuComponent是叶节点则会调用叶节点(MenuItem)的print()，如果menuComponent是节点，则又会节点调用(当前Menu)的print()也就是当前所在的方法
            //重复获取迭代器，hasNext(),next(),menuComponent.print()的过程
			menuComponent.print();
		}
	}
}
