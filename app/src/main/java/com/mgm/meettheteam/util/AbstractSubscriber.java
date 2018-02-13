package com.mgm.meettheteam.util;

import rx.Subscriber;

/**
 * Utility subscriber so concrete implementations only have to define the methods they are concerned with.
 *
 * Created by michaelmaitlen on 2/12/18.
 */
abstract public class AbstractSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
