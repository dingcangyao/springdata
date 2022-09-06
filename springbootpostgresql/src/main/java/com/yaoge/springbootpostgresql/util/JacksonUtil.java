package com.yaoge.springbootpostgresql.util;/**
* create by yaoge
* 2022/9/5 17:54
*/
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Created by Valerii Sosliuk on 5/12/2017.
 */
@Slf4j
public class JacksonUtil {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
//
//    static {
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(TsKvEntry.class, new TsKvEntrySerializer());
//        module.addDeserializer(TsKvEntry.class, new TsKvEntryDeserializer());
//        org.thingsboard.common.util.JacksonUtil.OBJECT_MAPPER.registerModule(module);
//        OBJECT_MAPPER.registerModule(module);
//    }

    public static <T> T convertValue(Object fromValue, Class<T> toValueType) {
        try {
            return fromValue != null ? OBJECT_MAPPER.convertValue(fromValue, toValueType) : null;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The given object value: "
                    + fromValue + " cannot be converted to " + toValueType, e);
        }
    }

    public static <T> T fromTree(JsonNode jsonNode, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.treeToValue(jsonNode, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("The given JsonNode value: "
                    + jsonNode.toString() + " cannot be transformed to Object" + clazz.getName());
        }
    }

    public static <T> T fromString(String string, Class<T> clazz) {
        try {
            return string != null ? OBJECT_MAPPER.readValue(string, clazz) : null;
        } catch (IOException e) {
            throw new IllegalArgumentException("The given string value: "
                    + string + " cannot be transformed to Json object", e);
        }
    }

    public static <T> T fromJsonNode(JsonNode jsonNode, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.convertValue(jsonNode, type);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The given jsonNode value: "
                    + jsonNode.toString() + " cannot be transformed to type " + type.getType().toString());
        }
    }

    public static <T> T fromJsonNode(JsonNode jsonNode, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.convertValue(jsonNode, clazz);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("The given jsonNode value: "
                    + jsonNode.toString() + " cannot be transformed to type " + clazz.getTypeName());
        }
    }


    public static String toString(Object value) {
        try {
            return value != null ? OBJECT_MAPPER.writeValueAsString(value) : null;
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("The given Json object value: "
                    + value + " cannot be transformed to a String", e);
        }
    }

    public static JsonNode toJsonNode(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readTree(value);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static JsonNode toJsonNode(Object value) {
        if (value == null) {
            return null;
        }
        return OBJECT_MAPPER.valueToTree(value);
    }

    public static ObjectNode newObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    public static <T> T clone(T value) {
        return fromString(toString(value), (Class<T>) value.getClass());
    }

    public static <T> JsonNode valueToTree(T value) {
        return OBJECT_MAPPER.valueToTree(value);
    }

}