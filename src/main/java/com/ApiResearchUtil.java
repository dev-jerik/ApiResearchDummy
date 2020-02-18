package com;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ApiResearchUtil {
	public static <T> T convertJSONToPOJO(String json, Class<T> clazz) {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			DateTimeFormatter formatter = getDateTimeFormatter("MM/dd/yyyy HH:mm:ss");

			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return LocalDateTime.parse(json.getAsString(), formatter);
			}
	
			
		}).create();
		return gson.fromJson(json, clazz);
	}
	
	private static DateTimeFormatter getDateTimeFormatter(String pattern) {
		DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(pattern)
	            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
	            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
	            .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
	            .toFormatter();
		
		return dtf;
	}
}
