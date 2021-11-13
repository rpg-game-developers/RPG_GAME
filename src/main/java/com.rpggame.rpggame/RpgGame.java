package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rpggame.rpggame.component.PositionComponent;
import com.rpggame.rpggame.component.SpriteComponent;
import com.rpggame.rpggame.component.VelocityComponent;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
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

		// create first entity
		entity = new Entity(entityWorld);
		entity.addComponent(new SpriteComponent(player));
		entity.addComponent(new PositionComponent(200, 150));
	}

	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		entityWorld.getSystem(PhysicsEntitySystem.class).applyPhysics();

		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			entity.removeComponent(VelocityComponent.class);
			entity.addComponent(new VelocityComponent(-1,0));
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			entity.removeComponent(VelocityComponent.class);
			entity.addComponent(new VelocityComponent(1,0));
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			entity.removeComponent(VelocityComponent.class);
			entity.addComponent(new VelocityComponent(0,1));
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			entity.removeComponent(VelocityComponent.class);
			entity.addComponent(new VelocityComponent(0,-1));
		}

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
