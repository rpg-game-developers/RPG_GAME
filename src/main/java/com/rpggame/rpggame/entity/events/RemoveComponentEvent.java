package com.rpggame.rpggame.entity.events;

import com.rpggame.rpggame.entity.Entity;

/**
 * Event that is sent when an entity removes a component.
 *
 */
public class RemoveComponentEvent {

    private Entity entity;

    public RemoveComponentEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

}
