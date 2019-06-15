package com.movie.mling.movieapp.mode.biz;

import rx.Subscriber;

/**
 * Created by zhougang on 2016/11/24.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {
    public BaseSubscriber() {

    }

    @Override
    public void onStart() {

    }

    /**
     * 完成
     */
    @Override
    public void onCompleted() {
    }

    /**
     * 对错误进行统一处理
     * @param e
     */
    @Override
    public void onError(Throwable e) {

    }


    @Override
    public abstract void onNext(T t);
}
