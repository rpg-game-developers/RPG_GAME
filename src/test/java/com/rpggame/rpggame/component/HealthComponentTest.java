package com.rpggame.rpggame.component;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HealthComponentTest {

	@Test
	public void damage() {
		final HealthComponent sut = new HealthComponent(10, 10);
		sut.damage(5);
		assertEquals(5, sut.getCurrentHealth());
	}

	@Test
	public void damage__HealthBelowZero() {
		final HealthComponent sut = new HealthComponent(10, 10);
		sut.damage(15);
		assertEquals(0, sut.getCurrentHealth());
	}

	@Test
	public void heal() {
		final HealthComponent sut = new HealthComponent(10, 4);
		sut.heal(3);
		assertEquals(7, sut.getCurrentHealth());
	}

	@Test
	public void heal__HealOverMaxHealth() {
		final HealthComponent sut = new HealthComponent(10, 5);
		sut.heal(25);
		assertEquals(sut.getMaxHealth(), sut.getCurrentHealth());
	}

}