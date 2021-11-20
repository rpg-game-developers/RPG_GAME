package com.rpggame.rpggame.component;

import lombok.*;

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
	public Component clone() {
		return new HealthComponent(this.maxHealth, this.currentHealth);
	}
}
