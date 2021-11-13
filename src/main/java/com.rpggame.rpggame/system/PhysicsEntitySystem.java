package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.PositionComponent;
import com.rpggame.rpggame.component.VelocityComponent;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class PhysicsEntitySystem extends EntitySystem {

    public PhysicsEntitySystem() {
        super(new EntityFamily(PositionComponent.class, VelocityComponent.class));
    }

    public void applyPhysics() {
        for (Entity entity : getEntities()) {
            VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
            PositionComponent position = entity.getComponent(PositionComponent.class);
            position.setX(position.getX() + velocity.getX());
            position.setY(position.getY() + velocity.getY());
        }
    }
}
