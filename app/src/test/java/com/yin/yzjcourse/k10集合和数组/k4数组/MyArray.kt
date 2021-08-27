package com.yin.yzjcourse.k10集合和数组.k4数组

import org.junit.Test

/**
 * Kotlin 标准库支持一套和集合相同的用于数组的扩展函数
 * 例如，filter,map操作等等，总之你可以像操作集合一样操作数组
 */
class MyArray {
    /**
     * 数组的创建
     */
    @Test
    fun testCreate() {
        //工厂方法
        val intArr = arrayOf(1, 2, 3)
        val strArr = arrayOf("a", "b", "c")

        //创建一个指定长度的元素可空的数组，参数是数组的长度，创建后默认返回一个所有元素都是null的数组
        val nullArr = arrayOfNulls<String>(3)//[null,null,null]
        for (i in nullArr.indices) {
            println("nullArr[$i]=${nullArr[i]}")
        }

        println("----------------------")


        //构造方法接收一个数组的大小，和lambda表达式，lambda表达式用于计算每一个数组元素
        //其中i或这it就是索引
        val letterArr = Array<Int>(3) { i -> i + 10 }//val letterArr = Array<Int>(3){it+10}
        //集合也可以通过构造方法实现
//        val mList = List<String>(3){"${it}--"}
//        println(mList)

        for (i in letterArr.indices) {
            println("letterArr[$i]=${letterArr[i]}")
        }

    }


    /**
     * 对于Array<T> Array<Int>是装箱后的数组，效率较低
     * 基本数据类型的数组，类似java的int[]，byte[]，char[]
     * kotlin对应的有IntArray，ByteArray，CharArray，BooleanArray等等
     *
     * 基本类型创建，以IntArray为例
     */
    @Test
    fun testBaseArray() {
        //工厂函数创建基本类型数组
        val intArr1 = intArrayOf(1, 2, 3)


        //构造器创建一个指定长度的基本类型数组，数组元素是具体类型的默认值
        //这里创建了长度是3的int数组，各个元素默认值是0
        val intArr2 = IntArray(3)//[0,0,0]
        for (i in intArr2.indices) {
            println("intArr2[$i]=${intArr2[i]}")
        }


        //it是索引
        val intArr3 = IntArray(3) { it * it }
        for (i in intArr3.indices) {
            println("intArr3[$i]=${intArr3[i]}")
        }
    }


    /**
     * 集合转数组
     */
    @Test
    fun testCollectionToArray() {
        val list = listOf(1, 2, 3)
        val array = list.toTypedArray()
        for (i in array.indices) {
            println("array[$i]=${array[i]}")
        }
    }


    /**
     * 装箱的数组或集合转基本类型的数组
     */
    @Test
    fun testToBaseArray() {
        //装箱的集合转对应基本类型的数组
        val list = listOf(2, 4, 6)
        val intArr1 = list.toIntArray()

        //装箱的数组转对应基本类型的数组
        val array = arrayOf(3, 5, 7)
        val intArr2 = array.toIntArray()
    }


    /**
     * 数组的遍历
     * 与集合的遍历完全相同
     */
    @Test
    fun testIterator() {
        val arr = arrayOf(3, 5, 7)
        //1:只遍历元素
        arr.forEach { println(it) }

        //2:也是只遍历元素，使用匿名函数
        println("$$$$$$$$$$$$$$$$$$$$$$$$$$$")
        arr.forEach(fun(element: Int) {
            println("匿名：$element")
        })

        //3：也是只遍历索元素
        println("=============================")
        for (element in arr) {
            println(element)
        }

        //4：遍历索引
        println("*******************************")
        for (i in arr.indices) {
            println("arr[$i]=${arr[i]}")
        }

        //5：同时遍历索引和元素
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        arr.forEachIndexed { index, element -> println("tag：arr[$index]=$element") }

        //6：同时也是同时遍历索引和元素
        println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
        for ((index, element) in arr.withIndex()) {
            println("with：arr[$index]=$element")
        }
    }





    /**
     * 数组的解构声明和组件函数
     */
    @Test
    fun testcomponent(){
        /**
         * 参考:[com.yin.yzjcourse.k20运算符重载与约定.k9_1解构声明_声明多个变量.testCollectionAndArray]
         */
    }
}