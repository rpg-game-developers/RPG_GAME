package com.rpggame.rpggame.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteComponent implements RenderingComponent, LogicComponent {
    private Texture sprite;

    public SpriteComponent() {
    }

    public SpriteComponent(Texture sprite) {
        this.sprite = sprite;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(sprite, 0, 0);
    }

    @Override
    public void doSomething() {

    }
}
