package com.rpggame.rpggame.controller;

/**
 * The interface for observers.
 *
 * @param <T>
 *     The type of event that gets received.
 */
public interface Observer<T> {

    void onNotify(T o);

}
