package com.rpggame.rpggame.component.loot;

import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Generates the loot from a single item loot table which looks something like this:
 * {
 * Entity : 1,
 * Entity : 5,
 * Entity : 10
 * }
 */
public class SingleItemLootTable implements LootTable {

	private final Map<Entity, Integer> table;

	public SingleItemLootTable() {
		this.table = new HashMap<>();
	}

	public SingleItemLootTable(final Map<Entity, Integer> table) {
		this.table = table;
	}

	@Override
	public Entity[] generateLoot() {
		Entity[] loot = new Entity[1];
		int totalSum = this.table.values().stream().mapToInt(e -> e).sum();
		if (totalSum == 0) {
			return loot;
		}
		int count = new Random().nextInt(totalSum) + 1;
		while (count > 0) {
			for (Map.Entry<Entity, Integer> entry : table.entrySet()) {
				count -= entry.getValue();
				if (count <= 0) {
					loot[0] = entry.getKey();
					break;
				}
			}
		}
		return loot;
	}

	@Override
	public Component clone() {
		return new SingleItemLootTable(this.table);
	}
}
