package com.rpggame.rpggame.entity;

import com.badlogic.gdx.InputProcessor;
import com.rpggame.rpggame.controller.Observer;
import com.rpggame.rpggame.controller.Subject;
import com.rpggame.rpggame.entity.events.AddComponentEvent;
import com.rpggame.rpggame.entity.events.RemoveComponentEvent;
import com.rpggame.rpggame.system.EntitySystem;
import org.apache.commons.lang3.ClassUtils;
import org.lwjgl.Sys;

import java.util.*;

public class EntityWorld implements InputProcessor {
    private List<Entity> entities;
    private List<EntitySystem> entitySystems;
    private Map<Class<?>, Subject<AddComponentEvent>> addComponentSubjects;
    private Map<Class<?>, Subject<RemoveComponentEvent>> removeComponentSubjects;

    public EntityWorld() {
        this.entities = new ArrayList<>();
        this.entitySystems = new ArrayList<>();
        this.addComponentSubjects = new HashMap<>();
        this.removeComponentSubjects = new HashMap<>();
    }

    /**
     * The intended way to add an entity to a world.
     * This also adds the entity to the systems it should be part of.
     * For better performance should be called after adding all the components to the entity.
     *
     * @param entity  The entity to add to this world.
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.setWorld(this);

        for (EntitySystem system : entitySystems) {
            system.onNewEntityAdded(entity);
        }
    }

    /**
     * The intended way to remove an entity from a world.
     * It automatically removes the entity from the applied systems.
     *
     * @param entity  The entity to remove from this world.
     */
    public void removeEntity(Entity entity) {
        for (EntitySystem system : entitySystems) {
            system.onEntityRemoved(entity);
        }

        entities.remove(entity);
    }

    public List<Entity> getEntities() {
        return Collections.unmodifiableList(entities);
    }

    public List<EntitySystem> getSystems() {
        return entitySystems;
    }

    public <T> T getSystem(Class<T> clazz) {
        for (EntitySystem comp : entitySystems) {
            if (clazz.isInstance(comp)) {
                return (T) comp;
            }
        }
        return null;
    }

    /**
     * Adds a system to this world.
     * This will allow the system to automatically keep track of the entities it is interested in.
     *
     * @param entitySystem  The system to add.
     */
    public void addSystem(EntitySystem entitySystem) {
        entitySystems.add(entitySystem);
        entitySystem.connect(this);
    }

    /**
     * Removes a system from this world, and disposes it.
     *
     * @param entitySystem  The system to remove.
     */
    public void removeSystem(EntitySystem entitySystem) {
        entitySystems.remove(entitySystem);
        entitySystem.dispose();
    }


    /**
     * Lets the observer start listening to AddComponentEvents.
     *
     * @param clazz<T>  The class of the component to listen for.
     * @param observer  The observer that will listen for this event.
     */
    public <T> void subscribeAddComponent(Class<T> clazz, Observer<AddComponentEvent> observer) {
        Subject<AddComponentEvent> subject = addComponentSubjects.get(clazz);

        if (subject == null) {
            subject = new Subject<>();
            addComponentSubjects.put(clazz, subject);
        }

        subject.subscribe(observer);
    }

    /**
     * Lets the observer start listening to RemoveComponentEvents.
     *
     * @param clazz<T>  The class of the component to listen for.
     * @param observer  The observer that will listen for this event.
     */
    public <T> void subscribeRemoveComponent(Class<T> clazz, Observer<RemoveComponentEvent> observer) {
        Subject<RemoveComponentEvent> subject = removeComponentSubjects.get(clazz);

        if (subject == null) {
            subject = new Subject<>();
            removeComponentSubjects.put(clazz, subject);
        }

        subject.subscribe(observer);
    }

    /**
     * Stops the observer from listening to addComponentEvents.
     *
     * @param clazz<T>  The class of the component to stop listening for.
     * @param observer  The observer that will stop listening for this event.
     */
    public <T> void unsubscribeAddComponent(Class<T> clazz, Observer<AddComponentEvent> observer) {
        Subject<AddComponentEvent> subject = addComponentSubjects.get(clazz);

        if(subject == null)
            return;

        subject.unsubscribe(observer);
    }

    /**
     * Stops the observer from listening to removeComponentEvents.
     *
     * @param clazz<T>  The class of the component to stop listening for.
     * @param observer  The observer that will stop listening for this event.
     */
    public <T> void unsubscribeRemoveComponent(Class<T> clazz, Observer<RemoveComponentEvent> observer) {
        Subject<RemoveComponentEvent> subject = removeComponentSubjects.get(clazz);

        if(subject == null)
            return;

        subject.unsubscribe(observer);
    }

    /**
     * Notify all the subscribed observers that component T has been added to this entity.
     *
     * @param clazz<T>  The class of the component.
     * @param entity    The entity where the component was added to.
     */
    public <T> void notifyAddComponent(Class<T> clazz, Entity entity) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(clazz);
        classes.addAll(ClassUtils.getAllSuperclasses(clazz));
        classes.addAll(ClassUtils.getAllInterfaces(clazz));

        for (Class<?> curClass : classes) {
            Subject<AddComponentEvent> subject = addComponentSubjects.get(curClass);

            if (subject == null)
                continue;

            subject.notify(new AddComponentEvent(entity));
        }
    }

    /**
     * Notify all the subscribed observers that component T has been removed from this entity.
     *
     * @param clazz<T>  The class of the component.
     * @param entity    The entity where the component was added to.
     */
    public <T> void notifyRemoveComponent(Class<T> clazz, Entity entity) {
        List<Class<?>> classes = new ArrayList<>();
        classes.add(clazz);
        classes.addAll(ClassUtils.getAllSuperclasses(clazz));
        classes.addAll(ClassUtils.getAllInterfaces(clazz));

        for (Class<?> curClass : classes) {
            Subject<RemoveComponentEvent> subject = removeComponentSubjects.get(curClass);

            if (subject == null)
                continue;

            subject.notify(new RemoveComponentEvent(entity));
        }
    }

    /**
     * This function should be called from the render function in your game.
     */
    public void onRender() {
        for (EntitySystem system : entitySystems) {
            system.onRender();
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        for (EntitySystem system : entitySystems) {
            if (system.keyDown(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (EntitySystem system : entitySystems) {
            if (system.keyUp(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        for (EntitySystem system : entitySystems) {
            if (system.keyTyped(c)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        for (EntitySystem system : entitySystems) {
            if (system.touchDown(x, y, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        for (EntitySystem system : entitySystems) {
            if (system.touchUp(x, y, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        for (EntitySystem system : entitySystems) {
            if (system.touchDragged(x, y, pointer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        for (EntitySystem system : entitySystems) {
            if (system.mouseMoved(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(float deltaX, float deltaY) {
        for (EntitySystem system : entitySystems) {
            if (system.scrolled(deltaX, deltaY)) {
                return true;
            }
        }
        return false;
    }
}
