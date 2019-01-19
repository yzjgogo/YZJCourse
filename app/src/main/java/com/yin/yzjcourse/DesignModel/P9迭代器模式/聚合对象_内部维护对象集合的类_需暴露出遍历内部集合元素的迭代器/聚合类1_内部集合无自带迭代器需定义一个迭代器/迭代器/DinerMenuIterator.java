package com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象_内部维护对象集合的类_需暴露出遍历内部集合元素的迭代器.聚合类1_内部集合无自带迭代器需定义一个迭代器.迭代器;

import com.yin.yzjcourse.DesignModel.P9迭代器模式.聚合对象内部集合存放的元素类.MenuItem;

import java.util.Iterator;

/**
 * 定义的一个用于迭代数组的迭代器。实现Java自带的Iterator，也可以自己写一个类似java自带的迭代器，但是要求所有聚合类都使用同一个迭代器。
 * 所有的聚合类都暴露同一类型的迭代器，这样客户代码只需通过迭代器的hasNext(),next()遍历即可，而不用关心各个聚合类内部到底是用什么类型
 * 的集合维护的对象，例如DinerMenu内部用数组维护对象,而PancakeHouseMenu内部用ArrayList维护对象。
 */
public class DinerMenuIterator implements Iterator<MenuItem> {
    MenuItem[] list;
    int position = 0;

    public DinerMenuIterator(MenuItem[] list) {
        this.list = list;
    }

    /**
     * 重写next()
     * 如果hasNext()能取到元素，则调用next()取出元素
     * @return
     */
    @Override
    public MenuItem next() {
        MenuItem menuItem = list[position];
        position = position + 1;//遍历到一个元素后，将索引移动到下一个，方便下次hasNext()判断。
        return menuItem;
    }

    /**
     * 重写hasNext()
     * 判断能否去到元素
     * @return
     */
    @Override
    public boolean hasNext() {
        if (position >= list.length || list[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 该方法可根据需要决定是否重写，
     * 这里移除数组的一个元素后，将后面的所有元素往前移一位。
     */
    @Override
    public void remove() {
        if (position <= 0) {
            throw new IllegalStateException
                    ("You can't remove an item until you've done at least one next()");
        }
        if (list[position - 1] != null) {
            for (int i = position - 1; i < (list.length - 1); i++) {
                list[i] = list[i + 1];
            }
            list[list.length - 1] = null;
        }
    }

}
