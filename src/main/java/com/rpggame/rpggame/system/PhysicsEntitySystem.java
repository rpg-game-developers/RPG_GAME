package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.physics.PositionComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class PhysicsEntitySystem extends EntitySystem {

    public PhysicsEntitySystem() {
        super(new EntityFamily(PositionComp.class, VelocityComp.class));
    }

    public void applyPhysics() {
        for (Entity entity : getEntities()) {
            VelocityComp velocity = entity.getComponent(VelocityComp.class);
            PositionComp position = entity.getComponent(PositionComp.class);
            position.setX(position.getX() + velocity.getX());
            position.setY(position.getY() + velocity.getY());
        }
    }
}
