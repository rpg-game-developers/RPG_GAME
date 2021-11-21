package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameCompTest {

    @Test
    public void toJson__ValuesCorrect() {
        final NameComp sut = new NameComp("test");
        final JsonObject nameCompAsJson = sut.toJson();
        assertEquals(sut.getName(), nameCompAsJson.get("name").getAsString());
    }

    @Test
    public void toJson__KeysCorrect() {
        final NameComp sut = new NameComp("test");
        final JsonObject nameCompAsJson = sut.toJson();
        assertTrue(nameCompAsJson.keySet().contains("name"));
    }
}