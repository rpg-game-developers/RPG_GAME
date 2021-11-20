package com.rpggame.rpggame.component.loot;

import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.entity.Entity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SingleItemLootTableCompTest {

	private Entity entity1;
	private Entity entity2;
	private Entity entity3;

	@Before
	public void init() {
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
}
