package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class HealthComponent implements Component {

	@Setter
	private int maxHealth;
	private int currentHealth;

	public HealthComponent() {
		this(0,0);
	}

	public void damage(int damage) {
		if (this.currentHealth - damage <= 0) {
			this.currentHealth = 0;
		} else {
			this.currentHealth -= damage;
		}
	}

	public void heal(int health) {
		if(currentHealth + health > maxHealth) {
			this.currentHealth = maxHealth;
		} else {
			this.currentHealth += health;
		}
	}

	public void resetHealth() {
		this.currentHealth = this.maxHealth;
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
		return new HealthComponent(this.maxHealth, this.currentHealth);
	}
}
