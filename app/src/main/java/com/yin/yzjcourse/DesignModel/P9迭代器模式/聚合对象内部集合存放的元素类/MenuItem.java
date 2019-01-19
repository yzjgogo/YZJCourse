package com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象内部集合存放的元素类;

/**
 * 所有聚合类内部的对象集合存放的都是MenuItem类型的对象。
 * 所谓聚合，其实就是聚合类内部的集合聚合了好多MenuItem元素。
 */
public class MenuItem {
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
}
