package com.yin.yzjcourse;

import com.yin.yzjcourse.structure.ArrayListY;
import com.yin.yzjcourse.structure.LinkedListY;
import com.yin.yzjcourse.structure.SingleLinkedList;
import com.yin.yzjcourse.utils.DLog;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

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



        ArrayListY<String> arrayListY = new ArrayListY();
        arrayListY.add("a");
        arrayListY.add("b");
        arrayListY.add("c");
        arrayListY.add("d");
//        System.out.println("输出："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));
//        arrayListY.remove(2);
//        System.out.println("输出2："+arrayListY.size()+" ， "+ Arrays.toString(arrayListY.getElementData()));

        Iterator<String> iterator = arrayListY.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("c")) {
                iterator.remove();
            }
            System.out.println("迭代器："+next);
        }

        ArrayList<String> mList = new ArrayList<>();
        mList.add("1");
        mList.add("2");
        mList.add("3");
        Iterator<String> mIter = mList.iterator();
        while (mIter.hasNext()) {
            String next = mIter.next();
            System.out.println("原来的迭代器："+next);
        }

        SingleLinkedList<String> singleLinkedList = new SingleLinkedList<>();
        singleLinkedList.addFirst("a");
        singleLinkedList.addLast("b");
        singleLinkedList.addLast("c");
        singleLinkedList.addLast("d");
        singleLinkedList.addLast("e");
//        singleLinkedList.add(3,"d");
//        singleLinkedList.add(0,"e");
//        singleLinkedList.add(5,"e2");
//        System.out.println("查找："+singleLinkedList.get(1));
//        singleLinkedList.remove(0);
//        singleLinkedList.remove(2);
//        singleLinkedList.remove("a");
//        singleLinkedList.remove("c");
//        singleLinkedList.remove("e");
//        singleLinkedList.set(2,"cc");

//        System.out.println("地址："+System.identityHashCode(singleLinkedList)+ " , "+ System.identityHashCode(singleLinkedList.getNode(0)));
//        System.out.println("地址："+singleLinkedList+ " , "+ singleLinkedList.getNode(0));

        System.out.println("单链表长度："+singleLinkedList.size());
        for(int i=0;i<singleLinkedList.size();i++){
            String str = singleLinkedList.get(i);
            System.out.println("单链表："+str+" , "+i);
        }
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        System.out.println("链表长度："+linkedList.size());
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println("链表遍历："+linkedList.get(i)+" , "+i);
        }
//        linkedList.getLast()
        */
        int size = 100;
        //0,0
        //1,0
        //2,1
        //3,1
        //4,2
        //100,50
//        System.out.println("100位运算："+(size >> 1));
        LinkedListY<String> linkedListY = new LinkedListY<>();
        linkedListY.add("a");
        linkedListY.add("b");
            String temp = linkedListY.get(0);
            System.out.println("取出："+temp);
//        for(int i=0;i<linkedListY.size();i++){
//            String temp = linkedListY.get(i);
//            System.out.println("遍历："+temp+ " , " +i);
//        }
    }
}