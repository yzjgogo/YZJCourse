package com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.被代理的类_这里也是被代理保护的类;

import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler.NonOwnerInvocationHandler;
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler.OwnerInvocationHandler;
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.代理和类和代理的类的基类.PersonBean;

/**
 * 这是一个目标类，但是我们不直接方法，而是让代理类Proxy通过处理器InvocationHandler处理。
 * 根据业务逻辑，本人不可以修改调用setHotOrNotRating(int rating)方法修改rating信息，
 * 别人不可以调用setXXX系列方法修改他人的基本信息；
 * 因此这里设计了两个处理器：
 * {@link OwnerInvocationHandler}：用于处理自己本人的信息；
 * {@link NonOwnerInvocationHandler}：用于处理他人的信息；
 */
public class PersonBeanImpl implements PersonBean {
	String name;
	String gender;
	String interests;
	int rating;
	int ratingCount = 0;
  
	public String getName() {
		return name;	
	} 
  
	public String getGender() {
		return gender;
	}
  
	public String getInterests() {
		return interests;
	}
   
	public int getHotOrNotRating() {
		if (ratingCount == 0) return 0;
		return (rating/ratingCount);
	}
  
 
	public void setName(String name) {
		this.name = name;
	}
 
	public void setGender(String gender) {
		this.gender = gender;
	} 
  
	public void setInterests(String interests) {
		this.interests = interests;
	} 
  
	public void setHotOrNotRating(int rating) {
		this.rating += rating;	
		ratingCount++;
	}
}
