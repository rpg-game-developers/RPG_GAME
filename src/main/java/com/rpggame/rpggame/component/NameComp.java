package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import com.rpggame.rpggame.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NameComp implements Component {

    private String name;

    public NameComp() {
        this.name = "";
    }

    @Override
    public JsonObject toJson() {
        JsonObject nameCompAsJson = new JsonObject();
        nameCompAsJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());
        nameCompAsJson.addProperty(Constants.JSON_KEYS.NAME_STRING, this.name);
        return nameCompAsJson;
    }

    @Override
    public Component clone() {
        return new NameComp(name);
    }
}
