package com.rpggame.rpggame.entity;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.system.EntitySystem;

import java.util.ArrayList;
import java.util.List;

public class Entity implements Comparable<Entity> {
    private List<Component> components;
    private EntityWorld world;

    // entities are connected in a linked list like way
    private Entity parent;
    private Entity next;
    private Entity prev;
    private Entity firstChild;
    private Entity lastChild;
    private int indexNumber;
    private static final int sqrtNumber = 1000;

    /**
     * Intended way to create entities.
     * It is more optimal to first create an entity like this,
     * then add all the components,
     * and then add it to the world.
     */
    public Entity() {
        this.world = null;
        this.components = new ArrayList<>();
        this.parent = null;
        this.next = null;
        this.prev = null;
        this.firstChild = null;
        this.lastChild = null;
        this.indexNumber = 0;
    }

    /**
     * Setting the world of the entity.
     * WARNING: Should normally only be called by EntityWorld and itself.
     *
     * @param world  the new world
     */
    public void setWorld(EntityWorld world) {
        this.world = world;
    }

    /**
     * Makes the other entity a child of this entity.
     * This is the intended way to add an entity to a world.
     * This also adds the child to the systems it should be part of.
     * WARNING: This should only be called when the parent is inside a world.
     *
     * @param entity  The child entity
     */
    public void addChild(Entity entity) {
        entity.setWorld(this.world);
        entity.parent = this;

        if (this.lastChild == null) {
            this.firstChild = entity;
        } else {
            this.lastChild.next = entity;
            entity.prev = this.lastChild;
        }
        this.lastChild = entity;

        entity.indexNumber = this.indexNumber + 1;
        this.world.updateEntityIndex();

        for (EntitySystem system : this.world.getSystems()) {
            system.onNewEntityAdded(entity);
        }
    }

    /**
     * The intended way to get rid of en entity.
     * It recursively calls destroy on its children.
     * It automatically removes the entity from the applied systems.
     */
    public void destroy() {
        Entity curChild = this.firstChild;
        while (curChild != null) {
            Entity nextChild = curChild.next;
            curChild.destroy();
            curChild = nextChild;
        }

        for (EntitySystem system : this.world.getSystems()) {
            system.onEntityRemoved(this);
        }

        if (this.prev != null) {
            this.prev.next = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        if (this.parent != null) {
            if (this.parent.firstChild == this) {
                this.parent.firstChild = this.next;
            }
            if (this.parent.lastChild == this) {
                this.parent.lastChild = this.prev;
            }
        }
        this.parent = null;
        this.next = null;
        this.prev = null;
        this.firstChild = null;
        this.lastChild = null;
        this.world = null;
        this.components.clear();
    }

    /**
     * Update the indexNumber of this entity and all its children recursively.
     *
     * @param curIndex  should be 0 the first time you call this.
     * @return  the biggest curIndex of all its children.
     */
    public int updateIndex(int curIndex) {
        this.indexNumber = curIndex;
        curIndex += sqrtNumber;
        Entity curChild = this.firstChild;
        while (curChild != null) {
            curIndex = curChild.updateIndex(curIndex);
            curChild = curChild.next;
        }
        return curIndex;
    }

    /**
     * Get a newly created list of the children of this entity.
     *
     * @return  a list of the children of the entity
     */
    public List<Entity> getChildren() {
        List<Entity> result = new ArrayList<>();
        Entity curChild = this.firstChild;
        while (curChild != null) {
            result.add(curChild);
            curChild = curChild.next;
        }
        return result;
    }

    /**
     * Returns the parent of this entity, or null if it doesn't have one.
     *
     * @return  The parent of this entity.
     */
    public Entity getParent() {
        return this.parent;
    }

    /**
     * Get a list of the subtree of this entity recursively.
     * They are sorted by dfs pre-order.
     *
     * @return  a list of all its children and itself.
     */
    public List<Entity> getTree() {
        List<Entity> result = new ArrayList<>();
        getTree(result);
        return result;
    }
    private void getTree(List<Entity> result) {
        result.add(this);
        Entity curChild = this.firstChild;
        while (curChild != null) {
            curChild.getTree(result);
            curChild = curChild.next;
        }
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
     * Get a list of components in this entity of the type T.
     *
     * @param clazz<T>  The class of the components to get
     * @return          A list of components of type T
     */
    public <T> List<T> getComponents(Class<T> clazz) {
        List<T> result = new ArrayList<>();
        for (Component comp : components) {
            if (clazz.isInstance(comp)) {
                result.add((T) comp);
            }
        }
        return result;
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

    @Override
    public int compareTo(Entity other) {
        return this.indexNumber - other.indexNumber;
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
