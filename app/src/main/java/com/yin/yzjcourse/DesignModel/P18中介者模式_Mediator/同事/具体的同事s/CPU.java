package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;

public class CPU extends Colleague {
    private String dataVideo, dataSound; //视频和音频数据
    public CPU(Mediator mediator) {
        super(mediator);
    }
    /**
     * 获取视频数据
     * 显卡GraphicsCard需要“视频数据”
     *
     * @return 视频数据
     */
    public String getDataVideo(){
        return dataVideo;
    }
    /**
     * 获取音频数据
     * 声卡SoundCard需要“音频数据”
     *
     * @return 音频数据
     */
    public String getDataSound(){
        return dataSound;
    }

    /**
     * 将光驱CDDevice中的"音视频"数据解析成“音频数据”和“视频”，之后
     * 会分别交给显卡GraphicsCard和声卡SoundCard处理。
     * 此时将该变化通知中介者
     * @param data 光驱CDDevice中的"音视频"数据
     */
    public void decodeData(String data){
        //分割音、视频数据
        String[] tmp = data.split(",");
        //解析音、视频数据
        dataVideo = tmp[0];
        dataSound = tmp[1];
        //告诉中介者自身状态改变
        mediator.changed(this);
    }
}
