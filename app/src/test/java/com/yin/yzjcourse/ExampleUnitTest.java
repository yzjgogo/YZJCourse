package com.yin.yzjcourse;

//import com.yin.yzjcourse.mykt.MyTest;
import com.yin.yzjcourse.utils.DLog;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        DLog.eLog("试一试");
    }
    @Test
    public void testAdd() throws Exception{
//        MyTest myTest = new MyTest();
//        myTest.get(1,3);
        int a = 1000000;
        Integer a1 = a;
        Integer a2 = a1;

//        DLog.eTest(a1.equals(a2)+"");
        if(a1.equals(a2)){
            DLog.eTest("a1 equals a2");
        }else {
            DLog.eTest("a1 不 equals a2");
        }
        if(a1==a2){
            DLog.eTest("相等");
        }else {
            DLog.eTest("不相等");
        }

        byte b = 1;
        int c = b;
        DLog.eTest("c:"+c);
    }
}