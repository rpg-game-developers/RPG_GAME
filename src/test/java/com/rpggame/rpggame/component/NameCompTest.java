package com.rpggame.rpggame.component;

import com.google.gson.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NameCompTest {

    private List<String> fieldNames;
    private Field[] classFields;

    @Before
    public void init() {
        classFields = NameComp.class.getDeclaredFields();
        fieldNames = Arrays.stream(classFields).map(Field::getName).collect(Collectors.toList());
    }

    private void compareFieldValues(NameComp sut, @NotNull Map<String, Object> data, @NotNull Field field) {
        try {
            assertEquals(data.get(field.getName()).toString(), field.get(sut).toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void toJson__ValuesCorrect() {
        final NameComp sut = new NameComp("test");
        final JsonObject nameCompAsJson = sut.toJson();
        Map<String, Object> data = nameCompAsJson.keySet().stream()
                .collect(Collectors.toMap(e -> e, e-> nameCompAsJson.get(e).getAsString()));
        Arrays.stream(classFields)
                .peek(e-> e.setAccessible(true))
                .forEach(field -> compareFieldValues(sut, data, field));
    }

    @Test
    public void toJson__KeysCorrect() {
        final NameComp sut = new NameComp("test");
        final JsonObject nameCompAsJson = sut.toJson();
        fieldNames.forEach(e -> assertTrue(nameCompAsJson.keySet().contains(e)));
    }
}