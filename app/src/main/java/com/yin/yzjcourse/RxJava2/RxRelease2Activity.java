package com.yin.yzjcourse.RxJava2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.View;

import com.yin.yzjcourse.R;
import com.yin.yzjcourse.bean.ApiUser;
import com.yin.yzjcourse.bean.User;
import com.yin.yzjcourse.utils.Utils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;


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
        findViewById(R.id.bt_observable_map).setOnClickListener(this);
        findViewById(R.id.bt_observable_zip).setOnClickListener(this);
        findViewById(R.id.bt_observable_defer).setOnClickListener(this);
        findViewById(R.id.bt_observable_disposable).setOnClickListener(this);
        findViewById(R.id.bt_observable_take).setOnClickListener(this);
        findViewById(R.id.bt_observable_timer).setOnClickListener(this);
        findViewById(R.id.bt_observable_interval).setOnClickListener(this);
        findViewById(R.id.bt_flowable_reduce).setOnClickListener(this);
        findViewById(R.id.bt_flowable_reduce2).setOnClickListener(this);
        findViewById(R.id.bt_observable_buffer).setOnClickListener(this);
        findViewById(R.id.bt_observable_filter).setOnClickListener(this);
        findViewById(R.id.bt_observable_skip).setOnClickListener(this);
        findViewById(R.id.bt_observable_scan).setOnClickListener(this);
        findViewById(R.id.bt_replay).setOnClickListener(this);
        findViewById(R.id.bt_concat).setOnClickListener(this);
        findViewById(R.id.bt_merge).setOnClickListener(this);
        findViewById(R.id.bt_distinct).setOnClickListener(this);
        findViewById(R.id.bt_last).setOnClickListener(this);
        findViewById(R.id.bt_replaysubject).setOnClickListener(this);
        findViewById(R.id.bt_publishsubject).setOnClickListener(this);
        findViewById(R.id.bt_behaviorsubject).setOnClickListener(this);
        findViewById(R.id.bt_asyncsubject).setOnClickListener(this);
        findViewById(R.id.bt_throttleFirst).setOnClickListener(this);
        findViewById(R.id.bt_throttleLast).setOnClickListener(this);
        findViewById(R.id.bt_debounce).setOnClickListener(this);
        findViewById(R.id.bt_window).setOnClickListener(this);
        findViewById(R.id.bt_delay).setOnClickListener(this);
        findViewById(R.id.bt_take_while).setOnClickListener(this);
        findViewById(R.id.bt_test_null).setOnClickListener(this);
        findViewById(R.id.bt_groupby).setOnClickListener(this);
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
            case R.id.bt_observable_map:
                testObservableMap();
                break;
            case R.id.bt_observable_zip:
                testObservableZip();
                break;
            case R.id.bt_observable_defer:
                testObservableDefer();
                break;
            case R.id.bt_observable_disposable:
                Intent intent = new Intent(this,DisposableExampleActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_observable_take:
                testTake();
                break;
            case R.id.bt_observable_timer:
                testTimer();
                break;
            case R.id.bt_observable_interval:
                Intent intent1 = new Intent(this,IntervalActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_flowable_reduce:
                testReduce();
                break;
            case R.id.bt_flowable_reduce2:
                testReduce2();
                break;
            case R.id.bt_observable_buffer:
                testBuffer();
                break;
            case R.id.bt_observable_filter:
                testFilter();
                break;
            case R.id.bt_observable_skip:
                testSkip();
                break;
            case R.id.bt_observable_scan:
                testScan();
                break;
            case R.id.bt_replay:
                testReplay();
                break;
            case R.id.bt_concat:
                testConcat();
                break;
            case R.id.bt_merge:
                testMerge();
                break;
            case R.id.bt_distinct:
                testDistinct();
                break;
            case R.id.bt_last:
                testLast();
                break;
            case R.id.bt_replaysubject:
                testReplaySubject();
                break;
            case R.id.bt_publishsubject:
                testPublishSubject();
                break;
            case R.id.bt_behaviorsubject:
                testBehaviorSubject();
                break;
            case R.id.bt_asyncsubject:
                testAsyncSubject();
                break;
            case R.id.bt_throttleFirst:
                testThrottleFirst();
                break;
            case R.id.bt_throttleLast:
                testThrottleLast();
                break;
            case R.id.bt_debounce:
                testDebounce();
                break;
            case R.id.bt_window:
                testWindow();
                break;
            case R.id.bt_delay:
                testDelay();
                break;
            case R.id.bt_take_while:
                testTakeWhile();
                break;
            case R.id.bt_test_null:
                testNull();
                break;
            case R.id.bt_groupby:
                testGroupBy();
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

    private void testObservableMap() {
        Observable.create(new ObservableOnSubscribe<List<ApiUser>>() {
            @Override
            public void subscribe(ObservableEmitter<List<ApiUser>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getApiUserList());
                    e.onComplete();
                }
            }
        })
           .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<ApiUser>, List<User>>() {//第一个参数类型，转化为第二个参数类型

                    @Override
                    public List<User> apply(List<ApiUser> apiUsers) throws Exception {
                        return Utils.convertApiUserListToUserList(apiUsers);
                    }
                })
                .subscribe(new Observer<List<User>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", " onSubscribe : " + d.isDisposed());
                    }

                    @Override
                    public void onNext(List<User> userList) {
                        Log.e("yin", " onNext : " + userList.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", " onError : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", " onComplete");
                    }
                });
    }

    /**
     * 获取两个用户列表，一个列表喜欢板球，另一个喜欢足球，然后找到板球和足球都喜欢的用户列表
     */
    private void testObservableZip() {
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),//这两个参数对应下面的后面两个参数
                new BiFunction<List<User>, List<User>, List<User>>() {//后面两个参数压缩之后的值由第一个参数接收，具体的压缩逻辑在apply方法中自定义
                    @Override
                    public List<User> apply(List<User> cricketFans, List<User> footballFans) throws Exception {
                        Log.e("yin","板球粉丝："+cricketFans.toString()+"\n足球粉丝："+footballFans.toString());
                        return Utils.filterUserWhoLovesBoth(cricketFans, footballFans);
                    }
                })
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver());
    }

    private Observable<List<User>> getCricketFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesCricket());
                    e.onComplete();
                }
            }
        });
    }

    private Observable<List<User>> getFootballFansObservable() {
        return Observable.create(new ObservableOnSubscribe<List<User>>() {
            @Override
            public void subscribe(ObservableEmitter<List<User>> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(Utils.getUserListWhoLovesFootball());
                    e.onComplete();
                }
            }
        });
    }

    private Observer<List<User>> getObserver() {
        return new Observer<List<User>>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin", " onSubscribe : " + d.isDisposed());
            }

            @Override
            public void onNext(List<User> userList) {
                Log.e("yin", " onNext : " + userList.size());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin", " onError : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.e("yin", " onComplete");
            }
        };
    }

    /**
     * defer：只有在被订阅后才会产生数据，才会调用call()方法？
     */
    private void testObservableDefer() {
        final String str = "a";
        Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(str);
            }
        });

