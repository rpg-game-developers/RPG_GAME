package com.rpggame.rpggame.system;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.controller.ConnectedObserver;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;
import com.rpggame.rpggame.entity.EntityWorld;
import com.rpggame.rpggame.entity.events.AddComponentEvent;
import com.rpggame.rpggame.entity.events.RemoveComponentEvent;

import java.util.*;

public class EntitySystem implements InputProcessor {
    private final List<ConnectedObserver<?>> observers;
    private final EntityFamily family;
    private TreeSet<Entity> entities;
    private EntityWorld world;

    public EntitySystem(EntityFamily family) {
        this.family = family;
        this.observers = new ArrayList<>();
        this.entities = new TreeSet<>();
    }

    /**
     * This method should be overridden if you want to do something every frame.
     */
    public void onRender() {}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean scrolled(float deltaX, float deltaY) {
        return false;
    }

    /**
     * Get all the entities that are interesting for the current system.
     * The entities that are interesting are the ones that are part of the EntityFamily.
     *
     * @return  An unmodifiable list of all the interesting entities.
     */
    public TreeSet<Entity> getEntities() {
        return entities;
    }

    /**
     * Method that connects this system to an EntityWorld.
     * WARNING: Should normally only be called by EntityWorld.
     *
     * @param world  the world to connect it to.
     */
    public void connect(EntityWorld world) {
        this.world = world;

        // add entities
        for (Entity entity : world.getEntities()) {
            if(family.isMember(entity)) {
                entities.add(entity);
            }
        }

        // create observers
        for (Class<? extends Component> c : family.getTypes()) {
            ConnectedObserver<AddComponentEvent> addObserver = new ConnectedObserver<>() {
                @Override
                public void onNotify(AddComponentEvent e) {
                    if (family.isMember(e.getEntity())) {
                        entities.add(e.getEntity());
                    }
                }
            };
            observers.add(addObserver);
            world.subscribeAddComponent(c, addObserver);

            ConnectedObserver<RemoveComponentEvent> removeObserver = new ConnectedObserver<>() {
                @Override
                public void onNotify(RemoveComponentEvent e) {
                    if (family.isMember(e.getEntity())) {
                        entities.remove(e.getEntity());
                    }
                }
            };
            observers.add(removeObserver);
            world.subscribeRemoveComponent(c, removeObserver);
        }
    }

    /**
     * Get the world this EntitySystem is connected to.
     *
     * @return  The EntityWorld this EntitySystem is connected to.
     */
    public EntityWorld getWorld() {
        return this.world;
    }

    /**
     * Method called when a new entity is added to the world.
     *
     * @param entity  the entity that was recently added.
     */
    public void onNewEntityAdded(Entity entity) {
        if (family.isMember(entity)) {
            entities.add(entity);
        }
    }

    /**
     * Method called when an entity is removed from the world.
     *
     * @param entity  the entity that will be removed
     */
    public void onEntityRemoved(Entity entity) {
        if (family.isMember(entity)) {
            entities.remove(entity);
        }
    }

    /**
     * Method that should be called when wanting to destroy a system.
     * WARNING: Should normally only be called by EntityWorld.
     */
    public void dispose() {
        for (ConnectedObserver<?> observer : observers) {
            observer.unsubscribe();
        }

        this.world = null;
        this.entities = null;
    }
}
