package com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式;


import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.被观察者.被观察具体类.WeatherData;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s.CurrentConditionsDisplay;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s.ForecastDisplay;
import com.yin.yzjcourse.DesignModel.P2观察者模式.自定义观察者模式.观察者.观察者具体类s.StatisticsDisplay;

/**
 * 设计原则:
 * 封装变化：
 * 多用组合，少用继承：
 * 针对接口编程，不针对实现编程：
 * 这里是，被观察者只接收抽象对象Observer，具体你是哪一个Observer，我是不管的。
 * 为交互对象之间的松耦合设计而努力：
 * 这里是，观察者和被观察者之间没有任何关系，只要二者遵守接口的约定即可，无论你增减观察者还是修改观察者，
 * 对被观察者来说都无所谓。被观察者也是一样，二者可以随便修改 ，只要你遵守接口的底线即可。
 * 别主动找我，我会叫你的(好莱坞原则)：被观察者一般是主动通知所有观察者，而观察者一般不会主动调用被观察者，单向联系。
 * <p>
 * <p>
 * 观察者模式：
 * 定义了对象之间的一对多依赖，这样一来，当一个对象改变状态时，它的所有依赖者都会收到通知并自动更新。
 *
 * 参考：observer_1.png、observer_2.png
 */
public class Test {

    public static void main(String[] args) {

        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay =
                new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        //模拟数据更新操作，触发被观察者数据变化，通知所有观察者
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
