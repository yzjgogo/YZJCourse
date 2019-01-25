package com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象池;

import com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象.Ticket;
import com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象.TrainTicket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 已对象池的方式实现对象的复用，避免了大量创建对象的压力。
 *
 * 解决一个疑惑，不同时间从对象池中拿来的对象能直接用吗，注意，这里不是让你直接用从对象池中取出的对象，
 * 只是让你避免new对象，你完全可以调用从对象池中取出的对象的setXX等方法改变对象的状态。但对象还是那个对象。
 */
public class TicketFactory {
    //存放有限个已经创建的对象
    static Map<String, Ticket> sTicketMap = new ConcurrentHashMap<String, Ticket>();

    /**
     * form和to是对象的内部状态，不会改变，因此可作为对象池中对象的key。
     * 这样，即使有10000个人买从“北京到青岛”的车票，也只需创建一个对象而已。
     * @param from
     * @param to
     * @return
     */
    public static Ticket getTicket(String from, String to) {
        String key = from + "-" + to;
        if (sTicketMap.containsKey(key)) {//如果对象中有缓存，则从缓存中取出服用，这样就不用重新创建了
            System.out.println("使用缓存==>" + key);
            return sTicketMap.get(key);
        } else {
            //如果对象没有缓存，则才创建对象，同时把这个新建出的对象放到对象池中，供下层次缓存使用
            System.out.println("创建对象==>" + key);
            Ticket ticket = new TrainTicket(from, to);
            sTicketMap.put(key, ticket);
            return ticket;
        }

    }
}

/**
 * 这种方式不可取
 * 加入我有10000个人买票，则要创建10000个对象，这样势必导致频繁GC，内存抖动，内存溢出。
 */
//public class TicketFactory{
//    public static Ticket getTicket(String from,String to){
//        return new TrainTicket(from,to);
//    }
//}


