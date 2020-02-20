package com.courtesypoint.apiresearch.json.google;

import java.util.List;

public class JsonGoogleCalendar {
	private String kind;
	private List<JsonGoogleCalendarItem> items;

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public List<JsonGoogleCalendarItem> getItems() {
		return items;
	}

	public void setItems(List<JsonGoogleCalendarItem> items) {
		this.items = items;
	}
}
