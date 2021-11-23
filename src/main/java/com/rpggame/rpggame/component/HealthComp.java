package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import com.rpggame.rpggame.entity.Entity;
import com.rpggame.rpggame.event.DeathEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HealthComp implements Component {

	private int maxHealth;
	private int currentHealth;

	public HealthComp() {
		this(0,0);
	}

	public void damage(Entity entity, int damage) {
		if (this.currentHealth - damage <= 0) {
			this.currentHealth = 0;
			entity.notify(new DeathEvent());
		} else {
			this.currentHealth -= damage;
		}
	}

	public void heal(Entity entity, int health) {
		if(currentHealth + health > maxHealth) {
			this.currentHealth = maxHealth;
		} else {
			this.currentHealth += health;
		}
	}

	public void resetHealth() {
		this.currentHealth = this.maxHealth;
	}

	public void drainHealth() {
		this.currentHealth = 0;
	}

	@Override
	public JsonObject toJson() {
		JsonObject healthAsJson = new JsonObject();
		healthAsJson.addProperty("type", this.getClass().getSimpleName());
		healthAsJson.addProperty("maxHealth", this.maxHealth);
		healthAsJson.addProperty("currentHealth", this.currentHealth);
		return healthAsJson;
	}

	@Override
	public Component clone() {
		return new HealthComp(this.maxHealth, this.currentHealth);
	}
}
