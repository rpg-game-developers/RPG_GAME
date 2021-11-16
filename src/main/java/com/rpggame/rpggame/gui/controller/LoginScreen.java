package com.rpggame.rpggame.gui.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.rpggame.rpggame.gui.model.LoginScreenModel;
import com.rpggame.rpggame.gui.view.LoginScreenView;

/**
 * Controller for the login screen.
 */
public class LoginScreen extends ScreenAdapter {

	private final LoginScreenView loginScreenView;
	private final LoginScreenModel loginScreenModel;

	private boolean usernameFieldBackspaceClicked = false;
	private boolean passwordFieldBackspaceClicked = false;

	/**
	 * Initializes the view and the model for the login screen.
	 * Adds functionality to the login view actors.
	 */
	public LoginScreen() {
		this.loginScreenView = new LoginScreenView();
		this.loginScreenModel = new LoginScreenModel();

		this.loginScreenView.getUsernameText().setTextFieldListener((textField, c) ->
				this.loginScreenModel.setUsername(textField.getText()));
		this.loginScreenView.getUsernameText().addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
					if(!usernameFieldBackspaceClicked) {
						usernameFieldBackspaceClicked = true;
						loginScreenView.getUsernameText().setText("");
					}
				}
			}
		});

		this.loginScreenView.getPasswordText().setTextFieldListener((textField, c) ->
				this.loginScreenModel.setPassword(textField.getText()));
		this.loginScreenView.getPasswordText().addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				if(Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
					if(!passwordFieldBackspaceClicked) {
						passwordFieldBackspaceClicked = true;
						loginScreenView.getPasswordText().setText("");
					}
				}
			}
		});
	}

	@Override
	public void show() {
		this.loginScreenView.show();
	}

	@Override
	public void render(float v) {
		this.loginScreenView.render(v);
	}

	@Override
	public void resize(int width, int height) {
		this.loginScreenView.resize(width, height);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		this.loginScreenView.dispose();
	}
}
