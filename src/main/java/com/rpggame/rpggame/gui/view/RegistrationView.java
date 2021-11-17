package com.rpggame.rpggame.gui.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class RegistrationView {

    private final Stage stage;
    private final Skin skin;
    private final TextureAtlas atlas;
    private final FitViewport viewport;
    private final BitmapFont font;

    private final float cw;
    private final float sw;
    private final float sh;
    private final float ch;

    public RegistrationView() {
        this.sw = Gdx.graphics.getWidth();
        this.sh = Gdx.graphics.getHeight();
        this.cw = this.sw * 0.7f;
        this.ch = this.sh * 0.5f;

        this.viewport = new FitViewport(this.sw, this.sh);
        this.stage = new Stage(viewport);
        this.atlas = new TextureAtlas("skins/default/uiskin.atlas");
        this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"), this.atlas);
        this.font = new BitmapFont(Gdx.files.internal("skins/default/default.fnt"));
    }

    public void show() {

    }

    public void render(float v) {

    }

    public void resize(int width, int height) {

    }

    public void hide() {

    }

    public void dispose() {

    }
}
