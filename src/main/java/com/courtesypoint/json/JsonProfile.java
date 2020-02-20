package com.courtesypoint.json;

import java.time.LocalDateTime;

import com.courtesypoint.deserializer.DateTimeDeserializer;
import com.courtesypoint.serializer.DateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonProfile {
	private String id;
	private String version;
	@JsonDeserialize(using = DateTimeDeserializer.class)
	@JsonSerialize(using=DateTimeSerializer.class)
	private LocalDateTime createDate;
	private String profileNumber;
	
	public String getId() {
		return id;
	}
	public String getVersion() {
		return version;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public String getProfileNumber() {
		return profileNumber;
	}
	public void setProfileNumber(String profileNumber) {
		this.profileNumber = profileNumber;
	}
	
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nId: " + id);
		strbldr.append("\nVersion: " + version);
		strbldr.append("\nCreate Date: " + createDate);
		strbldr.append("\nProfile Number: " + profileNumber);
		return strbldr.toString();
	}
}
