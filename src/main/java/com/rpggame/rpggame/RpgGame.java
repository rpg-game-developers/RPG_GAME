package com.rpggame.rpggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.HealthComp;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.component.ScriptComp;
import com.rpggame.rpggame.component.input.PlayerControllerComp;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.gui.controller.LoginScreen;
import com.rpggame.rpggame.repository.EntityAsJsonRepository;
import com.rpggame.rpggame.system.*;

public class RpgGame extends EntityApplicationAdapter {
	private LoginScreen loginScreen;

	private Entity entity;

	@Override
	public void create() {
		super.create();

		// create entity world
		entityWorld.addSystem(new RenderingSystem(sprites, camera, batch));
		entityWorld.addSystem(new PhysicsSystem());
		entityWorld.addSystem(new InputSystem());
		entityWorld.addSystem(new CollisionSystem());
		entityWorld.addSystem(new ScriptSystem());

		// create first entity
		entity = new Entity();
		entity.addComponent(new NameComp("Player"));
		entity.addComponent(new SpriteComp("alienPink_round.png"));
		entity.addComponent(new TransformComp(200, 150));
		entity.addComponent(new VelocityComp(0, 0));
		entity.addComponent(new PlayerControllerComp(4));
		entity.addComponent(new RectangleCollisionComp(70, 70));
		entity.addComponent(new HealthComp(10,10));
		entityWorld.getRoot().addChild(entity);

		// create box
		Entity entity2 = new Entity();
		entity2.addComponent(new NameComp("Box"));
		entity2.addComponent(new SpriteComp("boxCrate_double.png"));
		entity2.addComponent(new TransformComp(400, 400));
		entity2.addComponent(new RectangleCollisionComp(128, 128));
		entity2.addComponent(new ScriptComp("scripts/test.js"));
		entityWorld.getRoot().addChild(entity2);

		loginScreen = new LoginScreen();

		JsonObject json = EntityAsJsonRepository.loadJsonObjectFromFile("json/entity/test.json");
		Entity loadedEntity = EntityAsJsonRepository.loadEntity(json);
		loadedEntity.saveEntityAsJson("json/entity", "loaded");
	}

	@Override
	public void render() {
		super.render();
		ScreenUtils.clear(0, 0, 0.2f, 1);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		entityWorld.onRender();

//		loginScreen.show();
//		loginScreen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
		loginScreen.dispose();
	}
}
