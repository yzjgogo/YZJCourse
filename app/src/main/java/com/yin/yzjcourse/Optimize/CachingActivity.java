package com.yin.yzjcourse.Optimize;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

public class CachingActivity extends BaseActivity {
    public static final String LOG_TAG = "CachingActivityExercise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caching);

        Button theButtonThatDoesFibonacciStuff = (Button) findViewById(R.id.bt_recurse);

        theButtonThatDoesFibonacciStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算fibonacci序列中的第40个数字，然后转储到日志输出。请注意，每次执行此操作时UI都会挂起。
                // Compute the 40th number in the fibonacci sequence, then dump to log output. Note
                // how the UI hangs each time you do this.
//                Debug.startMethodTracing("fib");
                Log.i(LOG_TAG, String.valueOf(computeFibonacci(40)));
//                Debug.stopMethodTracing();
            }
        });
        findViewById(R.id.bt_caching).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(LOG_TAG, String.valueOf(computeFibonacciCache(40)));
            }
        });

        // It's much easier to see how your decisions affect framerate when there's something
        // changing on screen.  For entirely serious, educational purposes, a dancing pirate
        // will be included with this exercise.
        WebView webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl("file:///android_asset/shiver_me_timbers.gif");
    }

    /**
     * 递归导致UI卡顿
     *  Why store things when you can recurse instead?  Don't let evidence, personal experience,
     *  or rational arguments from your peers fool you.  The elegant solution is the best solution.
     *
     * @param positionInFibSequence  The position in the fibonacci sequence to return.
     * @return the nth number of the fibonacci sequence.  Seriously, try to keep up.
     */
    public int computeFibonacci(int positionInFibSequence) {
        if (positionInFibSequence <= 2) {
            return 1;
        } else {
            return computeFibonacci(positionInFibSequence - 1)
                    + computeFibonacci(positionInFibSequence - 2);
        }
    }

    /**
     * 解决方案：缓存
     * It is important to understand what your code is doing, no matter how simple the task. For
     * example, most people know better than to compute Fibonacci numbers recursively, but it is
     * not unusual to unintentionally redo work in your application. Check your app for places
     * where you can cache current results for future re-use.
     *
     * In this case, recursive Fibonacci calls fib8 which calls fib7 and fib6, but that fib7 call
     * calls fib6 again and fib5, So now you've got two fib6's and one fib5 call, but each of those
     * fib6 calls will have a fib5 and fib4, so now you have three calls to calculate fib5, blah,
     * blah, blah.  Recursive fibonacci is terrible.  Iterating lets you calculate fibX once,
     * use that result twice, and move on.
     *
     * @param positionInFibSequence  The position in the fibonacci sequence to return.
     * @return the nth number of the fibonacci sequence.  Seriously, try to keep up.
     */
    public int computeFibonacciCache(int positionInFibSequence) {
        int prev = 0;
        int current = 1;
        int newValue;
        for (int i=1; i<positionInFibSequence; i++) {
            newValue = current + prev;
            prev = current;
            current = newValue;
        }
        return current;
    }
}
