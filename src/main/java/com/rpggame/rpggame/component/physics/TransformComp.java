package com.rpggame.rpggame.component.physics;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.constants.Constants;
import org.lwjgl.system.MathUtil;

import javax.management.ValueExp;

public class TransformComp implements Component {
    private final Vector2 position;
    private final Vector2 scale;
    private float rotation;

    public TransformComp() {
        this.position = new Vector2(0.0f, 0.0f);
        this.scale = new Vector2(1.0f, 1.0f);
        this.rotation = 0.0f;
    }

    public TransformComp(float x, float y) {
        this.position = new Vector2(x, y);
        this.scale = new Vector2(1.0f, 1.0f);
        this.rotation = 0.0f;
    }

    public TransformComp(Vector2 position, Vector2 scale, float rotation) {
        this.position = position;
        this.scale = scale;
        this.rotation = rotation;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 nextPosition) {
        this.position.x = nextPosition.x;
        this.position.y = nextPosition.y;
    }

    public float getX() {
        return this.position.x;
    }

    public void setX(float x) {
        this.position.x = x;
    }

    public float getY() {
        return this.position.y;
    }

    public void setY(float y) {
        this.position.y = y;
    }

    public Vector2 getScale() {
        return this.scale;
    }

    public void setScale(Vector2 newScale) {
        this.scale.x = newScale.x;
        this.scale.y = newScale.y;
    }

    public float getRotation() {
        return this.rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation - (float)(Math.PI * 2.0 * Math.floor((rotation + Math.PI) / (Math.PI * 2.0)));
    }

    public Matrix3 getMatrix() {
        return new Matrix3()
                .setToTranslation(this.position)
                .rotateRad(this.rotation)
                .scale(this.scale);
    }

    @Override
    public Component clone() {
        return new TransformComp(new Vector2(this.position), new Vector2(this.scale), this.rotation);
    }

    @Override
    public JsonObject toJson() {
        JsonObject transformCompJson = new JsonObject();
        transformCompJson.addProperty(Constants.BACKEND.TYPE_STRING, this.getClass().getSimpleName());
        return transformCompJson;
    }
}
