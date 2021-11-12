package com.rpggame.rpggame.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteComponent implements RenderingComponent {
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
    public void render(SpriteBatch batch, double x, double y) {
        batch.draw(sprite, (int)x, (int)y);
    }
}
