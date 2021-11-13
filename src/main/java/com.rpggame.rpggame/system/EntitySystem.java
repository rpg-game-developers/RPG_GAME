package com.rpggame.rpggame.system;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.controller.ConnectedObserver;
import com.rpggame.rpggame.controller.Observer;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityFamily;
import com.rpggame.rpggame.entity.EntityWorld;
import com.rpggame.rpggame.entity.events.AddComponentEvent;
import com.rpggame.rpggame.entity.events.RemoveComponentEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EntitySystem {
    public List<Entity> entities;
    private List<ConnectedObserver<?>> observers;
    private EntityFamily family;

    public EntitySystem(EntityFamily family) {
        this.family = family;
        this.observers = new ArrayList<>();
        entities = new ArrayList<>();
    }

    /**
     * Method that connects this system to an EntityWorld.
     * WARNING: Should normally only be called by EntityWorld.
     *
     * @param world  the world to connect it to.
     */
    public void connect(EntityWorld world) {
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
     * Method that should be called when wanting to destroy a system.
     * WARNING: Should normally only be called by EntityWorld.
     */
    public void dispose() {
        for (ConnectedObserver<?> observer : observers) {
            observer.unsubscribe();
        }

        this.entities = null;
    }
}
