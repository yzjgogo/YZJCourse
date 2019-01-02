package com.yin.yzjcourse.k16_Lambda.k8集合库函数和序列

import org.junit.Test

/**
 * 参考：kotlin in action page116
 */
class MyCollection {
    /**
     * filter：过滤，获取满足条件的元素组成的集合
     * 接收一个Lambda表达式参数(函数类型的参数)
     * 从一个集合中获取满足某个条件的所有元素组成的新的集合，原集合不变；
     *
     * 特点：返回新集合
     */
    @Test
    fun testFilter() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val flist = people.filter { it.age > 10 }//获取年龄大于10岁的人组成的新的集合
        println("$people \n $flist")
    }

    /**
     * map：转化每一个元素
     * 接收一个Lambda表达式参数(函数类型的参数)
     * 转换原集合中的每一个元素，所有元素被转换后组成新的集合返回；原集合不变
     *
     * 特点：返回新集合
     */
    @Test
    fun testMap() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val mlist = people.map { it.name }//获取原集合中每一个人的名字组成新的集合返回
        println("$people \n $mlist")// [Jack1, Jack2, Jack3]
    }

    /**
     * all
     * 集合中的所有元素都符合某个条件返回true，否则返回false
     */
    @Test
    fun testAll() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val boo = people.all { it.age < 100 }
        println(boo)
    }

    /**
     * any
     * 集合中至少有一个元素符合某个条件则返回true，否则返回false
     */
    @Test
    fun testAny() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val boo = people.any { it.age > 20 }
        println(boo)
    }

    /**
     * count
     * 根据给定的条件判断集合中满足条件的元素的个数,返回int个数
     */
    @Test
    fun testCount() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val count = people.count { it.age > 0 }
        println(count)
    }

    /**
     * find
     * 根据给定的条件，从集合中找到第一个(如果有多个元素都满足也只返回第一个)满足条件的元素并返回，如果找不到则返回null，
     *
     * find有一个等价的替代方法:firstOrNull,
     */
    @Test
    fun testFind() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        val person = people.find { it.age > 11 }
        val person2 = people.firstOrNull { it.age > 11 }
        println("$person \n $person2")
    }


    /**
     * flatMap{返回可迭代的对象的Lambda}
     * 1：先对原集合中的各个元素做map变换操作；
     * 2：各个元素的各个操作结果组合成一个list返回，注意，这个返回的list可以有重复的元素；
     */
    @Test
    fun testFlatmap() {
        val books = listOf(Book("三国演义", listOf("张三", "李四")),
                Book("水浒传", listOf("李四", "刘能")),
                Book("西游记", listOf("王五")))

        val libook = books.flatMap { it.authers }
        println("flatmap:$libook")//[张三, 李四, 李四, 刘能, 王五]
    }


    /**
     * groupBy
     * 将集合中的所有元素按照给定的条件(key)分组，分组后条件作为map的key，符合该条件的元素形成的list作为map的value，
     * 组成一个map返回
     */
    @Test
    fun testGroup() {
        val people = listOf(Person("Jack1", 30),
                Person("Jack2", 30),
                Person("Jack3", 10),
                Person("Jack4", 30))
        val groupmap = people.groupBy { it.age }
        println(groupmap)
    }


    /**
     * 参考：kotlin in action page121
     *
     *集合操作转换为序列操作
     *
     * 为什么使用学历操作集合
     * 从filter,map操作的缺点：
     * 1：都会产生新的集合；
     * 2：都会一次性操作完集合的所有元素才会进行下一步；
     * 如果进行数据量比较大的集合操作就会影响性能；
     *
     * 序列就没有上述问题
     * asSequence():将一个集合转换为序列；
     * toList()：将序列转换为集合；应为对序列的操作很多集合的API无法使用，例如通过下标访问集合元素；
     *
     * 对序列的操作分为中间操作和末端操作：
     * 怎么区分这两种操作？
     * 中间操作：会返回新的序列，这个 序列知道如何变换原始序列中的元素；
     * 末端操作：返回最终的结果，可以是一个集合、元素、数字等；
     *
     * 中间操作是惰性的，只有调用末端操作时中间操作才真正执行，否则中间操作不执行；
     *
     * 序列操作的特点：注意与集合操作的对比
     * 以people.asSequence().map { it.age }.filter { it > 10 }为例
     * people中的各个元素依次执行map和filter，即people[0]->map->filter,people[1]->map->filter,people[2]->map->filter...
     * 而不是people中的所有元素全部执行一次map，再在map的结果的基础上所有的元素再全部执行依次filter，
     * 这样做的好处是不会产生新的集合，而是针对原集合中的各个元素依次执行map和filter等操作；效率更高；
     *
     * 注意某些情况下，先执行map后执行filter，还是先执行filter后执行map在效率上是有却别的，具体根据 业务逻辑区分；
     *
     */
    @Test
    fun testRes() {
        val people = listOf(Person("Jack1", 10), Person("Jack2", 20), Person("Jack3", 30))
        //下面这行代码并没有执行，因为map和filter是中间操作，是惰性的
        people.asSequence().map { println("map"); it.age }.filter { println("filter"); it > 10 }
        //下面这行的map和filter才会执行，因为执行了末端操作，返回一个list
        people.asSequence().map { println("map"); it.age }.filter { println("filter"); it > 10 }.toList()
        //下面的代码也会执行，因为你需要一个结果
        val result = people.asSequence().map { println("map"); it.age }.filter { println("filter"); it > 10 }
        println("结果哈哈：$result")
    }


    /**
     * 参考：kotlin in action page125
     *
     * 创建序列
     * 给定序列的第一个元素，其它元素根据第一个元素以及给定的条件自动生成
     *
     * takeWhile:根据给定条件获取子序列
     */

    @Test
    fun testCreate(){
        val seq = generateSequence(0) { it+1 }
        val sub = seq.takeWhile { it<20 }
        val list = sub.toList()
        println(list)
    }

}