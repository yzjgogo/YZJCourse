package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;

public class CDDevice extends Colleague {
    private String data; //视频数据
    public CDDevice(Mediator mediator) {
        super(mediator);
    }
    /**
     * 光驱中的“音视频”数据需要被读取出来，供CPU解析成“音频数据”和“视频数据”
     * 分别交给显卡GraphicsCard和声卡SoundCard处理。
     */
    public String read(){
        return data;
    }
    /**
     * 这是整个案例的第一步，插入CD，加载“音视频”
     * 此时光驱有变化了，通知中介者。
     */
    public void load(){
        data = "视频数据,音频数据";
        //告诉中介者自身状态改变
        mediator.changed(this);
    }
}
