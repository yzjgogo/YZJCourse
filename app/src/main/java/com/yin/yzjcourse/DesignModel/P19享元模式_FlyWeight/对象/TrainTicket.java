package com.yin.yzjcourse.DesignModel.P19享元模式_FlyWeight.对象;


import java.util.Random;

/**
 * 可能需要频繁创建使用的业务对象
 */
public class TrainTicket implements Ticket {

    /**
     * from和to是构造函数的参数，参与了对象的创建，是对象的内部状态，不会随着环境的变化而变化，
     * 因此可用于对象池中对象的标识；
     * 出发地和目的地肯定不变。
     */
    public String from;// 始发地
    public String to;// 目的地
    /**
     * bunk和price是对象的外部状态，会随着环境的变化而变化，这里价格会随着铺位的变化而变化，卧铺和坐票肯定不一样
     * 但是出发地和目的地肯定不变
     */
    public String bunk;// 铺位
    public int price;

    public TrainTicket(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void showTicketInfo(String bunk) {
        price = new Random().nextInt(300);
        System.out.println("购买从" + from + "到" + to + "的" + bunk + "火车票"
                + ",价格:" + price);
    }

}
