package com.rpggame.rpggame.entity.events;

import com.rpggame.rpggame.entity.Entity;

/**
 * Event that is sent when an entity adds a component.
 *
 */
public class AddComponentEvent {

    private Entity entity;

    public AddComponentEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
