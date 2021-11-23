package com.rpggame.rpggame.entity;

import java.lang.reflect.ParameterizedType;

public abstract class EntityObserver<T> {

    protected Entity entity;
    protected Entity subject;
    private Class<T> type;

    public EntityObserver() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Class<T> getType() {
        return this.type;
    }

    public void setConnection(Entity observer, Entity subject) {
        this.entity = observer;
        this.subject = subject;
    }

    public abstract void onNotify(T event);

    public void unsubscribe() {
        subject.unsubscribe(this);
    }

}
