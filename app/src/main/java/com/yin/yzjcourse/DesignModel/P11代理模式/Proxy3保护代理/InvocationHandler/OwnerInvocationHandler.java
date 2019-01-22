package com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler;
 
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.代理和类和代理的类的基类.PersonBean;

import java.lang.reflect.*;

/**
 * 操作自己的处理器
 * 代理类Proxy的处理器，所有处理器类都要实现{@link InvocationHandler}
 * 代理类Proxy的所有方法被调用时，都会交给InvocationHandler的invoke()处理
 */
public class OwnerInvocationHandler implements InvocationHandler { 
	PersonBean person;
 
	public OwnerInvocationHandler(PersonBean person) {
		this.person = person;
	}

    /**
     * 代理器所有的方法被调用时，都会由invoke()处理
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
			    //根据业务需要，本人不可以调用setHotOrNotRating()方法,所以抛出异常
				throw new IllegalAccessException();
			} else if (method.getName().startsWith("set")) {
				return method.invoke(person, args);
			} 
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } 
		return null;
	}
}
