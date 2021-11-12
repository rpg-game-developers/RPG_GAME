package com.rpggame.rpggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.rpggame.rpggame.component.SpriteComponent;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.entity.EntityWorld;
import com.rpggame.rpggame.system.RenderingSystem;

public class RpgGame extends ApplicationAdapter {
	private Texture player;
	private EntityWorld entityWorld;
	private SpriteBatch batch;

	@Override
	public void create () {
		// load all assets
		player = new Texture(Gdx.files.internal("alienPink_round.png"));

		// create first entity
		entityWorld = new EntityWorld();
		entityWorld.addSystem(new RenderingSystem());

		Entity entity = new Entity(entityWorld);
		batch = new SpriteBatch();


		entity.addComponent(new SpriteComponent());
		SpriteComponent sprite = entity.getComponent(SpriteComponent.class);
		sprite.setSprite(player);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0.2f, 1);

		batch.begin();
		entityWorld.getSystem(RenderingSystem.class).render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
	}
}
