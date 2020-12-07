package com.yin.yzjcourse;

import com.yin.yzjcourse.structure.ArrayListY;
import com.yin.yzjcourse.utils.DLog;

import org.junit.Test;

import java.util.Arrays;

public class ExampleUnitTest {

    @Test
    public void myTest() {
        /*
        String abc = "dddd";
        System.out.println(abc);

        String my = null;
        System.out.println(abc.equals(my));

        int[] configSpec = new int[]{10, 11, 12, 13, 14, 15};
        final int len = configSpec.length;
        final int[] newConfigSpec = new int[len + 2];
        System.arraycopy(configSpec, 0, newConfigSpec, 0, len - 1);
        System.out.println("输出：" + Arrays.toString(configSpec) + "," + Arrays.toString(newConfigSpec));

//        int result = (int) Math.sqrt(9);

        double mm = Math.toDegrees(Math.atan(1));
        System.out.println("结果WWW：" + mm);

        String astr = "abc";
        String bstr = String.join(astr,"def");
        System.out.println("字符串拼接："+astr+ " | " +bstr);



//        String[] strArr = {"a","b"};
//        String[] newStrArr = Arrays.copyOf(strArr,2);
//        //数组：Arrays.copyOf(strArr,1) -> [a] , false , false
//        //数组：Arrays.copyOf(strArr,2) -> [a, b] , true , false
//        //数组：Arrays.copyOf(strArr,3) -> [a, b, null] , false , false
//        System.out.println("数组："+Arrays.toString(newStrArr) +" , "+Arrays.equals(newStrArr,strArr) +" , "+(newStrArr == strArr));


        //arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        //src：被copy的原数组；
        //srcPos：从原数组的srcPos位置的元素开始逐个copy；
        //dest：要copy到的目标数组；
        //destPos：目标数组从第destPos个index开始接收copy的元素
        //length：src从开始copy的srcPos开始，一共要copy共length个元素
        int[] configSpec = new int[]{10, 11, 12, 13, 14, 15};
        System.out.println("地址1：" + configSpec);
        final int len = configSpec.length;
        final int[] newConfigSpec = new int[len + 2];
        System.arraycopy(configSpec, 2, configSpec, 2+1, 6-2);
        System.out.println("地址2：" + configSpec);
        //输出：[10, 11, 12, 13, 14, 15],[0, 0, 0, 0, 12, 13, 14, 0]
        System.out.println("输出：" + Arrays.toString(configSpec) + "," + Arrays.toString(newConfigSpec));

        */

        ArrayListY arrayListY = new ArrayListY();
        arrayListY.add("a");
        arrayListY.add("b");
        arrayListY.add("c");
        arrayListY.add("d");
        System.out.println("输出："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));
        arrayListY.remove(2);
        System.out.println("输出2："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));


    }
}