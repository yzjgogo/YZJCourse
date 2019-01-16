package com.yin.yzjcourse.DesignModel.P7_2外观模式;

import com.yin.yzjcourse.DesignModel.P7_2外观模式.外观类.HomeTheaterFacade;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.Amplifier;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.CdPlayer;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.DvdPlayer;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.PopcornPopper;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.Projector;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.Screen;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.TheaterLights;
import com.yin.yzjcourse.DesignModel.P7_2外观模式.子系统类s.Tuner;

/**
 * 外观模式：
 *      提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层接口，让子系统更容易使用。
 *      外观模式可以让客户和子系统之间避免紧耦合。
 *
 * 外观模式并没有改变原来的子系统，如果你愿意你仍然可以还对原来的子系统 操作，外观只是提供一种更便捷的操作
 * 子系统的方式。
 *
 * 外观可以增加子系统的功能，只要你愿意或有需要的话。
 *
 * 参考图片：fade_p.png
 *
 *
 * 设计原则：
 *  最少知识原则：只和你的密友谈话。意思是避免一段逻辑有太多的类参与，如果这样，修改系统的一部分就可能影响到其它部分。
 *              如果许多类相互依赖，说明这个系统很脆弱。
 *
 *  最少知识原则的实践方针,参考图片：least_knowledge.png
 *  对于一个对象obj的方法f内，只能调用属于一下范围的方法：
 *  1：该对象本身的方法，例如obj的方法f2;
 *  2：被当做方法的参数而传递进来的对象的方法，例如obj的f(obj2)的参数obj2的方法可以使用；
 *  3：f方法内部创建或实例化的任何对象，例如你在f内new obj3，则obj3的方法可以调用；
 *  4：对象的任何组件，例如obj的属性是obj4，则可调用obj4的方法。
 *  注意，如果某个对象是调用其它方法的返回结果，则不建议调用该对象的方法，例如：
 *  public float getTemp(){
 *      Thermometer thermometer = station.getThermometer();
 *      return thermometer.getTemperature()//不建议调用thermometer的方法。
 *  }
 *  我们可以在station类中定义一个getTemperature()方法即可:
 *  public float getTemp(){
 *      return station.getTemperature()
 *  }
 *
 *
 *  外观模式用到了最少知识原则：参考图片：fade_p_2.png
 *
 */
public class HomeTheaterTestDrive {
	public static void main(String[] args) {
	    //实例化各个子系统
		Amplifier amp = new Amplifier("Top-O-Line Amplifier");
		Tuner tuner = new Tuner("Top-O-Line AM/FM Tuner", amp);
		DvdPlayer dvd = new DvdPlayer("Top-O-Line DVD Player", amp);
		CdPlayer cd = new CdPlayer("Top-O-Line CD Player", amp);
		Projector projector = new Projector("Top-O-Line Projector", dvd);
		TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
		Screen screen = new Screen("Theater Screen");
		PopcornPopper popper = new PopcornPopper("Popcorn Popper");

		//通过各个子系统创建外观，由外观统一管理子系统
		HomeTheaterFacade homeTheater =
				new HomeTheaterFacade(amp, tuner, dvd, cd, 
						projector, screen, lights, popper);

		//想看电影，外观一步搞定，无需各个子系统依次调用了
		homeTheater.watchMovie("Raiders of the Lost Ark");
		homeTheater.endMovie();//停止看电影也是如此
	}
}
