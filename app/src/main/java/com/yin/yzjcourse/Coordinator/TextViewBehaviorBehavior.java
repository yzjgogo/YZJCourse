package com.yin.yzjcourse.Coordinator;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar.SnackbarLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
  自定义Behavior并不复杂，但是要考虑到两个关键元素：child和dependency
  child就是这个自定义的Behavior的承受者，即这个Behavior作用到这个child上
 dependency就像一个扳机一样，来出发child的Behavior
 这里的child就是这个TextView而dependency就是SnackbarLayout

 自定义Behavior要首先继承CoordinatorLayout.Behavior<V extends View>
 然后要至少重写layoutDependsOn和onDependentViewChanged两个方法。
 */
public class TextViewBehaviorBehavior extends CoordinatorLayout.Behavior<TextView> {

  public TextViewBehaviorBehavior(Context context, AttributeSet attrs) {
  }

  /**
   * 当布局发生变化的时候就会执行该方法，当我们确定了dependency后就要返回true，触发onDependentViewChanged方法
   * @param parent
   * @param child
   * @param dependency
   * @return
   */
  @Override
  public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
    boolean isTrue = dependency instanceof SnackbarLayout;
    Log.e("yin","执行layoutDependON:"+isTrue);
    return isTrue;
  }

  /**
   * child的行为就是在该方法中实现的
   *
   * 在Snackbar弹出的过程中，Snackbar的垂直位移是正数(在Snackbar的下方)且逐渐变小直至0,所以
   * childTransY是负数且绝对值逐渐变大，所以child向上垂直移动。
   * 在Snackbar消失的过程中，Snackbar的垂直位移是正数且从0直至最大，所以childTransY最终是0，所以
   * child的垂直位移是0，child回到原位。
   * @param parent
   * @param child
   * @param dependency
   * @return
   */
  @Override
  public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
    Log.e("yin","transY:"+dependency.getTranslationY()+",height:"+dependency.getHeight());
    float childTransY = Math.min(0, dependency.getTranslationY() - dependency.getHeight());
    Log.e("yin","结果："+childTransY);
    child.setTranslationY(childTransY);
    return true;
  }
  /**
   * 对于部分Android版本如果不使用该方法的效果是：当child被Snackbar顶起(onDependentViewChanged实现的效果)后，
   * Snackbar消失后child没有回到原位，通过重写该方法让child回到原位。
   *
   * 高版本的support.design包里有该方法，低版本的没有，不清楚哪些版本有哪些没有，用的时候注意一下
   * 一般低版本没有该方法时是在onDependentViewChanged处理这种情况，毕竟removed也是一种changed.
   * 但是高版本的包中的onDependentViewChanged和onDependentViewRemoved方法实现的child的移动没有动画效果可能比较生硬，
   * 应该可以自己通过动画实现，而低版本的这两个方法有动画效果，比较顺滑。
   * 低版本的参考：https://lab.getbase.com/introduction-to-coordinator-layout-on-android/
   * 其对应的github参考：https://github.com/ggajews/coordinatorlayoutwithfabdemo
   * @param parent
   * @param child
   * @param dependency
   */
  @Override
  public void onDependentViewRemoved(CoordinatorLayout parent, TextView child, View dependency) {
//    child.setTranslationY(0);
    super.onDependentViewRemoved(parent, child, dependency);
  }
}