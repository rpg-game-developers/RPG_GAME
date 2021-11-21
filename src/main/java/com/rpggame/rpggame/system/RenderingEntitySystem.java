package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.rendering.RenderingComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class RenderingEntitySystem extends EntitySystem {

    private OrthographicCamera camera;
    private SpriteBatch batch;

    public RenderingEntitySystem(OrthographicCamera camera, SpriteBatch batch) {
        super(new EntityFamily(RenderingComp.class, TransformComp.class));
        this.camera = camera;
        this.batch = batch;
    }

    @Override
    public void onRender() {
        batch.begin();
        for (Entity entity : getEntities()) {
            for (RenderingComp component : entity.getComponents(RenderingComp.class)) {
                TransformComp position = entity.getComponent(TransformComp.class);
                component.render(camera, batch, position.getX(), position.getY());
            }
        }
        batch.end();
    }
}
