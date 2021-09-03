package com.yin.yzjcourse;

import com.yin.yzjcourse.structure.ArrayListY;
import com.yin.yzjcourse.structure.LinkedListY;
import com.yin.yzjcourse.structure.SingleLinkedList;
import com.yin.yzjcourse.utils.DLog;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class ExampleUnitTest {

    private BigDecimal fromFenToYuan(int fen){

        return new BigDecimal(
                Double.toString(fen)
        )
                .divide(
                        new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP
                );
    }

    @Test
    public void justTest(){
boolean a = Objects.equals(null, null);
        System.out.println("结果："+a);
    }
    @Test
    public void myTest() {

        BigDecimal big = fromFenToYuan(2345);
        System.out.println(big);
        System.out.println(big.toString());



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
//            System.out.println("取出："+temp);
//        for(int i=0;i<linkedListY.size();i++){
//            String temp = linkedListY.get(i);
//            System.out.println("遍历："+temp+ " , " +i);
//        }


        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            LinkedList
        }

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("d");
        linkedList.add("e");
        ListIterator<String> listIterator = linkedList.listIterator(3);
//        while (listIterator.hasNext()) {
//            String next = listIterator.next();
//            System.out.println("遍历："+next);
//        }
        while (listIterator.hasPrevious()) {
            String next = listIterator.previous();
            System.out.println("遍历："+next);
        }
        */
//        String abc = "a-b-c";
//        String abc = "abc";
//        System.out.println("输出："+abc.lastIndexOf("-"));
        SortedSet<MyEntity> sortedSets = new TreeSet<>();
        sortedSets.add(new MyEntity("m-5"));
        sortedSets.add(new MyEntity("m"));
        sortedSets.add(new MyEntity("m-3"));
        sortedSets.add(new MyEntity("ab"));
        sortedSets.add(new MyEntity("等待"));
        sortedSets.add(new MyEntity("mc-3"));
        sortedSets.add(new MyEntity("aee-34"));
        sortedSets.add(new MyEntity("ap-1"));
        System.out.println("输出："+sortedSets.toString() + " , "+ sortedSets.size());

//        int a = 10;
//        int b = 20;
//        if(a<b)
//            System.out.println("输出1");
//            System.out.println("输出2");

//        String abc = "音频-本地-2";
////        String abc = "音频-本地2";
////        String abc = "音频本地2";
//        int first = abc.indexOf("-");
//        int last = abc.lastIndexOf("-");
//        System.out.println("输出:"+first+","+last);
//        if (first!=last && first!=-1 && last!=-1) {
//            String sub = abc.substring(last+1)+abc.substring(first+1,last);
//            System.out.println("输出:"+sub);
//        }
    }
class MyEntity implements Comparable<MyEntity>{
        public String content;

    public MyEntity(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(MyEntity o) {
        int oOrder = 0;
        int thisOrder = 0;
        if (o != null) {
            int oStartIndex = o.content.lastIndexOf("-");
            if (oStartIndex>=0) {
                try {
                    oOrder = Integer.parseInt(o.content.substring(oStartIndex+1,o.content.length()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        int thisStartIndex = this.content.lastIndexOf("-");
        if (thisStartIndex>=0) {
            try {
                thisOrder = Integer.parseInt(this.content.substring(thisStartIndex+1,this.content.length()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("输出:"+oOrder+","+thisOrder);
//        if (oOrder == 0 || thisOrder == 0) {
//            return -1;
//        }
        if (oOrder>thisOrder) {
            return -1;
        }else if(oOrder<thisOrder){
            return 1;
        }
        return 1;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MyEntity myEntity = (MyEntity) o;
//        return Objects.equals(content, myEntity.content);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(content);
//    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "content='" + content + '\'' +
                '}';
    }
}
}