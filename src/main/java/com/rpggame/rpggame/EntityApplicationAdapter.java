package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.rpggame.entity.EntityWorld;

public class EntityApplicationAdapter extends ApplicationAdapter implements InputProcessor {
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected EntityWorld entityWorld;
    protected SpriteBatch batch;

    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 500);
        viewport = new ScreenViewport(camera);
        entityWorld = new EntityWorld();
        batch = new SpriteBatch();
    }

    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    public EntityWorld getEntityWorld() {
        return this.entityWorld;
    }

    public SpriteBatch getSpriteBatch() {
        return this.batch;
    }

    @Override
    public boolean keyDown(int keycode) {
        return entityWorld.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return entityWorld.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char c) {
        return entityWorld.keyTyped(c);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        entityWorld.touchDown(x, y, pointer, button);
        return true;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        entityWorld.touchUp(x, y, pointer, button);
        return true;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        entityWorld.touchDragged(x, y, pointer);
        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        entityWorld.mouseMoved(x, y);
        return true;
    }

    @Override
    public boolean scrolled(float deltaX, float deltaY) {
        entityWorld.scrolled(deltaX, deltaY);
        return true;
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height);
    }
    
}
