package com.rpggame.rpggame.component;

import com.badlogic.gdx.Gdx;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.scripting.ScriptManager;
import lombok.Getter;

import javax.script.Invocable;
import javax.script.ScriptEngine;

public class ScriptComp implements Component {

    @Getter
    private String filePath;
    public transient ScriptEngine engine;

    public ScriptComp() {
        this.filePath = null;
        this.engine = null;
    }

    public ScriptComp(String filePath) {
        setFilePath(filePath);
    }

    public void setFilePath(String filePath) {
        if (!filePath.equals(this.filePath)) {
            this.filePath = filePath;
            ScriptManager manager = ScriptManager.getInstance();
            try {
                String script = Gdx.files.internal(filePath).readString();
                this.engine = manager.createJavaScriptEngine();
                this.engine.eval(script);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void update(Entity entity) {
        try {
            Invocable inv = (Invocable) this.engine;
            inv.invokeFunction("update", entity);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public JsonObject toJson() {
        JsonObject scriptCompAsJson = new JsonObject();
        scriptCompAsJson.addProperty(Constants.JSON_KEYS.TYPE_STRING, this.getClass().getSimpleName());
        scriptCompAsJson.addProperty(Constants.JSON_KEYS.SCRIPT_STRING, this.filePath);
        return scriptCompAsJson;
    }

    @Override
    public Component clone() {
        return new ScriptComp(this.filePath);
    }
}
