package com.rpggame.rpggame.entity;

import com.rpggame.rpggame.system.System;
import com.rpggame.rpggame.component.Component;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private long entityID;
    private List<Component> components;
    private EntityWorld world;

    public Entity(EntityWorld world) {
        this.world = world;
        this.components = new ArrayList<>();
    }

    public <T> boolean hasComponent(Class<T> clazz) {
        for (Component comp : components) {
            if (clazz.isInstance(comp)) {
                return true;
            }
        }
        return false;
    }

    public <T> T getComponent(Class<T> clazz) {
        for (Component comp : components) {
            if (clazz.isInstance(comp)) {
               return (T) comp;
            }
        }
        return null;
    }

    public void addComponent(Component newComponent) {
        components.add(newComponent);

        for (System system : world.getSystems()) {
            system.tryToAddEntity(this);
        }
    }
}
