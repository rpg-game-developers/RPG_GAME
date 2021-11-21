package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SpriteComp implements RenderingComp {
    private Texture sprite;

    public SpriteComp() {
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch, double x, double y) {
        if (sprite != null) {
            batch.draw(sprite, (int)x, (int)y);
        }
    }

    @Override
    public Component clone() {
        return new SpriteComp(sprite);
    }

    @Override
    public JsonObject toJson() {
        JsonObject spriteCompAsJson = new JsonObject();
        return spriteCompAsJson;
    }

}
