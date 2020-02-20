package com.courtesypoint.serializer;

import java.io.IOException;
import java.time.LocalDateTime;

import com.courtesypoint.core.admin.CptiConstants;
import com.courtesypoint.core.admin.CptiUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 1L;

	public DateTimeSerializer() { 
        this(LocalDateTime.class); 
    } 
 
    public DateTimeSerializer(Class<LocalDateTime> t) {
        super(t); 
    }
 
    @Override
    public void serialize(
    		LocalDateTime value, JsonGenerator gen, SerializerProvider arg2) 
      throws IOException, JsonProcessingException {
        gen.writeString(CptiUtil.convertDateToString(value, CptiConstants.FORMAT_DATETIME_CPTI));
    }
}
