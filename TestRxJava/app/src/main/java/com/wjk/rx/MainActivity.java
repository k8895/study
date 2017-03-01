package com.wjk.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * https://gold.xitu.io/user/573dba2171cfe448aa97b7b0 文章
 */
public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getName() + " -- MY_TAG ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        testBase();
//        testBase2();
//        testBase3();
//        testSchedulers();
//        testMap();
//        testFlatMap();
//        testConcatMap();
//        testZip();
        testFlowable();

    }

    /**
     * 观察者接收到被观察者发送的onComplete或者onError，
     * 观察者就不会继续接收信息，
     * 被观察者会继续发送剩余的事件
     */
    private void testBase() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("send - 11");
                e.onNext(11);
                System.out.println("send - 22");
                e.onNext(22);
                System.out.println("send - onComplete");
                e.onComplete();
                System.out.println("send - 33");
                e.onNext(33);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe success");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("get value = " + value);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("get error");
            }

            @Override
            public void onComplete() {
                System.out.println("get complete");
            }
        });
    }

    /**
     * 调用dispose()并不会导致上游不再继续发送事件, 上游会继续发送剩余的事件.
     */
    private Disposable disposable;

    private void testBase2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("send - 11");
                e.onNext(11);
                System.out.println("send - 22");
                e.onNext(22);
                System.out.println("send - 33");
                e.onNext(33);
                System.out.println("send - onComplete");
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("subscribe success");
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("get value = " + value);
                if (value == 22) {
                    disposable.dispose();  //截断
                    System.out.println("dispose it = " + disposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("get error");
            }

            @Override
            public void onComplete() {
                System.out.println("get complete");
            }
        });
    }

    /**
     * subscribe()有多个重载的方法
     * public final Disposable subscribe() {}
     * public final Disposable subscribe(Consumer<? super T> onNext) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete) {}
     * public final Disposable subscribe(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Consumer<? super Disposable> onSubscribe) {}
     * public final void subscribe(Observer<? super T> observer) {}
     * <p>
     * 不带任何参数的subscribe() 表示下游不关心任何事件,你上游尽管发你的数据去吧, 老子可不管你发什么.
     * 带有一个Consumer参数的方法表示下游只关心onNext事件, 其他的事件我假装没看见, 因此我们如果只需要onNext事件可以这么写:
     */
    private void testBase3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "send - 1");
                emitter.onNext(1);
                Log.d(TAG, "send - 2");
                emitter.onNext(2);
                Log.d(TAG, "send - 3");
                emitter.onNext(3);
                Log.d(TAG, "send - complete");
                emitter.onComplete();
                Log.d(TAG, "send - 4");
                emitter.onNext(4);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "get onNext: " + integer);
            }
        });
    }


    /**
     * subscribeOn() 指定的在上游发送事件的线程，接收事件，发送给下游所在线程
     * observeOn() 指定的是下游接收事件的线程.
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     * <p>
     * 在RxJava中, 已经内置了很多线程选项供我们选择, 例如有
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作
     * Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作
     * Schedulers.newThread() 代表一个常规的新线程
     * AndroidSchedulers.mainThread() 代表Android的主线程
     */
    private void testSchedulers() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
            }
        });

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * 通过Map, 可以将上游发来的事件转换为任意的类型, 可以是一个Object, 也可以是一个集合
     */
    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "Integer transformation to String = " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * flatMap并不保证事件的顺序
     */
    private void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(50, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * 和flatMap相似，但是有序
     */
    private void testConcatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * zip合并操作，下游收到的事件数量 是和上游中发送事件最少的那一根水管的事件数量 相同.
     * 不使用.subscribeOn(Schedulers.io()); 的话，上游两个事件在同一个线程中，
     * 会先执行完一个，在执行另一个
     * <p>
     * 比如一个界面需要展示用户的一些信息, 而这些信息分别要从两个服务器接口中获取, 而只有当两个都获取到了之后才能进行展示, 这个时候就可以用Zip了
     */
    private void testZip() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Log.d(TAG, "emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Log.d(TAG, "emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });

    }

    /**
     * Flowable在设计的时候采用了一种新的思路也就是响应式拉取的方式来更好的解决上下游流速不均衡的问题,
     * <p>
     * 下游没有调用request, 上游就认为下游没有处理事件的能力, 而这又是一个同步的订阅, 既然下游处理不了, 那上游不可能一直等待吧,
     * 如果是这样, 万一这两根水管工作在主线程里, 界面不就卡死了吗, 因此只能抛个异常来提醒我们. 那如何解决这种情况呢, 很简单啦,
     * 下游直接调用request(Long.MAX_VALUE)就行了, 或者根据上游发送事件的数量来request就行了, 比如这里request(3)就可以了.
     * <p>
     * Flowable里默认有一个大小为128的水缸(异步时), 当上下游工作在不同的线程中时, 上游就会先把事件发送到这个水缸中,
     * 因此, 下游虽然没有调用request, 但是上游在水缸中保存着这些事件, 只有当下游调用request时, 才从水缸里取出事件发给下游.
     * BackpressureStrategy.ERROR 存储超过128抛异常
     * BackpressureStrategy.BUFFER 大缓存，会造成OOM
     * BackpressureStrategy.DROP 直接把存不下的事件丢弃，把超过128之后的事件丢弃
     * BackpressureStrategy.LATEST 只保留最新的事件
     */
    private void testFlowable() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "current requested: " + emitter.requested()); //获取下游处理能力
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR);

        Subscriber<Integer> downstream = new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
                s.request(Long.MAX_VALUE);  //下游处理能力
//                s.cancel();       //切断水管
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);

            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };

        upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downstream);
    }

}
