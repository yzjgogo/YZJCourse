package com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象的公共接口_使所有聚合类属于同一类_使客户代码解耦;

import com.yin.yzjcourse.DesignModel.P9_1迭代器模式.聚合对象内部集合存放的元素类.MenuItem;

import java.util.Iterator;

/**
 * 所有聚合类的公共接口，用于将所有聚合类统一类型，这样客户端只需依赖该接口类型即可，无需依赖各个聚合类。
 * 因为要求所有集合类都提供遍历内部集合的迭代器，所以将暴露迭代器的方法定义在该公共接口里。
 * 该抽象方法返回各自聚合类的用于遍历内部对象集合的迭代器。
 */
public interface Menu {
	public Iterator<MenuItem> createIterator();
}
