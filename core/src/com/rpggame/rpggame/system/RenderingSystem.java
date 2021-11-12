package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.PositionComponent;
import com.rpggame.rpggame.component.RenderingComponent;
import com.rpggame.rpggame.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class RenderingSystem implements System {
    private List<Entity> entities;

    public RenderingSystem() {
        this.entities = new ArrayList<>();
    }

    @Override
    public void tryToAddEntity(Entity entity) {
        if (entity.hasComponent(RenderingComponent.class) &&
            entity.hasComponent(PositionComponent.class)) {
            entities.add(entity);
        }
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : entities) {
            RenderingComponent component = entity.getComponent(RenderingComponent.class);
            PositionComponent position = entity.getComponent(PositionComponent.class);
            component.render(batch, position.getX(), position.getY());
        }
    }
}
