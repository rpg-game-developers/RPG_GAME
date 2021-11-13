package com.rpggame.rpggame.entity.events;

import com.rpggame.rpggame.entity.Entity;

/**
 * Event that is sent when an entity adds a component of type T.
 *
 * @param <T>
 *     The type of component to listen for.
 */
public class AddComponentEvent<T> {

    private Entity entity;
    
}
