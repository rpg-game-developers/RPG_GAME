package com.rpggame.rpggame.component;

public class HealthComponent implements Component {

	private int maxHealth;
	private int currentHealth;

	public HealthComponent() {
		this.maxHealth = 0;
		this.currentHealth = 0;
	}

	public HealthComponent(int maxHealth, int currentHealth) {
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
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

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	@Override
	public Component clone() {
		return new HealthComponent(this.maxHealth, this.currentHealth);
	}
}
