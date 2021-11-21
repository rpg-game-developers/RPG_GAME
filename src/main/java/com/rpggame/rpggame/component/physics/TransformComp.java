package com.rpggame.rpggame.component.physics;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;

public class TransformComp implements Component {
    private final Matrix3 matrix;

    public TransformComp() {
        this.matrix = new Matrix3();
    }

    public TransformComp(float x, float y) {
        this.matrix = new Matrix3();
        this.matrix.setToTranslation(x, y);
    }

    public TransformComp(Matrix3 matrix) {
        this.matrix = matrix;
    }

    public Vector2 getPosition() {
        return this.matrix.getTranslation(new Vector2());
    }

    public void setPosition(Vector2 nextPosition) {
        float[] values = this.matrix.getValues();
        values[Matrix3.M02] = nextPosition.x;
        values[Matrix3.M12] = nextPosition.y;
    }

    public float getX() {
        return this.matrix.getValues()[Matrix3.M02];
    }

    public void setX(float x) {
        this.matrix.getValues()[Matrix3.M02] = x;
    }

    public float getY() {
        return this.matrix.getValues()[Matrix3.M12];
    }

    public void setY(float y) {
        this.matrix.getValues()[Matrix3.M12] = y;
    }

    public Vector2 getScale() {
        return this.matrix.getScale(new Vector2());
    }

    public void setScale(Vector2 newScale) {
        this.matrix.getValues()[Matrix3.M00] = newScale.x;
        this.matrix.getValues()[Matrix3.M11] = newScale.y;
    }

    public Matrix3 getMatrix() {
        return this.matrix;
    }

    @Override
    public Component clone() {
        return new TransformComp(new Matrix3(this.matrix));
    }

    @Override
    public JsonObject toJson() {
        return null;
    }
}
