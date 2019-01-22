package com.yin.yzjcourse.DesignModel.P11代理模式.Proxy3保护代理.代理和类和代理的类的基类;

/**
 * 目标类PersonBeanImpl和代理类Proxy共同的基类，
 * 注意这里的代理类Proxy由java实现，我们并没有实际操作，只是创建了它同时立马将它的职责交给处理器InvocationHandler
 * 参考：getOwnerProxy()方法。
 */
public interface PersonBean {
 
	String getName();
	String getGender();
	String getInterests();
	int getHotOrNotRating();
 
    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setHotOrNotRating(int rating); 
 
}
