package com.courtesypoint.apiresearch.mixin;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.link.serializer.DateSerializer;
import com.link.serializer.DateTimeSerializer;

@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public abstract class JsonProfileMixin {
	@JsonSerialize(using=DateTimeSerializer.class)
	private LocalDateTime createDate;
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateOfBirth();
	
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateCstmFld01();
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateCstmFld02();
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateCstmFld03();
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateCstmFld04();
	@JsonSerialize(using=DateSerializer.class)
	abstract LocalDateTime getDateCstmFld05();
}
