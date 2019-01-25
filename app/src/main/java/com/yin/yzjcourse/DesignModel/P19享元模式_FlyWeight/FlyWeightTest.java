package com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight;

import com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象.Ticket;
import com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象池.TicketFactory;

/**
 * 享元模式：
 * 通过使用对象池，避免大量对象的创建。
 *
 */
public class FlyWeightTest {
    public static void main(String[] args) {
        //这次会创建，并放入对象池
        Ticket ticket01 = TicketFactory.getTicket("北京", "青岛");
        ticket01.showTicketInfo("上铺");
        //因此from和to在在对象池存在，所以这次不会创建，直接从对象池取出复用。
        Ticket ticket02 = TicketFactory.getTicket("北京", "青岛");
        ticket01.showTicketInfo("下铺");
        //同理，也不会创建
        Ticket ticket03 = TicketFactory.getTicket("北京", "青岛");
        ticket01.showTicketInfo("坐票");
    }
}
