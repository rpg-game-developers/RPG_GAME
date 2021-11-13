package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.physics.PositionComp;
import com.rpggame.rpggame.component.rendering.RenderingComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class RenderingEntitySystem extends EntitySystem {

    public RenderingEntitySystem() {
        super(new EntityFamily(RenderingComp.class, PositionComp.class));
    }

    public void render(SpriteBatch batch) {
        for (Entity entity : getEntities()) {
            RenderingComp component = entity.getComponent(RenderingComp.class);
            PositionComp position = entity.getComponent(PositionComp.class);
            component.render(batch, position.getX(), position.getY());
        }
    }
}
