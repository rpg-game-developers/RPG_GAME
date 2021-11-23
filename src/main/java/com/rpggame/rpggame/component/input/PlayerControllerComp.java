package com.rpggame.rpggame.component.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.entity.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerControllerComp implements InputComp {

    private float speed;

    public PlayerControllerComp() {
        this.speed = 1.0f;
    }

    public PlayerControllerComp(float speed) {
        this.speed = speed;
    }

    @Override
    public void handleInput(Entity entity) {
        if (!entity.hasComponent(VelocityComp.class))
            return;

        VelocityComp vel = entity.getComponent(VelocityComp.class);

        vel.setX(0);
        vel.setY(0);

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            vel.setX(vel.getX() - speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            vel.setX(vel.getX() + speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)) {
            vel.setY(vel.getY() + speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            vel.setY(vel.getY() - speed);
        }
    }

    @Override
    public Component clone() {
        return new PlayerControllerComp(speed);
    }

    @Override
    public JsonObject toJson() {
        JsonObject playerControllerJson = new JsonObject();
        playerControllerJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());
        return playerControllerJson;
    }


}
