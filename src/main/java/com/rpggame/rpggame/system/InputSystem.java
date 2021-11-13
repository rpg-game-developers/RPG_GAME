package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.input.InputComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class InputSystem extends EntitySystem {

    public InputSystem() {
        super(new EntityFamily(InputComp.class));
    }

    public void handleInput() {
        for (Entity entity : getEntities()) {
            InputComp inputComp = entity.getComponent(InputComp.class);
            inputComp.handleInput(entity);
        }
    }
}
