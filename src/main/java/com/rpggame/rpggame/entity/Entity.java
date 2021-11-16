package com.rpggame.rpggame.entity;

import com.rpggame.rpggame.component.Component;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    private List<Component> components;
    private EntityWorld world;

    /**
     * Intended way to create entities.
     * It is more optimal to first create an entity like this,
     * then add all the components,
     * and then add it to the world.
     */
    public Entity() {
        this.world = null;
        this.components = new ArrayList<>();
    }

    /**
     * Setting the world of the entity.
     * WARNING: Should normally only be called by EntityWorld.
     *
     * @param world  the new world
     */
    public void setWorld(EntityWorld world) {
        this.world = world;
    }

    /**
     * Checks if this entity has at least one component of type T.
     *
     * @param clazz<T>  The class of the component to check
     * @return          true if it exists, otherwise false
     */
    public <T> boolean hasComponent(Class<T> clazz) {
        for (Component comp : components) {
            if (clazz.isInstance(comp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a single component in this entity of the type T.
     * If this component does not exist it returns null.
     *
     * @param clazz<T>  The class of the component to get
     * @return          The component if it exists, otherwise null
     */
    public <T> T getComponent(Class<T> clazz) {
        for (Component comp : components) {
            if (clazz.isInstance(comp)) {
               return (T) comp;
            }
        }
        return null;
    }

    /**
     * Add a new component to this entity.
     * This automatically adds this entity to the required systems.
     *
     * @param newComponent  The component to add.
     */
    public void addComponent(Component newComponent) {
        components.add(newComponent);
        if (world != null)
            world.notifyAddComponent(newComponent.getClass(), this);
    }

    /**
     * Remove all components from this entity of type T.
     * This automatically removes this entity from the systems it is not part of anymore.
     *
     * @param clazz<T>  The class type to remove
     */
    public <T> void removeComponent(Class<T> clazz) {
        if (world != null)
            world.notifyRemoveComponent(clazz, this);
        components.removeIf(clazz::isInstance);
    }

    /**
     * Makes a deep copy of this entity and all its components.
     * This new entity is not part of a world.
     *
     * @return  A new entity with a clone of this entity's components.
     */
    public Entity clone() {
        Entity entity = new Entity();
        for (Component comp : components) {
            entity.components.add(comp.clone());
        }
        return entity;
    }
}
