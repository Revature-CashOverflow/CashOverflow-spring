package com.revature.helper;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestHelper {
	public static String asJsonString(final Object obj, ObjectMapper mapper) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static HashMap<String, Object> asJsonObject(String s, ObjectMapper mapper) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			map = mapper.readValue(s, new TypeReference<HashMap<String, Object>>() {
			});
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
