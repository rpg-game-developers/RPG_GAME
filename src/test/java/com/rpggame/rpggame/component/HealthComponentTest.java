package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void toJson__ValuesCorrect() {
		final HealthComponent sut = new HealthComponent(10,10);
		final JsonObject healthAsJson = sut.toJson();
		assertEquals("health", healthAsJson.get("type").getAsString());
		assertEquals(10, healthAsJson.get("maxHealth").getAsInt());
		assertEquals(10, healthAsJson.get("currentHealth").getAsInt());
	}

	@Test
	public void toJson__KeysCorrect() {
		final HealthComponent sut = new HealthComponent(10,10);
		final JsonObject healthAsJson = sut.toJson();
		List<String> expectedKeys = Arrays.asList("type", "maxHealth", "currentHealth");
		for(String key : healthAsJson.keySet()) {
			assertTrue(expectedKeys.contains(key));
		}
	}

}