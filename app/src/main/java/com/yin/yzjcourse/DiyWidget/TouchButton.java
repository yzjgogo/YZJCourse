package com.yin.yzjcourse.DiyWidget;

import android.content.Context;
import android.databinding.repacked.org.antlr.v4.codegen.model.ElementFrequenciesVisitor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yin.yzjcourse.utils.DLog;

/**
 * Created by think on 2017/12/13.
 */

public class TouchButton extends Button implements View.OnTouchListener,View.OnClickListener{
    public TouchButton(Context context) {
        super(context);
        DLog.eLog("TouchButton1");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        DLog.eLog("TouchButton2");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DLog.eLog("TouchButton3");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        DLog.eLog("TouchButton4");
        setOnClickListener(this);
        setOnTouchListener(this);
    }

    /**
     测试子View的dispatchTouchEvent方法返回值的意义：
     第一步：当MotionEvent为ACTION_DOWN时dispatchTouchEvent直接返回true,ACTION_MOVE和ACTION_UP时return什么无所谓：
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchButton的dispatchTouchEvent-- action=0,result:true

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onInterceptTouchEvent-- action=2,result=false
     TouchButton的dispatchTouchEvent-- action=2,result:false

     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onInterceptTouchEvent-- action=1,result=false
     TouchButton的dispatchTouchEvent-- action=1,result:false
     ACTION_DOWN时返回true说明该View消费掉了ACTION_DOWN事件，且该View可以处理当前touch事件序列(后续的ACTION_MOVE,ACTION_UP)，无需还给父ViewGroup来处理当前Touch事件序列


     第二步：当MotionEvent为ACTION_DOWN时dispatchTouchEvent直接返回false,ACTION_MOVE和ACTION_UP时return什么无所谓：
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchLinearLayout的onInterceptTouchEvent-- action=0,result=false
     TouchButton的dispatchTouchEvent-- action=0,result:false
     TouchLinearLayout的onTouch-- action=0
     TouchLinearLayout的onTouchEvent-- action=0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的onTouch-- action=2
     TouchLinearLayout的onTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的onTouch-- action=1
     TouchLinearLayout的onTouchEvent-- action=1
     TouchLinearLayout的onClick--

     ACTION_DOWN时返回false说明该View没有消费掉ACTION_DOWN事件，要还给(这个ACTION也是父亲分发过来的，现在还给父亲)父
     ViewGroup继续处理该ACTIION_DOWN（走父ViewGroup的这一套4个方法），因为ACTION_DOWN由ViewGroup处理了，所以整个事件序列后续
     的ACTION_MOVE和ACTION_UP也会由父ViewGroup来处理。
     注意，只有ACTION_DOWN时返回false才会将事件交还给父ViewGroup处理，ACTION_MOVE和ACTION_UP无论返回什么都无所谓，不会交还给父类处理，还是自己可以处理
     */
    /**
     * 测试子View的dispatchTouchEvent方法的返回值与父ViewGroup的返回值的关系：
     第一步：返回false
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchButton的dispatchTouchEvent-- action=0,result:false
     TouchLinearLayout的dispatchTouchEvent-- action=0,result:true
     rootView--true:0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchLinearLayout的dispatchTouchEvent-- action=2,result:true
     rootView--true:2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchLinearLayout的dispatchTouchEvent-- action=1,result:true
     rootView--true:1

     第二步：返回true
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchButton的dispatchTouchEvent-- action=0,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=0,result:true
     rootView--true:0

     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchButton的dispatchTouchEvent-- action=2,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=2,result:true
     rootView--true:2

     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchButton的dispatchTouchEvent-- action=1,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=1,result:true
     rootView--true:1

     第三步：DOWN返回false，MOVE、UP返回true效果和第一步一样，因为DOWN返回false，根本接收不到MOVE和UP

     第四步：DOWN和UP返回true，MOVE返回false
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchButton的dispatchTouchEvent-- action=0,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=0,result:true
     rootView--true:0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchButton的dispatchTouchEvent-- action=2,result:false
     TouchLinearLayout的dispatchTouchEvent-- action=2,result:false
     rootView--false:2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchButton的dispatchTouchEvent-- action=1,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=1,result:true
     rootView--true:1

     第五步：DOWN返回true，MOVE、UP返回false
     TouchLinearLayout的dispatchTouchEvent-- action=0
     TouchButton的dispatchTouchEvent-- action=0,result:true
     TouchLinearLayout的dispatchTouchEvent-- action=0,result:true
     rootView--true:0
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchButton的dispatchTouchEvent-- action=2,result:false
     TouchLinearLayout的dispatchTouchEvent-- action=2,result:false
     rootView--false:2
     TouchLinearLayout的dispatchTouchEvent-- action=2
     TouchButton的dispatchTouchEvent-- action=2,result:false
     TouchLinearLayout的dispatchTouchEvent-- action=2,result:false
     rootView--false:2
     TouchLinearLayout的dispatchTouchEvent-- action=1
     TouchButton的dispatchTouchEvent-- action=1,result:false
     TouchLinearLayout的dispatchTouchEvent-- action=1,result:false
     rootView--false:1

     通过以上步骤得出结论：如果ACTION_DOWN时子View的dispatchTouchEvent返回false，则父ViewGroup返回true；且子View不会再处理MOVE和UP事件；且父ViewGroup
     的MOVE和UP事件的dispatchTouchEvent方法也返回true；
     其余情况子View的dispatchTouchEvent方法的返回值与父ViewGroup的返回值相同；
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        boolean result = false;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                result = true;
                break;
            case MotionEvent.ACTION_MOVE:
                result = false;
                break;
            case MotionEvent.ACTION_UP:
                result = false;
                break;
            default:
                break;
        }
        DLog.eLog("TouchButton的dispatchTouchEvent-- action=" + event.getAction()+",result:"+result);
        return result;
//        return super.dispatchTouchEvent(event);

    }

    /**
        第三步：onTouch返回true，dispatchTouchEvent不重写（不在执行执行onTouchEvent,dispatchTouchEvent也返回true）
        参考第一步
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        DLog.eLog("TouchButton的onTouch-- action="+event.getAction());
        return false;
    }

    /**
      因为一旦执行onTouchEvent则onTouchEvent就是dispatchTouchEvent的返回值
     所以参考第一步和第二步
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
//        DLog.eLog("TouchButton的onTouchEvent-- action="+event.getAction()+",result:");
        return result;
    }
    /**
     * onClicks是在ACTION_UP后执行，还有一条时从子View拦截过来的EVENT执行到ACTION_UP后不会执行onClick
     * 必须是ACTION_DOWN时手指正好在该View或ViewGroup上时，ACTION_UP后才会执行OnClick
     * @param v
     */
    @Override
    public void onClick(View v) {
//        DLog.eLog("TouchButton的onClick--");
    }
}
