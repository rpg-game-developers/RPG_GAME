package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
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
                TransformComp transformComp = entity.getComponent(TransformComp.class);

                Matrix3 transform = new Matrix3(transformComp.getMatrix());
                Entity current = entity.getParent();
                while (current != null) {
                    if (current.hasComponent(TransformComp.class)) {
                        transform.mul(current.getComponent(TransformComp.class).getMatrix());
                    }
                    current = current.getParent();
                }

                component.render(camera, batch, transform);
            }
        }
        batch.end();
    }
}
