package com.rpggame.rpggame.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LoginScreen extends ScreenAdapter {

    private final Stage stage;
    private final Skin skin;
    private final TextureAtlas atlas;
    private final FitViewport viewport;
    private final BitmapFont font;

    private final Table loginTable;

    public LoginScreen(final SpriteBatch batch) {
        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        float cw = sw * 0.7f;
        float ch = sh * 0.5f;

        this.viewport = new FitViewport(sw, sh);
        this.stage = new Stage(viewport, batch);
        this.atlas = new TextureAtlas("skins/default/uiskin.atlas");
        this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"), this.atlas);
        this.font = new BitmapFont(Gdx.files.internal("skins/default/default.fnt"));

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = this.font;
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = this.font;
        textFieldStyle.fontColor = Color.WHITE;

        Label usernameLabel = new Label("Username: ", this.skin);
        usernameLabel.setFontScale(1.0f);

        Label passwordLabel = new Label("Password:", this.skin);
        passwordLabel.setFontScale(1.0f);

        TextField usernameText = new TextField("Username", this.skin);

        TextField passwordText = new TextField("Password", this.skin);

        Button submitButton = new TextButton("Submit", this.skin);
        Button goToRegisterPageButton = new TextButton("Register", this.skin);

        this.loginTable = new Table(this.skin);
        this.loginTable.row();
        this.loginTable.add(usernameLabel);
        this.loginTable.add(usernameText);
        this.loginTable.row();
        this.loginTable.add(passwordLabel);
        this.loginTable.add(passwordText);
        this.loginTable.row();
        this.loginTable.add(submitButton);
        this.loginTable.add(goToRegisterPageButton);

        this.loginTable.setSize(cw,ch);
        this.loginTable.setPosition((sw - cw) / 2.0f, (sh - ch) / 2.0f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(loginTable);
    }

    @Override
    public void render(float v) {
        this.stage.act(v);
        this.stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i, i1);
    }


    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        this.font.dispose();
        this.skin.dispose();
        this.atlas.dispose();
    }
}
