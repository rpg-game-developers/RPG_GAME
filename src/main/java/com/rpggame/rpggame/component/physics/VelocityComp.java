package com.rpggame.rpggame.component.physics;

import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.constants.Constants;
import lombok.Getter;

public class VelocityComp implements Component {

    @Getter
    private Vector2 velocity;

    public VelocityComp() {
        this.velocity = new Vector2();
    }

    public VelocityComp(float x, float y) {
        this.velocity = new Vector2(x,y);
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

    public Component clone() {
        return new VelocityComp(velocity.x, velocity.y);
    }

    @Override
    public JsonObject toJson() {
        JsonObject velocityJson = new JsonObject();

        velocityJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());

        JsonArray jsonVelocity = new JsonArray();
        jsonVelocity.add(this.velocity.x);
        jsonVelocity.add(this.velocity.y);
        velocityJson.add(Constants.JSON_KEYS.VELOCITY_STRING, jsonVelocity);

        return velocityJson;
    }

}
