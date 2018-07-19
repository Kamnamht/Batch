package com.airtel.merchant.scheduler.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * The Class JsonUtils provides utility methods to convert JSON to Entity and
 * vice-versa.
 */
public final class JsonUtils {

	/**
	 * Instantiates a new json utils.
	 */
	private JsonUtils() {
		super();
	}

	/**
	 * This method will take Json string and return Entity object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonTxt
	 *            the json txt
	 * @param resultClass
	 *            the result class
	 * @param objectMapper
	 *            the object mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T jsonToEntity(String jsonTxt, Class<T> resultClass, ObjectMapper objectMapper)
			throws IOException {
		T resultObject = objectMapper.readValue(jsonTxt, resultClass);
		if (resultObject == null) {
			throw new IllegalArgumentException("null resultObject after JSON to Object conversion");
		}
		return resultObject;
	}

	/**
	 * This method will take Object and return json.
	 *
	 * @param obj
	 *            the obj
	 * @param objectMapper
	 *            the object mapper
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String entityToJson(Object obj, ObjectMapper objectMapper) throws IOException {
		String jsonString;
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		jsonString = objectMapper.writeValueAsString(obj);
		return jsonString;
	}

}