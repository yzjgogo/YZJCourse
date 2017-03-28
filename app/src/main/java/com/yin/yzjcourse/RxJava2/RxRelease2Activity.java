package com.yin.yzjcourse.RxJava2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by think on 2017/3/28.
 */

public class RxRelease2Activity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rx_release_two);
        findViewById(R.id.bt_no_back_pressure).setOnClickListener(this);
        findViewById(R.id.bt_back_pressure).setOnClickListener(this);
        findViewById(R.id.bt_back_pressure_create).setOnClickListener(this);
        findViewById(R.id.bt_maybe).setOnClickListener(this);
        findViewById(R.id.bt_single).setOnClickListener(this);
        findViewById(R.id.bt_complete).setOnClickListener(this);
        findViewById(R.id.bt_action0).setOnClickListener(this);
        findViewById(R.id.bt_action1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_no_back_pressure:
                normalNoBackPressureObservable();
                break;
            case R.id.bt_back_pressure:
                normalBackPressureFlowable();
                break;
            case R.id.bt_back_pressure_create:
                normalBackPressureCreateFlowable();
                break;
            case R.id.bt_maybe:
                testMaybe();
                break;
            case R.id.bt_single:
                testSingle();
                break;
            case R.id.bt_complete:
                testComplete();
                break;
            case R.id.bt_action0:
                testAction();
                break;
            case R.id.bt_action1:
                testAction1();
                break;
        }
    }

    /**
     * Observerable不支持压背策略：当被观察者快速发送大量数据时，下游不会做其他处理，即使数据大量堆积，
     * 调用链也不会报MissingBackpressureException,消耗内存过大只会OOM
     */
    private void normalNoBackPressureObservable() {
        //被观察者
        Observable mObservable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        });
        //观察者
        Observer observer = new Observer<Integer>(){
            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe:"+d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.e("yin","onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError:"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete");
            }
        };
        mObservable.subscribe(observer);
    }

    /**
     * 支持压背策略的被观察者Flowable
     *
     * 执行结果：
     * 03-28 19:35:01.862 18580-18580/com.yin.yzjcourse E/yin: onSubscribe start
     03-28 19:35:01.875 18580-18580/com.yin.yzjcourse E/yin: onNext:0
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:1
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:2
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:3
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:4
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:5
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:6
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:7
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:8
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onNext:9
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onComplete
     03-28 19:35:01.876 18580-18580/com.yin.yzjcourse E/yin: onSubscribe end
     */
    private void normalBackPressureFlowable() {
        Flowable.range(0,10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription s) {//订阅后就会调用该方法
                        Log.e("yin","onSubscribe start");
                        subscription = s;
                        subscription.request(1);//必须在onSubscribe中request()一次Flowable才会发出数据，onNext,onComplete,onError才会执行
                        Log.e("yin","onSubscribe end");//Flowable发射完所有数据后（或者没有request()），才会执行这最后一行，看注释
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.e("yin","onNext:"+integer);
                        subscription.request(1);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("yin","onError:"+t.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete");
                    }
                });
    }

    /**
     * 03-28 19:50:55.712 18073-18073/com.yin.yzjcourse E/yin: 1onSubscribe start
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: 1onSubscribe end
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: onNext:1
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: onNext:2
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: onNext:3
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: onNext:4
     03-28 19:50:55.713 18073-18073/com.yin.yzjcourse E/yin: onComplete

     */
    private void normalBackPressureCreateFlowable() {
        //创建被观察者
        Flowable flowable = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);
        //创建观察者
        Subscriber subscriber = new Subscriber<Integer>() {
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {//订阅后就会调用该方法
                Log.e("yin","1onSubscribe start");
                subscription = s;
                subscription.request(1);//必须在onSubscribe中request()一次Flowable才会发出数据，onNext,onComplete,onError才会执行
                Log.e("yin","1onSubscribe end");//与前面的不同，执行完上面的request()，会立刻执行这一行，即在onNext之前执行，看注释
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("yin","onNext:"+integer);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                Log.e("yin","onError:"+t.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete");
            }
        };
        flowable.subscribe(subscriber);
    }

    private void testMaybe() {
        Maybe.just(true)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MaybeObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin","onSubscribe:"+d.isDisposed());
                    }

                    @Override
                    public void onSuccess(Boolean value) {
                        Log.e("yin","onSuccess:"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin","onError:"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete");
                    }
                });
        //创建一个Maybe
        Maybe.create(new MaybeOnSubscribe<Integer>() {
            @Override
            public void subscribe(MaybeEmitter<Integer> e) throws Exception {
                e.onSuccess(23);
                e.onComplete();
            }
        }).subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 它总是只发射一个值，或者一个错误通知，而不是发射一系列的值
     */
    private void testSingle() {
        Single.create(new SingleOnSubscribe<Integer>() {
            @Override
            public void subscribe(SingleEmitter<Integer> e) throws Exception {
                    e.onSuccess(1);
                e.onSuccess(2);//无效
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe:"+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                Log.e("yin","onSuccess:"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError:"+e.toString());
            }
        });
    }

    /**
     * 被观察者没有数据的发送，只是在处理完自己的逻辑后通知观察者已经处理完成
     */
    private void testComplete() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                // TODO: 2017/3/28
                    e.onComplete();//你的逻辑处理完成后通知观察者完成
            }
        }).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe:"+d.isDisposed());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete:");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError:"+e.toString());
            }
        });
    }

    /**
     * Rxjava1.x的Action0在2.x中是Action
     */
    private void testAction() {
        Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(CompletableEmitter e) throws Exception {
                // TODO: 2017/3/28
                e.onComplete();//你的逻辑处理完成后通知观察者完成
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Log.e("yin","action的run");
            }
        });
    }
    /**
     * Rxjava1.x的Action1在2.x中是Consumer
     */
    private void testAction1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("yin", "accept:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("yin","执行throwable");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                Log.e("yin","执行run");//e.onComplete();后执行
            }
        });
    }
}
