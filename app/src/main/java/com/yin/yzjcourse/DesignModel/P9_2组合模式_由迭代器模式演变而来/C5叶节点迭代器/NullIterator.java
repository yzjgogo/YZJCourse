package com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C5叶节点迭代器;
 
import com.yin.yzjcourse.DesignModel.P9_2组合模式_由迭代器模式演变而来.C1节点和叶节点的超类.MenuComponent;

import java.util.Iterator;

/**
 * 叶节点的迭代器，空迭代器，只是为了和节点迭代器一直而设计。
 */
public class NullIterator implements Iterator<MenuComponent> {
   
	public MenuComponent next() {
		return null;
	}
  
	public boolean hasNext() {
		return false;
	}
   
	/*
	 * No longer needed as of Java 8
	 * 
	 * (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 * 
	public void remove() {
		throw new UnsupportedOperationException();
	}
	*/
}
