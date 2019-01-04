package com.yin.yzjcourse.k1函数.k7高阶函数.k3java调用有函数类型参数的kotlin函数;

import com.yin.yzjcourse.utils.DLog;

import org.junit.Test;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/**
 * java调用kotlin的带有函数类型参数的函数时，传入这个函数类型的参数应该是FunctionN接口的实例，该接口里
 * 的invoke()函数，就是函数类型参数的函数体(实参)
 * kotlin定义了一系列接口：
 * FunctionO<R> ： invoke()没有参数，有返回值
 * Function1<P1,R> : invoke()有1个参数，有返回值
 * Function2<P1,P2,R> : invoke()有2个参数，有返回值
 * Function3<P1,P2,P3,R> : invoke()有3个参数，有返回值
 *                      .
 *                      .
 * FunctionN<P1,P2,..Pn,R> : invoke()有n个参数，有返回值
 *
 * 其中的返回值需要特别注意：如果kotlin中定义的函数类型没有返回值(实际上有返回Unit)，则仍按有返回值处理，因为
 * 没有返回值实际上也是有返回值Unit的。
 */

public class MyJava {
    private MyKotlin myKotlin = new MyKotlin();

    @Test
    public void test1() {
        myKotlin.kotfun1(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //...逻辑
                return Unit.INSTANCE;//即使kotlin函数没有返回值，其实隐含返回了Unit，所以在java里要手动返回Unit
            }
        });
    }

    @Test
    public void test2() {
        myKotlin.kotfun2(new Function1<Integer, Unit>() {
            @Override
            public Unit invoke(Integer integer) {
                //...逻辑
                DLog.eTest("" + integer);//10
                return Unit.INSTANCE;
            }
        });
    }

    @Test
    public void test3() {
        myKotlin.kotfun3(new Function1<Integer, String>() {
            @Override
            public String invoke(Integer integer) {
                //...逻辑
                return integer != null ? integer.toString() : "null";
            }
        });
    }

    @Test
    public void test4() {
        myKotlin.kotfun4("Fuck", new Function2<Integer, String, String>() {
            @Override
            public String invoke(Integer integer, String s) {
                //...逻辑
                return integer+s;
            }
        });
    }
}
