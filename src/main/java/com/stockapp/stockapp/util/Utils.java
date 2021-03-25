package com.stockapp.stockapp.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

	public static final String PREFIX = "Bearer ";
	
	public static final ObjectMapper mapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static void validarJson(Object objeto) {
		try {
			// get Oraganisation object as a json string
			String jsonStr = mapper.writeValueAsString(objeto);

			// Displaying JSON String
			System.out.println(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String objectToJSON(Object objeto) {
		try {
			return mapper.writeValueAsString(objeto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}

}
