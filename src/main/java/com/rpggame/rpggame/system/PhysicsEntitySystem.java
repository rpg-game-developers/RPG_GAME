package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.component.physics.collision.CollisionComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class PhysicsEntitySystem extends EntitySystem {

    public PhysicsEntitySystem() {
        super(new EntityFamily(TransformComp.class, VelocityComp.class));
    }

    public void applyPhysics() {
        CollisionSystem collisionSystem = getWorld().getSystem(CollisionSystem.class);

        for (Entity entity : getEntities()) {
            VelocityComp velocity = entity.getComponent(VelocityComp.class);
            TransformComp transform = entity.getComponent(TransformComp.class);

            TransformComp nextTransform = new TransformComp();
            nextTransform.setPosition(transform.getPosition());
            nextTransform.setX(transform.getX() + velocity.getX());
            nextTransform.setY(transform.getY() + velocity.getY());

            if (entity.hasComponent(CollisionComp.class) && collisionSystem != null) {
                CollisionComp collisionComp = entity.getComponent(CollisionComp.class);
                if(!collisionSystem.collisionCheck(entity, nextTransform, collisionComp)) {
                    transform.setPosition(nextTransform.getPosition());
                }
            } else {
                transform.setPosition(nextTransform.getPosition());
            }
        }
    }
}
