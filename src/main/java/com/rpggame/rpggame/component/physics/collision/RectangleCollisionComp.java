package com.rpggame.rpggame.component.physics.collision;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.constants.Constants;

public class RectangleCollisionComp implements CollisionComp {
    private Vector2 size;

    public RectangleCollisionComp() {
        this.size = new Vector2();
    }

    public RectangleCollisionComp(float x, float y) {
        this.size = new Vector2(x, y);
    }

    public RectangleCollisionComp(Vector2 size) {
        this.size = size;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    @Override
    public boolean checkCollision(TransformComp transform, CollisionComp other, TransformComp otherTransform) {
        if (other instanceof RectangleCollisionComp) {
            RectangleCollisionComp rect = (RectangleCollisionComp) other;

            OBB2D a = new OBB2D(this.size, transform.getMatrix());
            OBB2D b = new OBB2D(rect.size, otherTransform.getMatrix());

            return a.overlaps(b);
        }
        return false;
    }

    @Override
    public Component clone() {
        return new RectangleCollisionComp(size.x, size.y);
    }

    @Override
    public JsonObject toJson() {
        JsonObject rectangleCollisionJson = new JsonObject();
        rectangleCollisionJson.addProperty(Constants.BACKEND.TYPE_STRING, this.getClass().getSimpleName());
        return rectangleCollisionJson;
    }

}