//        str = "b";
    }

    private void testTake() {
        Observable.just(1,2,3,4,5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .take(3)//取前三个事件
                .takeLast(2)//取刚才取得前三个的后两个
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin","onSubscribe"+d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin","onNext:"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin","onError:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete:");
                    }
                });
    }

    /**
     * 5秒后发送事件
     */
    private void testTimer() {
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin","onSubscribe"+d.isDisposed());//订阅后立刻执行，不用等5s
                    }

                    @Override
                    public void onNext(Long value) {
                        Log.e("yin","onNext:"+value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin","onError:"+e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete:");
                    }
                });
    }

    /**
     * 04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: onSubscribefalse
     04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: 50,1
     04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: 51,2
     04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: 53,3
     04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: 56,4
     04-11 19:27:55.106 29456-29456/com.yin.yzjcourse E/yin: onSuccess60
     */
    private void testReduce() {
        Flowable<Integer> observable = Flowable.just(1, 2, 3, 4);
        observable.reduce(50, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer t1, Integer t2) {
                Log.e("yin",t1+","+t2);
                return t1 + t2;
            }
        }).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe"+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                Log.e("yin","onSuccess"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError"+e.toString());
            }
        });
    }

    /**
     * 04-12 10:29:30.861 23677-23677/com.yin.yzjcourse E/yin: onSubscribefalse
     04-12 10:29:30.863 23677-23677/com.yin.yzjcourse E/yin: 1,2
     04-12 10:29:30.863 23677-23677/com.yin.yzjcourse E/yin: 3,3
     04-12 10:29:30.863 23677-23677/com.yin.yzjcourse E/yin: 6,4
     04-12 10:29:30.863 23677-23677/com.yin.yzjcourse E/yin: onSuccess10
     */
    private void testReduce2() {
        Observable.just(1,2,3,4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.e("yin",integer + "," + integer2);
                        return integer+integer2;//返回的结果参与下一次的计算，就是下一次apply的第一个参数；
                    }
                }).subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe"+d.isDisposed());
            }

            @Override
            public void onSuccess(Integer value) {
                Log.e("yin","onSuccess"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete");
            }
        });
    }

    /**
     buffer(3,1)的结果：
     onSubscribefalse
     onNext[one, two, three]
     onNext[two, three, four]
     onNext[three, four, five]
     onNext[four, five]
     onNext[five]
     onComplete


     buffer(3,2)的结果：
     onSubscribefalse
     onNext[one, two, three]
     onNext[three, four, five]
     onNext[five]
     onComplete

     buffer(int count, int skip)的第一个参数代表取事件的前count个组成list，第二个参数代表下次取时往前跳skip个index，直到只能取到一个元素的list为止
     */
    private void testBuffer() {
        Observable<List<String>> observable = Observable.just("one","two","three","four","five").buffer(3,2);
        observable.subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","onSubscribe"+d.isDisposed());
            }

            @Override
            public void onNext(List<String> value) {
                Log.e("yin","onNext"+value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","onError"+e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin","onComplete");
            }
        });
    }

    /**
     * onSubscribefalse
     onNext2
     onNext4
     onNext6
     onComplete
     */
    private void testFilter() {
        Observable.just(1,2,3,4,5,6,7)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 2 == 0;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin","onSubscribe"+d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin","onNext"+value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin","onError"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete");
                    }
                });
    }

    /**
     * yin: onSubscribefalse
     yin: onNext3
     yin: onNext4
     yin: onNext5
     yin: onNext6
     yin: onComplete
     */
    private void testSkip() {
        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .skip(2)//跳过前两个事件从第三个事件开始发送
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin","onSubscribe"+d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin","onNext"+value.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin","onError"+e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin","onComplete");
                    }
                });
    }

    /**
     yin: onSubscribefalse
     yin: onNext1
     yin: 1,2
     yin: onNext3
     yin: 3,3
     yin: onNext6
     yin: 6,4
     yin: onNext10
     yin: 10,5
     yin: onNext15
     yin: 15,6
     yin: onNext21
     yin: onComplete

     由上可知，先将第一个事件（1）发出执行一次onNext,以后每次都是将上一次事件的结果拿过来与下一个事件合并然后将合并的结果发出；
     与reduce的对比：reduce只发送一次最终的事件结果，而scan每次事件的结果都会发出
     */
    private void testScan() {
        Observable.just(1,2,3,4,5,6)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.e("yin",integer+","+integer2);
                        return integer + integer2;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin", "onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.e("yin", "onNext:" + value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin", "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin", "onComplete");
            }
        });
    }

    /**
     yin: first_onSubscribe:false
     yin: first_onNext:1
     yin: first_onNext:2
     yin: first_onNext:3
     yin: first_onNext:4
     yin: first_onComplete
     yin: second_onSubscribe:false
     yin: second_onNext:2
     yin: second_onNext:3
     yin: second_onNext:4
     yin: second_onComplete

     replay:确保所有的观察者都能收到相同的被发射的数据，即使观察者在被观察者发送数据之后订阅也能收到数据

     RxJava1.x有类似的：connect()

     */
    private void testReplay() {

        PublishSubject<Integer> source = PublishSubject.create();
        ConnectableObservable<Integer> connectableObservable = source.replay(3); // 保留后三个事件用于其它观察者接收，如果大于总事件数则保留总事件数个。
        connectableObservable.connect(); // connecting the connectableObservable

        connectableObservable.subscribe(getFirstObserver());
        //connectableObservable.subscribe(getSecondObserver());
        // 如果在被观察者发送事件之前有多个观察者订阅，则这些观察者同时收到被观察者的事件，
        // 而不是第一个观察者先收到所有事件，第二个观察则再收，下面是运行结果
        /**
         * yin: first_onSubscribe:false
         yin: second_onSubscribe:false
         yin: first_onNext:1
         yin: second_onNext:1
         yin: first_onNext:2
         yin: second_onNext:2
         yin: first_onNext:3
         yin: second_onNext:3
         yin: first_onNext:4
         yin: second_onNext:4
         yin: first_onComplete
         yin: second_onComplete
         */
        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        /*
         * it will emit 2, 3, 4 as (count = 3), retains the 3 values for replay
         */
        connectableObservable.subscribe(getSecondObserver());
    }


    private Observer<Integer> getFirstObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin", "first_onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.e("yin", "first_onNext:" + value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin", "first_onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin", "first_onComplete");
            }
        };
    }

    private Observer<Integer> getSecondObserver() {
        return new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin", "second_onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.e("yin", "second_onNext:" + value.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin", "second_onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin", "second_onComplete");
            }
        };
    }

    /**
     yin: onSubscribe:false
     yin: onNext:A1
     yin: onNext:A2
     yin: onNext:A3
     yin: onNext:A4
     yin: onNext:B1
     yin: onNext:B2
     yin: onNext:B3
     yin: onComplete

     concat:合并多个被观察者，且有先后顺序，按照被观察者的顺序先后发送事件

     RxJava1.x中有类似：Observable.merge(obs1,obs2)
     */
    private void testConcat() {
        final String[] aStrings = {"A1", "A2", "A3", "A4"};
        final String[] bStrings = {"B1", "B2", "B3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.concat(aObservable, bObservable)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     yin: onSubscribe:false
     yin: onNext:C1
     yin: onNext:C2
     yin: onNext:C3
     yin: onNext:C4
     yin: onNext:D1
     yin: onNext:D2
     yin: onNext:D3
     yin: onComplete

     顺序可能不是上面的顺序，可能是任意顺序

     merge:合并多个被观察者但不保证事件的发射顺序
     */
    private void testMerge() {
        final String[] aStrings = {"C1", "C2", "C3", "C4"};
        final String[] bStrings = {"D1", "D2", "D3"};

        final Observable<String> aObservable = Observable.fromArray(aStrings);
        final Observable<String> bObservable = Observable.fromArray(bStrings);

        Observable.merge(aObservable, bObservable)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     * yin: onSubscribe:false
     yin: onNext:1
     yin: onNext:2
     yin: onNext:3
     yin: onNext:4
     yin: onNext:6
     yin: onComplete

     distinct:去重操作
     */
    private void testDistinct() {
        Observable.just(1, 2, 1, 1, 2, 3, 4, 6, 4)
                .distinct()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     yin: onSubscribe:false
     yin: onSuccess:A6

     last:只发射被观察者的最后一个事件，如果被观察者为空的，则发送last指定的默认事件
     */
    private void testLast() {
        Observable.just("A1", "A2", "A3", "A4", "A5", "A6")
                .last("B")//last指定的默认事件，如果被观察者为空就发送这个默认事件，否则就发送被观察者的最后一个事件
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onSuccess(String value) {
                        Log.e("yin", "onSuccess:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }
                });
    }

    /**
     yin: first_onSubscribe:false
     yin: first_onNext:1
     yin: first_onNext:2
     yin: first_onNext:3
     yin: first_onNext:4
     yin: first_onComplete
     yin: second_onSubscribe:false
     yin: second_onNext:1
     yin: second_onNext:2
     yin: second_onNext:3
     yin: second_onNext:4
     yin: second_onComplete

     ReplaySubject:无论什么时候多少个观察者订阅该ReplaySubject,这些观察者都能收到被观察者发送的所有的事件，对比replay
     */
    private void testReplaySubject() {

        ReplaySubject<Integer> source = ReplaySubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);
        source.onNext(4);
        source.onComplete();

        source.subscribe(getSecondObserver());
    }

    /**
     yin: first_onSubscribe:false
     yin: first_onNext:1
     yin: first_onNext:2
     yin: first_onNext:3
     yin: second_onSubscribe:false
     yin: first_onNext:4
     yin: second_onNext:4
     yin: first_onComplete
     yin: second_onComplete

     被观察者只向各个观察者发送各自订阅之后产生的事件，即各个观察者只能接受到其订阅之后产生的事件
     */
    private void testPublishSubject() {

        PublishSubject<Integer> source = PublishSubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 4 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
    }

    /**
     *
     yin: first_onSubscribe:false
     yin: first_onNext:1
     yin: first_onNext:2
     yin: first_onNext:3
     yin: second_onSubscribe:false
     yin: second_onNext:3
     yin: first_onNext:4
     yin: second_onNext:4
     yin: first_onComplete
     yin: second_onComplete
     */
    private void testBehaviorSubject() {

        BehaviorSubject<Integer> source = BehaviorSubject.create();

        source.subscribe(getFirstObserver()); // it will get 1, 2, 3, 4 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 3(last emitted), 4 and onComplete for second observer also.
         * 发射3,4和onComplete给第二个观察者
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);
        source.onComplete();
    }

    /**
     04-12 15:05:00.133 23899-23899/com.yin.yzjcourse E/yin: first_onSubscribe:false
     04-12 15:05:00.134 23899-23899/com.yin.yzjcourse E/yin: second_onSubscribe:false
     04-12 15:05:00.135 23899-23899/com.yin.yzjcourse E/yin: first_onNext:5
     04-12 15:05:00.135 23899-23899/com.yin.yzjcourse E/yin: first_onComplete
     04-12 15:05:00.135 23899-23899/com.yin.yzjcourse E/yin: second_onNext:5
     04-12 15:05:00.135 23899-23899/com.yin.yzjcourse E/yin: second_onComplete
        被观察者只发送最后一个事件给所有的观察者
     */
    private void testAsyncSubject() {

        AsyncSubject<Integer> source = AsyncSubject.create();

        source.subscribe(getFirstObserver()); // it will emit only 5 and onComplete

        source.onNext(1);
        source.onNext(2);
        source.onNext(3);

        /*
         * it will emit 5 and onComplete for second observer also.
         */
        source.subscribe(getSecondObserver());

        source.onNext(4);//
        source.onNext(5);//如果这一行和上一行注释掉，则只发送3
        source.onComplete();
    }

    /**
     yin: onSubscribe:false
     yin: onNext:1
     yin: onNext:3
     yin: onNext:7
     yin: onComplete


     throttleFirst：接收一次事件后，指定时间内不接收新的事件（丢弃掉）；即接收指定时间区间内的第一个事件
     */
    private void testThrottleFirst() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                Thread.sleep(0);
                emitter.onNext(1); //发送
                emitter.onNext(2); // 睡
                Thread.sleep(505);  //睡醒
                emitter.onNext(3); // 发送
                Thread.sleep(99);   //睡
                emitter.onNext(4); // 睡
                Thread.sleep(100); //睡
                emitter.onNext(5); //睡
                emitter.onNext(6); // 睡
                Thread.sleep(305);//醒
                emitter.onNext(7); //发送
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).throttleFirst(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     yin: onSubscribe:false
     yin: onNext:2
     yin: onNext:6
     yin: onNext:7
     yin: onComplete

     throttleLast:接收指定时间区间内的第一个事件
     */
    private void testThrottleLast() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                Thread.sleep(0);
                emitter.onNext(1);
                emitter.onNext(2);
                Thread.sleep(505);
                //第一个500ms内只有上面两个事件产生，因此只发送最后一个，即2
                emitter.onNext(3);
                Thread.sleep(99);   //
                emitter.onNext(4); //
                Thread.sleep(100); //
                emitter.onNext(5); //
                emitter.onNext(6); //
                Thread.sleep(305);//
                //第二个500ms内产生了3,4,5,6事件，因此只发送最后一个，即6
                emitter.onNext(7); //
                Thread.sleep(510);
                //第三个500ms内产生了6事件，因此只发送最后一个，即7
                emitter.onComplete();
            }
        }).throttleLast(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     *
     yin: onSubscribe:false
     yin: onNext:2
     yin: onNext:4
     yin: onNext:5
     yin: onComplete

     debounce操作符对源Observable每产生一个结果后，如果在规定的间隔时间内没有别的结果产生，则把这个结果提交给订阅者处理，否则忽略该结果。

     值得注意的是，如果源Observable产生的最后一个结果后在规定的时间间隔内调用了onCompleted，那么通过debounce操作符也会把这个结果提交给订阅者
     */
    private void testDebounce() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1);
                Thread.sleep(400);// 500ms内有新的事件2，不发送1
                emitter.onNext(2);
                Thread.sleep(505);//事件2产生后的500ms(505)内没有新的事件，因此2发出

                emitter.onNext(3);
                Thread.sleep(100);//事件3产生后的500ms(100)内有新的事件4，因此3发出
                emitter.onNext(4); //事件4产生后的500ms(605)内没有新的事件，因此4发出
                Thread.sleep(605);
                emitter.onNext(5);
                Thread.sleep(510);//事件5产生后的500ms(510)内没有新的事件，因此5发出
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     yin: 第一执行accept
     yin: accept:0
     yin: accept:1
     yin: 第一执行accept
     yin: accept:2
     yin: accept:3
     yin: accept:4
     yin: 第一执行accept
     yin: accept:5
     yin: accept:6
     yin: accept:7
     yin: 第一执行accept
     yin: accept:8
     yin: accept:9
     yin: accept:10
     yin: 第一执行accept
     yin: accept:11

     window操作符会在时间间隔内缓存结果，类似于buffer缓存一个list集合，区别在于window将这个结果集合封装成了observable
     */
    private void testWindow() {
        Observable.interval(1, TimeUnit.SECONDS).take(12)
                .window(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getConsumer());
    }

    public Consumer<Observable<Long>> getConsumer() {
        return new Consumer<Observable<Long>>() {
            @Override
            public void accept(Observable<Long> observable) throws Exception {
                Log.e("yin","第一执行accept");
                observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long value) {
                                Log.e("yin","accept:"+value);
                            }
                        });
            }
        };
    }

    /**
     * delay：延迟指定时间后发送事件
     */
    private void testDelay() {
        Observable.just("Amit").delay(10, TimeUnit.SECONDS)
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("yin", "onSubscribe:" + d.isDisposed());
                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("yin", "onNext:" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("yin", "onError:" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("yin", "onComplete");
                    }
                });
    }

    /**
     * TakeWhile则是根据一个函数来判断是否发射数据，当函数返回值为true的时候正常发射数据；当函数返回false的时候丢弃所有后面的数据。
     */
    private void testTakeWhile() {
        Observable.just(1,2,3,4,5,6,7)
                .takeWhile(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer!=4;//1,2,3会被发射出去，从4开始(4==4),4,5,6,7都不会被发射出去
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin", "onSubscribe:" + d.isDisposed());
            }

            @Override
            public void onNext(Integer value) {
                Log.e("yin", "onNext:" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin", "onError:" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.e("yin", "onComplete");
            }
        });
    }
    private void testNull(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("1");
                e.onNext(null);
                e.onComplete();
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("yin","执行onSubscribe:"+d.isDisposed());
            }

            @Override
            public void onNext(String value) {
                Log.e("yin","执行onNext:"+value);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("yin","执行onError:"+e.toString());//空指针
            }

            @Override
            public void onComplete() {
                Log.e("yin","执行onComplete");
            }
        });
    }

    private void testGroupBy() {
        Observable.range(1,10)
                .groupBy(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer figure) throws Exception {
                        return (figure%2 == 0)?"偶数组":"奇数组";
                    }
                })
                .subscribe(new Consumer<GroupedObservable<String, Integer>>() {
                    @Override
                    public void accept(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
                            if(stringIntegerGroupedObservable.getKey().equalsIgnoreCase("偶数组")){
                                stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer integer) throws Exception {
                                        Log.e("yin","偶数："+stringIntegerGroupedObservable.getKey()+","+integer);
                                    }
                                });
                            }else if(stringIntegerGroupedObservable.getKey().equalsIgnoreCase("奇数组")){
                                stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer integer) throws Exception {
                                        Log.e("yin","奇数"+stringIntegerGroupedObservable.getKey()+","+integer);
                                    }
                                });
                            }
                    }
                });
    }
}
