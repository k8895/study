package com.wjk.rx;

/**
 * Created by WJK on 17/02/27.
 */

public class Read {

    /**
     * 如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI, 那么APP肯定就崩溃了,
     * 怎么办呢, 上一节我们说到了Disposable , 说它是个开关, 调用它的dispose()方法时就会切断水管, 使得下游收不到事件,
     * 既然收不到事件, 那么也就不会再去更新UI了. 因此我们可以在Activity中将这个Disposable 保存起来, 当Activity退出时, 切断它即可.
     *
     * 那如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable,
     * 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.
     */

    /**
     * sample操作符,这个操作符每隔指定的时间就从上游中取出一个事件发送给下游.
     */

}
