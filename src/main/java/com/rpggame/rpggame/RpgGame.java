package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rpggame.rpggame.component.input.PlayerControllerComp;
import com.rpggame.rpggame.component.physics.PositionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import com.rpggame.rpggame.system.InputSystem;
import com.rpggame.rpggame.system.PhysicsEntitySystem;
import com.rpggame.rpggame.system.RenderingEntitySystem;

public class RpgGame extends ApplicationAdapter {
	private Texture player;
	private SpriteBatch batch;

	private EntityWorld entityWorld;
	private Entity entity;

	@Override
	public void create() {
		// load all assets
		player = new Texture(Gdx.files.internal("alienPink_round.png"));
		batch = new SpriteBatch();

		// create entity world
		entityWorld = new EntityWorld();
		entityWorld.addSystem(new RenderingEntitySystem());
		entityWorld.addSystem(new PhysicsEntitySystem());
		entityWorld.addSystem(new InputSystem());

		// create first entity
		entity = new Entity();
		entity.addComponent(new SpriteComp(player));
		entity.addComponent(new PositionComp(200, 150));
		entity.addComponent(new VelocityComp(0, 0));
		entity.addComponent(new PlayerControllerComp());
		entityWorld.addEntity(entity);
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		entityWorld.getSystem(InputSystem.class).handleInput();
		entityWorld.getSystem(PhysicsEntitySystem.class).applyPhysics();

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
