package com.rpggame.rpggame.gui.controller;

import com.badlogic.gdx.ScreenAdapter;
import com.rpggame.rpggame.gui.view.RegistrationView;

public class RegistrationScreen extends ScreenAdapter {

    private final RegistrationView registrationView;

    public RegistrationScreen() {
        this.registrationView = new RegistrationView();
    }

    @Override
    public void show() {
        registrationView.show();
    }

    @Override
    public void render(float v) {
        registrationView.render(v);
    }

    @Override
    public void resize(int width, int height) {
        registrationView.resize(width, height);
    }

    @Override
    public void hide() {
        registrationView.hide();
    }

    @Override
    public void dispose() {
        registrationView.dispose();
    }
}
