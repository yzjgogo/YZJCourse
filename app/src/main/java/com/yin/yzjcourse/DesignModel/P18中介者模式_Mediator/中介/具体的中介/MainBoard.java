package com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.具体的中介;

import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.中介.抽象的中介.Mediator;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.CDDevice;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.CPU;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.抽象的同事.Colleague;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.GraphicsCard;
import com.yin.yzjcourse.DesignModel.P18中介者模式_Mediator.同事.具体的同事s.SoundCard;

/**
 * 这是具体的中介者，主板，用于协光驱、CPU、显卡、声卡等各个同事
 */
public class MainBoard extends Mediator {
    /**
     * 中介者需要持有各个同事的引用才能协调到所有同事
     * 就好像主板中有光驱、CPU、显卡、声卡等。
     */
    private CDDevice cdDevice; //光驱设备
    private CPU cpu; //CPU
    private SoundCard soundCard; //声卡设备
    private GraphicsCard graphicsCard; //显卡设备

    /**
     * 这是关键方法
     * 中介者在这里发挥作用，协调各个同事
     * @param c 同事对象
     */
    @Override
    public void changed(Colleague c) {
        //如果光驱加载了“音视频”数据
        if(c == cdDevice){
            handleCD((CDDevice) c);
        }
        //如果CPU处理完数据，“音视频”被解析成“音频数据”和“视频数据”
        else if(c == cpu){
            handleCD((CPU) c);
        }
    }
    /**
     * 处理光驱加载数据后与其他设备的交互
     *
     * @param cdDevice 光驱设备
     */
    public void handleCD(CDDevice cdDevice){
        //光驱加载“音视频”后，由CPU解析成“音频数据”和“视频数据”
        //decodeData()解析后又会通知中介者的changed(CPU)
        cpu.decodeData(cdDevice.read());
    }
    /**
     * 处理CPU解析出“音频数据”和“视频数据”就交给显卡和声卡播放
     *
     * @param cpu CPU
     */
    public void handleCD(CPU cpu){
        soundCard.soundPlay(cpu.getDataSound());
        graphicsCard.videoPlay(cpu.getDataVideo());
    }
    /**
     * 设置CD设备
     * 不用关心
     *
     */
    public void setCDDevice(CDDevice cdDevice){
        this.cdDevice = cdDevice;
    }
    /**
     * 设置CPU
     * 不用关心
     * @param cpu CPU
     */
    public void setCPU(CPU cpu){
        this.cpu = cpu;
    }
    /**
     * 设置声卡设备
     * 不用关心
     * @param soundCard 声卡设备
     */
    public void setSoundCard(SoundCard soundCard){
        this.soundCard = soundCard;
    }
    /**
     * 设置显卡设备
     * 不用关心
     * @param graphicsCard 显卡设备
     */
    public void setGraphicsCard(GraphicsCard graphicsCard){
        this.graphicsCard = graphicsCard;
    }
}
