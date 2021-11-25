package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.component.physics.collision.CollisionComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class PhysicsSystem extends EntitySystem {

    public PhysicsSystem() {
        super(new EntityFamily(TransformComp.class, VelocityComp.class));
    }

    @Override
    public void onRender() {
        CollisionSystem collisionSystem = getWorld().getSystem(CollisionSystem.class);

        for (Entity entity : getEntities()) {
            VelocityComp velocity = entity.getComponent(VelocityComp.class);
            TransformComp transform = entity.getComponent(TransformComp.class);

            TransformComp nextTransform = (TransformComp) transform.clone();

            if (entity.hasComponent(CollisionComp.class) && collisionSystem != null) {
                CollisionComp collisionComp = entity.getComponent(CollisionComp.class);

                nextTransform.setX(transform.getX() + velocity.getX());
                if(collisionSystem.collisionCheck(entity, nextTransform, collisionComp)) {
                    nextTransform.setX(transform.getX());
                }

                nextTransform.setY(transform.getY() + velocity.getY());
                if(collisionSystem.collisionCheck(entity, nextTransform, collisionComp)) {
                    nextTransform.setY(transform.getY());
                }
            } else {
                nextTransform.setX(transform.getX() + velocity.getX());
                nextTransform.setY(transform.getY() + velocity.getY());
            }

            transform.setPosition(nextTransform.getPosition());
        }
    }
}
