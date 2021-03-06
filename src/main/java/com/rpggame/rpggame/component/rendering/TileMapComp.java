package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.system.SpriteManager;
import lombok.Getter;
import lombok.Setter;

public class TileMapComp implements RenderingComp {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Texture tiles;
    private TextureRegion[][] splitTiles;
    @Getter
    @Setter
    private int tileWidth;
    @Getter
    @Setter
    private int tileHeight;
    @Getter
    @Setter
    private int margin;

    public TileMapComp() {
    }

    public TileMapComp(FileHandle spriteHandle) {
        setSprite(spriteHandle);
    }

    public Texture getSprite() {
        return tiles;
    }

    public void setSprite(FileHandle spriteHandle) {
        this.tiles = new Texture(spriteHandle);
        this.splitTiles = TextureRegion.split(this.tiles, tileWidth + margin, tileHeight + margin);
        this.tiledMap = new TiledMap();
        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer layer = new TiledMapTileLayer(150, 150, tileWidth, tileHeight);
        layers.add(layer);
        this.renderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TextureRegion[][] getSplitTiles() {
        return splitTiles;
    }

    public int getSheetRows() {
        if (splitTiles != null) {
            return splitTiles.length;
        }
        return 0;
    }

    public int getSheetColumns() {
        if(splitTiles != null) {
            return splitTiles[0].length;
        }
        return 0;
    }

    public int getRows() {
        return 150;
    }

    public int getColumns() {
        return 150;
    }

    @Override
    public void render(SpriteManager sprites, OrthographicCamera camera, SpriteBatch batch, Matrix3 transform) {
        if (this.renderer != null) {
            batch.end();
            Matrix4 transform4 = new Matrix4();
            transform4.set(transform);
            transform4.mulLeft(camera.combined);
            renderer.setView(transform4, 0.0f, 0.0f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            renderer.render();
            batch.begin();
        }
    }

    @Override
    public Component clone() {
        return new TileMapComp();
    }

    @Override
    public JsonObject toJson() {
        JsonObject tileMapJson = new JsonObject();
        tileMapJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());
        return tileMapJson;
    }

}
