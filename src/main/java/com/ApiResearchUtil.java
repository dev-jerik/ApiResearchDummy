package com;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.courtesypoint.core.admin.CptiConstants;
import com.courtesypoint.core.admin.CptiUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ApiResearchUtil {
	public static <T> T convertJSONToPOJO(String json, Class<T> clazz) {
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			DateTimeFormatter formatter = CptiUtil.getDateTimeFormatter(CptiConstants.FORMAT_DATETIME_CPTI);

			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return LocalDateTime.parse(json.getAsString(), formatter);
			}
	
			
		}).registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
			DateTimeFormatter formatter = CptiUtil.getDateTimeFormatter(CptiConstants.FORMAT_DATE_CPTI);

			@Override
			public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return LocalDateTime.parse(json.getAsString(), formatter);
			}
	
			
		}).create();
		return gson.fromJson(json, clazz);
	}
}
