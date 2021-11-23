package com.rpggame.rpggame.component.loot;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rpggame.rpggame.component.HealthComponent;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.entity.Entity;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SingleItemLootTableCompTest {

	private Entity entity1;
	private Entity entity2;
	private Entity entity3;

	private List<String> fieldNames;
	private Field[] classFields;

	@Before
	public void init() {
		classFields = SingleItemLootTableComp.class.getDeclaredFields();
		fieldNames = Arrays.stream(classFields).map(Field::getName).collect(Collectors.toList());

		entity1 = new Entity();
		entity1.addComponent(new NameComp("entity1"));

		entity2 = new Entity();
		entity2.addComponent(new NameComp("entity2"));

		entity3 = new Entity();
		entity3.addComponent(new NameComp("entity3"));
	}

	private void basicResultCheck(Entity[] result) {
		assertNotNull(result);
		assertEquals(1, result.length);
	}

	@Test
	public void generateLoot__NoMapPresent() {
		final SingleItemLootTableComp sut = new SingleItemLootTableComp();
		final Entity[] result = sut.generateLoot();
		basicResultCheck(result);
		assertNull(result[0]);
	}

	@Test
	public void generateLoot__SinglePossibleItem() {
		final Map<Entity, Integer> sampleTable = Map.of(this.entity1, 1, this.entity2, 5, this.entity3, 10);
		final SingleItemLootTableComp sut = new SingleItemLootTableComp(sampleTable);
		final Entity[] result = sut.generateLoot();
		basicResultCheck(result);
		assertNotNull(result[0]);
	}

	@Test
	public void generateLoot__NoPossibleItems() {
		final Map<Entity, Integer> sampleTable = Map.of(this.entity1, 0, this.entity2, 0, this.entity3, 0);
		final SingleItemLootTableComp sut = new SingleItemLootTableComp(sampleTable);
		final Entity[] result = sut.generateLoot();
		basicResultCheck(result);
		assertNull(result[0]);
	}

	@Test
	public void generateLoot__CorrectOccurrences() {
		final Map<Entity, Integer> sampleTable = Map.of(this.entity1, 1, this.entity2, 5, this.entity3, 10);
		final SingleItemLootTableComp sut = new SingleItemLootTableComp(sampleTable);
		final List<Entity> results = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			results.add(sut.generateLoot()[0]);
		}
		final int amountEntity1 = Collections.frequency(results, this.entity1);
		final int amountEntity2 = Collections.frequency(results, this.entity2);
		final int amountEntity3 = Collections.frequency(results, this.entity3);
		assertTrue(amountEntity3 > amountEntity2);
		assertTrue(amountEntity2 > amountEntity1);
	}

	@Test
	public void toJson__ValuesCorrect() {

	}

	@Test
	public void toJson__KeysCorrect() {
		final Map<Entity, Integer> sampleTable = Map.of(this.entity1, 1, this.entity2, 5, this.entity3, 10);
		final SingleItemLootTableComp sut = new SingleItemLootTableComp(sampleTable);
		JsonObject lootTableJson = sut.toJson();
		Arrays.stream(classFields).filter(e -> (e.getModifiers()& Modifier.TRANSIENT)==Modifier.TRANSIENT).forEach(e -> {
			assertTrue(lootTableJson.keySet().contains(e.toString()));
			JsonArray asJsonArray = lootTableJson.getAsJsonArray(e.toString());
			assertTrue(asJsonArray.size() > 0);
		});
		assertTrue(lootTableJson.keySet().contains(Constants.BACKEND.TYPE_STRING));
	}
}
