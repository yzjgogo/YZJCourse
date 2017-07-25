package com.yin.yzjcourse.RxJava2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.BaseActivity;
import com.yin.yzjcourse.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by think on 2017/4/11.
 */

public class IntervalActivity extends BaseActivity implements View.OnClickListener{

    private final CompositeDisposable disposables = new CompositeDisposable();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        findViewById(R.id.btn).setOnClickListener(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clearing it : do not emit after destroy
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn){
            doSomeWork();
        }
    }

    /*
     * simple example using interval to run task at an interval of 2 sec
     * which start immediately
     */
    private void doSomeWork() {
        disposables.add(getObservable()
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getObserver()));
    }

    /**
     * 每隔2秒发送一个事件，5秒后开始执行
     * @return
     */
    private Observable<? extends Long> getObservable() {
        return Observable.interval(5, 2, TimeUnit.SECONDS);
    }

    private DisposableObserver<Long> getObserver() {
        return new DisposableObserver<Long>() {

            @Override
            public void onNext(Long value) {
                Log.e("yin","onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError:"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete:");
            }
        };
    }
}
