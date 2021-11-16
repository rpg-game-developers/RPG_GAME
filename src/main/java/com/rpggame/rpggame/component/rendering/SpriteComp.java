package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteComp implements RenderingComp {
    private Texture sprite;

    public SpriteComp() {
    }

    public SpriteComp(Texture sprite) {
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
        if (sprite != null) {
            batch.draw(sprite, (int)x, (int)y);
        }
    }
}
