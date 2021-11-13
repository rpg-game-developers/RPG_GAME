package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.PositionComponent;
import com.rpggame.rpggame.component.RenderingComponent;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class RenderingEntitySystem extends EntitySystem {

    public RenderingEntitySystem() {
        super(new EntityFamily(RenderingComponent.class, PositionComponent.class));
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : getEntities()) {
            RenderingComponent component = entity.getComponent(RenderingComponent.class);
            PositionComponent position = entity.getComponent(PositionComponent.class);
            component.render(batch, position.getX(), position.getY());
        }
    }
}
