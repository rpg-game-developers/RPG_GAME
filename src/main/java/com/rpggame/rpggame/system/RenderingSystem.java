package com.rpggame.rpggame.system;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.rendering.RenderingComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class RenderingSystem extends EntitySystem {

    protected SpriteManager sprites;
    protected OrthographicCamera camera;
    protected SpriteBatch batch;

    public RenderingSystem(SpriteManager sprites, OrthographicCamera camera, SpriteBatch batch) {
        super(new EntityFamily(RenderingComp.class, TransformComp.class));
        this.sprites = sprites;
        this.camera = camera;
        this.batch = batch;
    }

    @Override
    public void onRender() {
        batch.begin();
        for (Entity entity : getEntities()) {
            for (RenderingComp component : entity.getComponents(RenderingComp.class)) {
                Matrix3 transform = TransformComp.getCombinedMatrix(entity);
                component.render(sprites, camera, batch, transform);
            }
        }
        batch.end();
    }
}
