package com.rpggame.rpggame.entity;

import java.util.HashSet;
import java.util.Set;

public class EntitySubject<T> {

    private final Set<EntityObserver<T>> observers;

    public EntitySubject() {
        this.observers = new HashSet<>();
    }

    public void subscribe(EntityObserver<T> entity) {
        this.observers.add(entity);
    }

    public void unsubscribe(EntityObserver<T> entity) {
        this.observers.remove(entity);
    }

    public void notify(T event) {
        for (EntityObserver<T> observer : this.observers) {
            observer.onNotify(event);
        }
    }

}
