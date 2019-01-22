package com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler;
 
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.代理和类和代理的类的基类.PersonBean;

import java.lang.reflect.*;

/**
 * 操作他人的处理器
 */
public class NonOwnerInvocationHandler implements InvocationHandler { 
	PersonBean person;
 
	public NonOwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

    /**
     * 代理类Proxy的方法被调用时，就由invoke()处理
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws IllegalAccessException
     */
	public Object invoke(Object proxy, Method method, Object[] args) 
			throws IllegalAccessException {
  
		try {
			if (method.getName().startsWith("get")) {
				return method.invoke(person, args);
   			} else if (method.getName().equals("setHotOrNotRating")) {
				return method.invoke(person, args);
			} else if (method.getName().startsWith("set")) {
			    //根据业务需要，某个人不可以调用他人的set方法，所以抛出异常
				throw new IllegalAccessException();
			} 
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
		return null;
	}
}
