package com.rpggame.rpggame.component.rendering;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;

public class TileMapComp implements RenderingComp {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private Texture tiles;
    private TextureRegion[][] splitTiles;
    private int tileWidth;
    private int tileHeight;
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

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public TextureRegion[][] getSplitTiles() {
        return splitTiles;
    }

    public int getRows() {
        if (splitTiles != null) {
            return splitTiles.length;
        }
        return 0;
    }

    public int getColumns() {
        if(splitTiles != null) {
            return splitTiles[0].length;
        }
        return 0;
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch, double x, double y) {
        if (this.renderer != null) {
            batch.end();
            camera.update();
            renderer.setView(camera);
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
        return null;
    }

}
