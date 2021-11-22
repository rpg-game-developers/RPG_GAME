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
        this.name = new String();
    }

    @Override
    public JsonObject toJson() {
        JsonObject nameCompAsJson = new JsonObject();
        nameCompAsJson.addProperty(Constants.BACKEND.TYPE_STRING, this.getClass().getSimpleName());
        nameCompAsJson.addProperty("name", this.name);
        return nameCompAsJson;
    }

    @Override
    public Component clone() {
        return new NameComp(name);
    }
}
