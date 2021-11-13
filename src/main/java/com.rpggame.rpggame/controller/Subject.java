package com.rpggame.rpggame.controller;

import java.util.HashSet;
import java.util.Set;

/**
 * The base class for all kinds of events. Subjects transmit events to
 * subscribed observers.
 *
 * @param <T>
 *           The type of event.
 */
public class Subject<T> {
    private Set<Observer<T>> observers;

    public Subject() {
        this.observers = new HashSet<>();
    }

    public void subscribe(Observer<T> observer) {
        observers.add(observer);
        observer.onSubscribe(this);
    }

    public void unsubscribe(Observer<T> observer) {
        observers.remove(observer);
        observer.onUnsubscribe(this);
    }

    public void notify(T event) {
        for (Observer<T> observer : observers) {
            observer.onNotify(event);
        }
    }
}
