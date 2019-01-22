package com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理;

import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler.NonOwnerInvocationHandler;
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.InvocationHandler.OwnerInvocationHandler;
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.代理和类和代理的类的基类.PersonBean;
import com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.被代理的类_这里也是被代理保护的类.PersonBeanImpl;

import java.lang.reflect.*;
import java.util.*;

/**
 * Java API中的代理，创建一个保护代理
 *
 * 因为实际的代理类是在运行时创建的所以叫动态代理。
 * 动态代理中java已经为你创建了Proxy类，所以不能像远程代理和虚拟代理那样修改Proxy类，因为Proxy类不是你实现的。
 * 因此就用到了处理器InvocationHandler,虽然你不能修改Proxy类，但是InvocationHandler可以响应代理的任何调用，注意是
 * 任何调用,当Proxy的方法被调用时，Proxy就会将这个调用转发给InvocationHandler的invoke(),不管Proxy被调用的是
 * 哪个方法，处理器InvocationHandler被调用的一定是invoke()。
 *
 * 总结：所谓保护代理，就是我们不直接操作代理类Proxy，而是通过代理类Proxy的处理器InvocationHandler来调用目标类。
 *
 * 下面看一下invoke(Object proxy, Method method, Object[] args)
 * 第一个参数proxy：就是代理类Proxy的实例；
 * 第二个参数method：就是Proxy被调用的方法；
 * 第三个参数args：就是Proxy被调用的方法的参数列表；
 *
 * 保护代理图片解释,参考：protect_proxy_img.png
 */
public class ProtectProxyTest {
	HashMap<String, PersonBean> datingDB = new HashMap<String, PersonBean>();
 	
	public static void main(String[] args) {
		ProtectProxyTest test = new ProtectProxyTest();
		test.drive();
	}

	//模拟初始化数据库
	public ProtectProxyTest() {
		initializeDatabase();
	}

	public void drive() {
	    //模拟从数据库中获取某个人的实例
		PersonBean joe = getPersonFromDatabase("Joe Javabean");
		//获取操作自己的Proxy代理类，也是Person类型的，实际这个代理ownerProxy将交由OwnerInvocationHandler处理
		PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		//调用Proxy代理的setInterests()方法，其实就是调用OwnerInvocationHandler的invoke()方法，最终还是操作当前这个人joe的方法。
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		try {
		    //根据业务自己不可以修改自己的rating,从OwnerInvocationHandler可看出，如果调用会抛出异常
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

		PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bowling, Go");
		} catch (Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
	}

    /**
     * 这里就是动态创建代理Proxy的地方，Proxy创建的同时将它的方法调用转发给InvocationHandler。
     *
     * newProxyInstance(参数1，参数2，参数 3)：
     * 参数1：代理类和目标类的公开接口PersonBean的类加载器；
     * 参数2：代理需要实现的接口，因为代理和目标类有共同的接口，所以需要实现接口PersonBean，也就有了PersonBean的一套方法。
     * 参数3：代理的处理器，代理里的方法被调用时实际的处理者。
     * @param person
     * @return 返回值就是Proxy实例，因为代理和目标对象有共同的接口，所以返回接口PersonBean
     */
	PersonBean getOwnerProxy(PersonBean person) {
        return (PersonBean) Proxy.newProxyInstance( 
            	person.getClass().getClassLoader(),
            	person.getClass().getInterfaces(),
                new OwnerInvocationHandler(person));
	}

	PersonBean getNonOwnerProxy(PersonBean person) {
		
        return (PersonBean) Proxy.newProxyInstance(
            	person.getClass().getClassLoader(),
            	person.getClass().getInterfaces(),
                new NonOwnerInvocationHandler(person));
	}

	PersonBean getPersonFromDatabase(String name) {
		return (PersonBean)datingDB.get(name);
	}

	void initializeDatabase() {
		PersonBean joe = new PersonBeanImpl();
		joe.setName("Joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);

		PersonBean kelly = new PersonBeanImpl();
		kelly.setName("Kelly Klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}
