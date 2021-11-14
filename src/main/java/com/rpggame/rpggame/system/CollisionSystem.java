package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.collision.CollisionComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class CollisionSystem extends EntitySystem {

    public CollisionSystem() {
        super(new EntityFamily(TransformComp.class, CollisionComp.class));
    }

    public boolean collisionCheck(Entity skippingEntity, TransformComp transform, CollisionComp collision) {
        for (Entity entity : getEntities()) {
            if (entity == skippingEntity)
                continue;

            TransformComp checkingTransform = entity.getComponent(TransformComp.class);
            CollisionComp checkingCollision = entity.getComponent(CollisionComp.class);
            if (checkingCollision.checkCollision(checkingTransform, collision, transform)) {
                return true;
            }
        }

        return false;
    }
}
