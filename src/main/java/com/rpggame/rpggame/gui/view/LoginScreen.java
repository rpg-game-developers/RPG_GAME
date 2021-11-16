package com.rpggame.rpggame.gui.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rpggame.rpggame.gui.controller.LoginScreenController;

public class LoginScreen extends ScreenAdapter {

    private final Stage stage;
    private final Skin skin;
    private final TextureAtlas atlas;
    private final FitViewport viewport;
    private final BitmapFont font;
    private Table loginTable;
    private final LoginScreenController loginScreenController;

    private final float cw;
    private final float sw;
    private final float sh;
    private final float ch;

    private String username;
    private String password;
    private boolean passwordFieldBackspaceClicked;

    public LoginScreen(final SpriteBatch batch) {
        this.loginScreenController = new LoginScreenController();

        this.sw = Gdx.graphics.getWidth();
        this.sh = Gdx.graphics.getHeight();
        this.cw = sw * 0.7f;
        this.ch = sh * 0.5f;

        this.username = "";
        this.password = "";
        this.passwordFieldBackspaceClicked = false;

        this.viewport = new FitViewport(sw, sh);
        this.stage = new Stage(viewport, batch);
        this.atlas = new TextureAtlas("skins/default/uiskin.atlas");
        this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"), this.atlas);
        this.font = new BitmapFont(Gdx.files.internal("skins/default/default.fnt"));

        createAndShowLoginTable();
    }

    public void createAndShowLoginTable() {
        Label usernameLabel = new Label("Username: ", this.skin);
        usernameLabel.setFontScale(1.0f);

        Label passwordLabel = new Label("Password:", this.skin);
        passwordLabel.setFontScale(1.0f);

        TextField usernameText = new TextField("Username", this.skin);

        TextField passwordText = new TextField("Password", this.skin);
        passwordText.setTextFieldListener((textField, c) -> password = textField.getText());
        passwordText.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                // backspace is clicked so we want to clear the text field
                if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                    if(!passwordFieldBackspaceClicked) {
                        passwordFieldBackspaceClicked = true;
                        passwordText.setText("");
                    }
                }
            }
        });

        Button submitButton = new TextButton("Submit", this.skin);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                loginScreenController.submitLoginData();
            }
        });

        Button goToRegisterPageButton = new TextButton("Register Page", this.skin);
        goToRegisterPageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                loginScreenController.goToRegisterPage();
            }
        });

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

        this.loginTable.setSize(this.cw, this.ch);
        this.loginTable.setPosition((this.sw - this.cw) / 2.0f, (this.sh - this.ch) / 2.0f);
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
