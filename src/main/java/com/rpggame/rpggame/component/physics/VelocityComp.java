package com.rpggame.rpggame.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.rpggame.rpggame.component.Component;

public class VelocityComp implements Component {
    private Vector2 velocity;

    public VelocityComp() {
        this.velocity = new Vector2();
    }

    public VelocityComp(float x, float y) {
        this.velocity = new Vector2(x,y);
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getX() {
        return velocity.x;
    }

    public void setX(float x) {
        this.velocity.x = x;
    }

    public float getY() {
        return this.velocity.y;
    }

    public void setY(float y) {
        this.velocity.y = y;
    }
}
