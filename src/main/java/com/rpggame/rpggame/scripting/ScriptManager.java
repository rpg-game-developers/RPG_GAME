package com.rpggame.rpggame.scripting;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptManager {
    private static ScriptManager INSTANCE;
    public ScriptEngineManager manager;
    public ScriptEngine engine;

    private ScriptManager() {
        manager = new ScriptEngineManager();
        engine = manager.getEngineByName("JavaScript");
    }

    public static ScriptManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScriptManager();
        }
        return INSTANCE;
    }

}
