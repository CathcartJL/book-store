package com.jcathcart.bookstore.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    private JsonUtil() { }

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String serialize(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

}
