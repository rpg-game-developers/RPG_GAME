package com.rpggame.rpggame.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;

public class TransformComp implements Component {
    private Vector2 position;

    public TransformComp() {
        position = new Vector2();
    }

    public TransformComp(float x, float y) {
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return new Vector2(position);
    }

    public void setPosition(Vector2 nextPosition) {
        this.position = nextPosition;
    }

    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        position.x = x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y) {
        position.y = y;
    }

    @Override
    public Component clone() {
        return new TransformComp(position.x, position.y);
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
