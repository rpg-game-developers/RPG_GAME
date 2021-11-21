package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;

/**
 * All components should implement this interface
 */
public interface Component {
    Component clone();

    JsonObject toJson();
}
