package com.courtesypoint.apiresearch.mixin;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.link.serializer.DateSerializer;

public class JsonIndividualProfileBasicInfoMixin {
	@JsonSerialize(using=DateSerializer.class)
	private LocalDateTime weddingDate;
}
