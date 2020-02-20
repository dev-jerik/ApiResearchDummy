package com.courtesypoint.deserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import com.courtesypoint.core.admin.CptiConstants;
import com.courtesypoint.core.admin.CptiUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateTimeDeserializer extends StdDeserializer<LocalDateTime> {
	private static final long serialVersionUID = 1L;

	public DateTimeDeserializer() { 
        this(null);
    } 
 
    public DateTimeDeserializer(Class<LocalDateTime> t) {
        super(t);
    }
 
    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    	 String dateAsString = jp.getText();
    	 return CptiUtil.convertStringToDate(dateAsString, CptiConstants.FORMAT_DATETIME_CPTI);
    }
}
