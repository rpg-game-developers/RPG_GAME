package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.system.SpriteManager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpriteComp implements RenderingComp {

    private String sprite;
    private transient final static float[] vertices = new float[20];

    @Override
    public void render(SpriteManager sprites, OrthographicCamera camera, SpriteBatch batch, Matrix3 transform) {
        if (sprite != null) {
            Texture texture = sprites.getSprite(sprite);
            Vector2 tl = new Vector2(0,0).mul(transform);
            Vector2 tr = new Vector2(texture.getWidth(), 0).mul(transform);
            Vector2 br = new Vector2(texture.getWidth(),texture.getHeight()).mul(transform);
            Vector2 bl = new Vector2(0, texture.getHeight()).mul(transform);

            vertices[0] = tl.x;
            vertices[1] = tl.y;
            vertices[2] = batch.getPackedColor();
            vertices[3] = 0;
            vertices[4] = 1;

            vertices[5] = bl.x;
            vertices[6] = bl.y;
            vertices[7] = batch.getPackedColor();
            vertices[8] = 0;
            vertices[9] = 0;

            vertices[10] = br.x;
            vertices[11] = br.y;
            vertices[12] = batch.getPackedColor();
            vertices[13] = 1;
            vertices[14] = 0;

            vertices[15] = tr.x;
            vertices[16] = tr.y;
            vertices[17] = batch.getPackedColor();
            vertices[18] = 1;
            vertices[19] = 1;

            batch.draw(texture, vertices, 0, 20);
        }
    }

    @Override
    public Component clone() {
        return new SpriteComp(sprite);
    }

    @Override
    public JsonObject toJson() {
        JsonObject spriteCompAsJson = new JsonObject();
        spriteCompAsJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());
        spriteCompAsJson.addProperty(Constants.JSON_KEYS.SPRITE_STRING, sprite); // TODO: Change with the filepath of the sprite.
        return spriteCompAsJson;
    }

}
