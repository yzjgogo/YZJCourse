package com.yin.yzjcourse.MultiProcess.AndroidMulti;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.TextView;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
    除了JNIAndroid中使用多进程唯一的方式就是给四大组件指定android:process属性。
    process属性也有两种指定方式：
    1：android:process=":remote"
        注意是冒号‘:’，省略了包名，完整进程名是：com.yin.yzjcourse:remote，该进程属于当前应用的私有进程，
        其它应用的组件不可以和它跑在同一个进程中。
    2：android:process="com.yin.yzjcourse.remote"
        没有省略任何东西，完整的进程名，该进程属于全局进程，其它应用可通过ShareUID方式和它跑在同一个进程中。
    因此两种声明process的区别就是是否可以与其他应用共享数据。

    UID：从Linux系统衍生而来，Linux是多用户系统，UID表示UserId，即哪个用户运行了这个程序，主要用于权限的管理，
        而Android是单用户系统，因此Android中UID有新的含义：Android为每个应用都分配了不同的id，用于实现数据共享。

    假设有A，B两个应用要共享数据，则需要至少满足两个条件：
    1：A和B要用共同的ShareUID,这个通过给每个应用的清单文件的<manifest/>标签设置相同的android:sharedUserId="任意"
        来实现。
    2：A和B两个应用要有相同的签名，否则别人知道了我定义的sharedUserId就可以获取我的数据了。
    另外要让A和B可以相互访问还需要权限。

    参考:https://www.cnblogs.com/kings-boke/p/4268930.html


    设置了android:process属性后，出现了哪些变化？
    Android系统会为每个应用分配一个独立的虚拟机，准确的说是会为每个进程分配一个独立的虚拟机，每一个虚拟机相互独立，
    在整个内存中瓜分不同的内存块，彼此完全隔离，你给一个应用通过android:process开启多进程后，每一个进程都会复制一
    套完整的应用代码到自己的内存块中，这些代码不存在任何关系，要说有关系，那关系就是都是从程序员写的代码中复制过来的，
    既然多个进程拥有不同的内存空间，那也就不存在通过内存共享数据的可能了，因此就能解释我们举的例子中mStatus为什么
    会出现这种情况了，因为MultiMainActivity中的mStatus和MultiSecondActivity中的mStatus根本不是同一个东西。

    使用多进程后，所有和内存相关的都完全失效，例如：
    1：静态成员和单例模式完全失效，(因为各自处于不同的地址)
    2：线程同步机制完全失效，(因为线程同步是通过锁对象或锁全局类来实现的，因此处于不同内存的肯定不是同一个对象，本质上和第1条一样)
    3：SharedPreferences的可靠性下降，因为可能出现并发读写(多个进程同时读写一个SharedPreferences),数据不安全。
    4：Application会多次创建，其实不光Application会多次创建，你给应用开启多线程，其实就相当于开启了一个新的应用，这个
        新的应用和其它线程的应用拥有完全相同的代码，每个应用当然都有属于 自己的Application.

    给一个应用开启多线程，可以这样理解：相当于多个不同的应用采用了SharedUID的模式。

    一句话总结：应用的多进程就是产生多个应用(多套代码)分身，彼此完全无关。

    因为多进程之前无法通过内存地址通信，所以Android提供了一些跨进程通信的方式。。。。下面一个个讲到。
 */
public class MultiMainActivity extends BaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_main);
        ButterKnife.bind(this);
        //我在这里修改了mStatus的值,案例说mStatus已经变成了 101，但是你在MultiSecondActivity获取mStatus看看，它还是100
        tv.setText(Process.myPid() + "\n" + ProcessUtils.getProcessName(this)+"\n"+Process.myUid()+"\n"+
                (++ProcessUtils.mStatus));
    }

    @OnClick({R.id.bt_second, R.id.bt_third})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_second:
                startActivity(new Intent(this, MultiSecondActivity.class));
                break;
            case R.id.bt_third:
                startActivity(new Intent(this, MultiThirdActivity.class));
                break;
        }
    }
}
