package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.rendering.RenderingComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class RenderingEntitySystem extends EntitySystem {

    public RenderingEntitySystem() {
        super(new EntityFamily(RenderingComp.class, TransformComp.class));
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        for (Entity entity : getEntities()) {
            RenderingComp component = entity.getComponent(RenderingComp.class);
            TransformComp position = entity.getComponent(TransformComp.class);
            component.render(camera, batch, position.getX(), position.getY());
        }
    }
}