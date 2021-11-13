package com.rpggame.rpggame.controller;

public abstract class ConnectedObserver<T> extends Observer<T> {
    private Subject subject;

    public void unsubscribe() {
        this.subject.unsubscribe(this);
    }

    @Override
    public void onSubscribe(Subject<T> subject) {
        this.subject = subject;
    }

    @Override
    public void onUnsubscribe(Subject<T> subject) {
        this.subject = null;
    }
}
