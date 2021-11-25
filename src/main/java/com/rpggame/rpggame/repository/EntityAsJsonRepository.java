package com.rpggame.rpggame.repository;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rpggame.rpggame.component.Component;
import com.rpggame.rpggame.component.HealthComp;
import com.rpggame.rpggame.component.NameComp;
import com.rpggame.rpggame.component.ScriptComp;
import com.rpggame.rpggame.component.input.PlayerControllerComp;
import com.rpggame.rpggame.component.loot.SingleItemLootTableComp;
import com.rpggame.rpggame.component.physics.TransformComp;
import com.rpggame.rpggame.component.physics.VelocityComp;
import com.rpggame.rpggame.component.physics.collision.RectangleCollisionComp;
import com.rpggame.rpggame.component.rendering.SpriteComp;
import com.rpggame.rpggame.component.rendering.TileMapComp;
import com.rpggame.rpggame.constants.Constants;
import com.rpggame.rpggame.entity.Entity;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileReader;

@NoArgsConstructor
public class EntityAsJsonRepository {

	public static @NotNull Entity loadEntity(@NotNull JsonObject jsonObject) {
		Entity mainEntity = new Entity();
		JsonArray components = jsonObject.getAsJsonArray("components");
		if(components.size() > 0) {
			for(JsonElement component : components) {
				JsonObject asJsonObject = component.getAsJsonObject();
				switch (asJsonObject.get(Constants.JSON_KEYS.TYPE_STRING).getAsString()) {
					case "NameComp" :
						NameComp nameComp = new NameComp(
								asJsonObject.get(Constants.JSON_KEYS.NAME_STRING).getAsString());
						mainEntity.addComponent(nameComp);
						break;
					case "ScriptComp" :
						ScriptComp scriptComp = new ScriptComp(
								asJsonObject.get(Constants.JSON_KEYS.SCRIPT_STRING).getAsString());
						mainEntity.addComponent(scriptComp);
						break;
					case "HealthComp" :
						HealthComp healthComp = new HealthComp(
								asJsonObject.get(Constants.JSON_KEYS.MAX_HEALTH_STRING).getAsInt(),
								asJsonObject.get(Constants.JSON_KEYS.CURRENT_HEALTH_STRING).getAsInt());
						mainEntity.addComponent(healthComp);
						break;
					case "TileMapComp" :
						TileMapComp tileMapComp = new TileMapComp();
						mainEntity.addComponent(tileMapComp);
						break;
					case "SpriteComp" :
						SpriteComp spriteComp = new SpriteComp(
								asJsonObject.get(Constants.JSON_KEYS.SPRITE_STRING).getAsString());
						mainEntity.addComponent(spriteComp);
						break;
					case "VelocityComp" :
						JsonArray velocities = asJsonObject.getAsJsonArray(Constants.JSON_KEYS.VELOCITY_STRING);
						int[] velocityArray = new int[2];
						for(int i=0; i<velocities.size(); i++) {
							velocityArray[i] = velocities.get(i).getAsInt();
						}
						VelocityComp velocityComp = new VelocityComp(
								velocityArray[0], velocityArray[1]
						);
						mainEntity.addComponent(velocityComp);
						break;
					case "TransformComp" :
						JsonArray transformPositions = asJsonObject.getAsJsonArray(Constants.JSON_KEYS.POSITION_STRING);
						JsonArray transformScales = asJsonObject.getAsJsonArray(Constants.JSON_KEYS.SCALE_STRING);
						float[] positions = new float[2];
						float[] scales = new float[2];
						for(int i=0; i<transformPositions.size(); i++) {
							positions[i] = transformPositions.get(i).getAsFloat();
						}
						for(int i=0; i<transformScales.size(); i++) {
							scales[i] = transformScales.get(i).getAsFloat();
						}
						Vector2 position = new Vector2(positions[0], positions[1]);
						Vector2 scale = new Vector2(scales[0], scales[1]);
						TransformComp transformComp = new TransformComp(
								position, scale, asJsonObject.get(Constants.JSON_KEYS.ROTATION_STRING).getAsFloat());
						mainEntity.addComponent(transformComp);
						break;
					case "RectangleCollisionComp" :
						JsonArray collisions = asJsonObject.getAsJsonArray(Constants.JSON_KEYS.RECTANGLE_SIZE_STRING);
						Float[] sizes = new Float[2];
						for(int i=0; i < collisions.size(); i++) {
							sizes[i] = collisions.get(i).getAsFloat();
						}
						Vector2 size = new Vector2(sizes[0], sizes[1]);
						RectangleCollisionComp rectangleCollisionComp = new RectangleCollisionComp(size);
						mainEntity.addComponent(rectangleCollisionComp);
						break;
					case "SingleItemLootTableComp" :
						SingleItemLootTableComp singleItemLootTableComp = new SingleItemLootTableComp();
						mainEntity.addComponent(singleItemLootTableComp);
						break;
					case "PlayerControllerComp" :
						PlayerControllerComp playerControllerComp = new PlayerControllerComp(
								asJsonObject.get(Constants.JSON_KEYS.SPEED_STRING).getAsFloat()
						);
						mainEntity.addComponent(playerControllerComp);
						break;
				}
			}
		}
		JsonArray children = jsonObject.getAsJsonArray("children");
		if(children.size()>0) {
			for(JsonElement child : children) {
				mainEntity.addChild(loadEntity(((JsonObject) child)));
			}
		}
		return mainEntity;
	}

	public static @Nullable JsonObject loadJsonObjectFromFile(String filePath) {
		JsonParser jsonParser = new JsonParser();
		try {
			return (JsonObject) jsonParser.parse(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static @NotNull JsonObject saveEntityAsJson(@NotNull Entity entity) {
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

	public static void writeToFile(@NotNull JsonObject json, String path, String fileName) {
		try {
			Gdx.files.local(String.format("%s/%s.json", path, fileName)).writeString(json.toString(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
