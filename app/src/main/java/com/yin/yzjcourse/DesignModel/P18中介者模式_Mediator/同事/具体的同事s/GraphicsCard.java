package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;

public class GraphicsCard extends Colleague {
    public GraphicsCard(Mediator mediator) {
        super(mediator);
    }
    /**
     * 播放视频数据
     *
     */
    public void videoPlay(String data){
        System.out.println("视频：" + data);
    }
}
