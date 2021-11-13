package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.PositionComponent;
import com.rpggame.rpggame.component.VelocityComponent;
import com.rpggame.rpggame.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSystem implements System {
    private List<Entity> entities;

    public PhysicsSystem() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void tryToAddEntity(Entity entity) {
        if (entity.hasComponent(PositionComponent.class) &&
            entity.hasComponent(VelocityComponent.class)) {
            entities.add(entity);
        }
    }

    public void applyPhysics() {
        for (Entity entity : entities) {
            VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
            PositionComponent position = entity.getComponent(PositionComponent.class);
            position.setX(position.getX() + velocity.getX());
            position.setY(position.getY() + velocity.getY());
        }
    }
}
