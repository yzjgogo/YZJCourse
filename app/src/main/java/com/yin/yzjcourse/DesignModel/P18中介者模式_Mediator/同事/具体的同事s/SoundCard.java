package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;

public class SoundCard extends Colleague {
    public SoundCard(Mediator mediator) {
        super(mediator);
    }
    /**
     * 播放音频数据
     *
     */
    public void soundPlay(String data){
        System.out.println("音频：" + data);
    }
}
