package com.rpggame.rpggame.gui.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.rpggame.rpggame.gui.model.LoginScreenModel;
import com.rpggame.rpggame.gui.view.LoginScreenView;

/**
 * Controller for the login screen.
 */
public class LoginScreen extends ScreenAdapter {

	private final LoginScreenView loginScreenView;
	private final LoginScreenModel loginScreenModel;

	public LoginScreen() {
		this.loginScreenView = new LoginScreenView();
		this.loginScreenModel = new LoginScreenModel();

		this.loginScreenView.getUsernameText().setTextFieldListener((textField, c) ->
				this.loginScreenModel.setUsername(textField.getText()));
		this.loginScreenView.getPasswordText().setTextFieldListener((textField, c) ->
				this.loginScreenModel.setPassword(textField.getText()));
	}

	/**
	 * Changes the screen to the registration form.
	 */
	public void goToRegisterPage() {


	}

	/**
	 * Submits the login data and checks if the user can log in with those credentials.
	 */
	public void submitLoginData() {

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
