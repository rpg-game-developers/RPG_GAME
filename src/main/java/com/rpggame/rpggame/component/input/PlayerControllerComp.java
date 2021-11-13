package com.rpggame.rpggame.component.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.entity.Entity;

public class PlayerControllerComp implements InputComp {
    @Override
    public void handleInput(Entity entity) {
        VelocityComp vel = entity.getComponent(VelocityComp.class);
        double speed = 4;

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
}
