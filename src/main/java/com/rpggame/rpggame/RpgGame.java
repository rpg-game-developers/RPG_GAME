package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rpggame.rpggame.component.input.PlayerControllerComp;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import com.rpggame.rpggame.system.CollisionSystem;
import com.rpggame.rpggame.system.InputSystem;
import com.rpggame.rpggame.system.PhysicsEntitySystem;
import com.rpggame.rpggame.system.RenderingEntitySystem;

public class RpgGame extends EntityApplicationAdapter {
	private Texture player;
	private Texture box;
	private SpriteBatch batch;

	private Entity entity;

	@Override
	public void create() {
		super.create();

		// load all assets
		player = new Texture(Gdx.files.internal("alienPink_round.png"));
		box = new Texture(Gdx.files.internal("boxCrate_double.png"));
		batch = new SpriteBatch();

		// create entity world
		entityWorld.addSystem(new RenderingEntitySystem());
		entityWorld.addSystem(new PhysicsEntitySystem());
		entityWorld.addSystem(new InputSystem());
		entityWorld.addSystem(new CollisionSystem());

		// create first entity
		entity = new Entity();
		entity.addComponent(new SpriteComp(player));
		entity.addComponent(new TransformComp(200, 150));
		entity.addComponent(new VelocityComp(0, 0));
		entity.addComponent(new PlayerControllerComp());
		entity.addComponent(new RectangleCollisionComp(70, 70));
		entityWorld.addEntity(entity);

		// create box
		Entity entity2 = new Entity();
		entity2.addComponent(new SpriteComp(box));
		entity2.addComponent(new TransformComp(400, 400));
		entity2.addComponent(new RectangleCollisionComp(128, 128));
		entityWorld.addEntity(entity2);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		entityWorld.getSystem(InputSystem.class).handleInput();
		entityWorld.getSystem(PhysicsEntitySystem.class).applyPhysics();

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		entityWorld.getSystem(RenderingEntitySystem.class).render(batch);
		batch.end();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		player.dispose();
		batch.dispose();
	}
}
