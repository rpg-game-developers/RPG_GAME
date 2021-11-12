package com.rpggame.rpggame.entity;

import com.rpggame.rpggame.system.System;

import java.util.ArrayList;
import java.util.List;

public class EntityWorld {
    private List<Entity> entities;
    private List<System> systems;

    public EntityWorld() {
        entities = new ArrayList<>();
        systems = new ArrayList<>();
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<System> getSystems() {
        return systems;
    }

    public <T> T getSystem(Class<T> clazz) {
        for (System comp : systems) {
            if (clazz.isInstance(comp)) {
                return (T) comp;
            }
        }
        return null;
    }

    public void addSystem(System system) {
        systems.add(system);
    }

}
