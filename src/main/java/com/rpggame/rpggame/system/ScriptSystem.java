package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.ScriptComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;

public class ScriptSystem extends EntitySystem {

    public ScriptSystem() {
        super(new EntityFamily(ScriptComp.class));
    }

    @Override
    public void onRender() {
        for (Entity entity : getEntities()) {
            entity.getComponent(ScriptComp.class).update(entity);
        }
    }
}
