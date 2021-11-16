package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rpggame.rpggame.entity.EntityWorld;

public class EntityApplicationAdapter extends ApplicationAdapter implements InputProcessor {
    protected OrthographicCamera camera;
    protected Viewport viewport;
    protected EntityWorld entityWorld;

    public void create() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 500);
        viewport = new ScreenViewport(camera);
        entityWorld = new EntityWorld();
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public EntityWorld getEntityWorld() {
        return entityWorld;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return true;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(float deltaX, float deltaY) {
        return false;
    }

    @Override
    public void resize (int width, int height) {
        viewport.update(width, height);
    }
    
}
