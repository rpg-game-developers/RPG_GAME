package com.rpggame.rpggame.component.loot;

import com.badlogic.gdx.utils.Json;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;

import java.util.*;

/**
 * Generates the loot from a single item loot table which looks something like this:
 * {
 * Entity : 1,
 * Entity : 5,
 * Entity : 10
 * }
 */
public class SingleItemLootTableComp implements LootTableComp {

	private final Map<Entity, Integer> table;

	public SingleItemLootTableComp() {
		this.table = new HashMap<>();
	}

	public SingleItemLootTableComp(final Map<Entity, Integer> table) {
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
		return new SingleItemLootTableComp(this.table);
	}

	@Override
	public JsonObject toJson() {
		JsonObject singleItemLootTableJson = new JsonObject();
		singleItemLootTableJson.addProperty("type", this.getClass().getSimpleName());
		JsonArray tableJson = new JsonArray();
		for(Map.Entry<Entity, Integer> entry : table.entrySet()) {
			JsonObject drop = new JsonObject();
			drop.addProperty("drop", "/"); // TODO: Change to file path of the entity
			drop.addProperty("rarity", entry.getValue());
			tableJson.add(drop);
		}
		singleItemLootTableJson.add("table", tableJson);
		System.out.println(singleItemLootTableJson);
		return singleItemLootTableJson;
	}
}
