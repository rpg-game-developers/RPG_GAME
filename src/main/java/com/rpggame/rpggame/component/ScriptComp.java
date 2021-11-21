package com.rpggame.rpggame.component;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpggame.rpggame.component.rendering.RenderingComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.scripting.ScriptManager;

import javax.script.Invocable;
import javax.script.ScriptException;

public class ScriptComp implements RenderingComp {

    Entity entity;

    public ScriptComp() {
    }

    public ScriptComp(Entity entity, String script) {
        this.entity = entity;
        ScriptManager manager = ScriptManager.getInstance();
        try {
            manager.engine.eval(script);
        } catch (ScriptException se) {
            System.out.println(se);
        }
    }

    @Override
    public void render(OrthographicCamera camera, SpriteBatch batch, double x, double y) {
        try {
            ScriptManager manager = ScriptManager.getInstance();
            Invocable inv = (Invocable) manager.engine;
            inv.invokeFunction("onRender", entity);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Component clone() {
        return new ScriptComp();
    }
}
