package com.rpggame.rpggame.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class SpriteManager {
    Map<String, Texture> sprites;

    public SpriteManager() {
        sprites = new HashMap<>();
    }

    public Texture getSprite(String filePath) {
        if (!sprites.containsKey(filePath)) {
            sprites.put(filePath, new Texture(Gdx.files.internal(filePath)));
        }
        return sprites.get(filePath);
    }

    public void dispose() {
        for (Map.Entry<String, Texture> entry : sprites.entrySet()) {
            entry.getValue().dispose();
        }
    }
}
