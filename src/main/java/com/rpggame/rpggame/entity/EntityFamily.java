package com.rpggame.rpggame.entity;

import com.rpggame.rpggame.component.Component;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EntityFamily {
    private List<Class<? extends Component>> types;

    public EntityFamily(Class<? extends Component> @NotNull ... types) {
        this.types = new ArrayList<>();
        this.types.addAll(Arrays.asList(types));
    }

    public List<Class<? extends Component>> getTypes() {
        return Collections.unmodifiableList(types);
    }

    /**
     * Check if the entity is part of this family,
     * by checking if it has all the components.
     *
     * @param entity  the entity to check
     * @return  true if the entity has all the components, otherwise false
     */
    public boolean isMember(Entity entity) {
        for (Class<?> type : types) {
            if(!entity.hasComponent(type)) {
                return false;
            }
        }
        return true;
    }
}
