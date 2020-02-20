package com.courtesypoint.apiresearch.json.google;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonGoogleCalendarItem {
	private String id;
	private String summary;
	private String accessRole;
	public String getId() {
		return id;
	}
	public String getSummary() {
		return summary;
	}
	public String getAccessRole() {
		return accessRole;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setAccessRole(String accessRole) {
		this.accessRole = accessRole;
	}
}
