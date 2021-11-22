package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import com.rpggame.rpggame.constants.Constants;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HealthComponentTest {

	private List<String> fieldNames;
	private Field[] classFields;

	@Before
	public void init() {
		classFields = HealthComponent.class.getDeclaredFields();
		fieldNames = Arrays.stream(classFields).map(Field::getName).collect(Collectors.toList());
	}

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

	private void compareFieldValues(HealthComponent sut, @NotNull Map<String, Object> data, @NotNull Field field) {
		try {
			assertEquals(data.get(field.getName()), field.get(sut).toString());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void toJson__ValuesCorrect() {
		final HealthComponent sut = new HealthComponent();
		final JsonObject healthAsJson = sut.toJson();
		Map<String, Object> data = healthAsJson.keySet().stream()
				.collect(Collectors.toMap(e -> e, e-> healthAsJson.get(e).getAsString()));
		Arrays.stream(classFields)
				.peek(e-> e.setAccessible(true))
				.forEach(field -> compareFieldValues(sut, data, field));
		assertEquals(healthAsJson.get(Constants.BACKEND.TYPE_STRING).getAsString(), HealthComponent.class.getSimpleName());
	}

	@Test
	public void toJson__KeysCorrect() {
		final HealthComponent sut = new HealthComponent();
		final JsonObject healthAsJson = sut.toJson();
		fieldNames.forEach(e -> assertTrue(healthAsJson.keySet().contains(e)));
		assertTrue(healthAsJson.keySet().contains(Constants.BACKEND.TYPE_STRING));
	}

}