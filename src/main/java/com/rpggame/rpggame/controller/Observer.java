package com.rpggame.rpggame.controller;

/**
 * The interface for observers.
 *
 * @param <T>
 *     The type of event that gets received.
 */
public abstract class Observer<T> {

    public abstract void onNotify(T o);

    public void onSubscribe(Subject<T> subject) {}

    public void onUnsubscribe(Subject<T> subject) {}
}
