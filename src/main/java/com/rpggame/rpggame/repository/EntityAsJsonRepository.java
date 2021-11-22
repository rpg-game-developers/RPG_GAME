package com.rpggame.rpggame.repository;

import com.badlogic.gdx.Gdx;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.entity.Entity;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;

@NoArgsConstructor
public class EntityAsJsonRepository {

	public Entity loadEntityFromJson(String filePath) {
		JsonParser jsonParser = new JsonParser();
		Entity entity = new Entity();
		try {
			JsonObject entityToLoad = (JsonObject) jsonParser.parse(new FileReader(filePath));
			JsonArray components = entityToLoad.getAsJsonArray("components");
			if(!(components.size() > 0)) {
				return entity;
			} else {
				return entity;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JsonObject saveEntityAsJson(@NotNull Entity entity) {
		JsonObject entityAsJson = new JsonObject();
		entityAsJson.add("extends", new JsonArray());
		JsonArray entityComponents = new JsonArray();
		for(Component component : entity.getComponents(Component.class)) {
			entityComponents.add(component.toJson());
		}
		entityAsJson.add("components", entityComponents);
		JsonArray childrenJson = new JsonArray();
		for(Entity e : entity.getChildren()) {
			childrenJson.add(saveEntityAsJson(e));
		}
		entityAsJson.add("children", childrenJson);
		return entityAsJson;
	}

	public void writeToFile(@NotNull JsonObject json, String path, String fileName) {
		Gdx.files.local(String.format("%s/%s.json", path, fileName)).writeString(json.toString(), false);
	}
}
