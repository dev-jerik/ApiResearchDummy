package com.courtesypoint.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonProfileComplete {
	private JsonProfile profile;
	private JsonIndividualProfileBasicInfo individualProfileBasicInfo;
	
	public JsonProfile getProfile() {
		return profile;
	}
	public void setProfile(JsonProfile profile) {
		this.profile = profile;
	}

	public JsonIndividualProfileBasicInfo getIndividualProfileBasicInfo() {
		return individualProfileBasicInfo;
	}
	public void setIndividualProfileBasicInfo(JsonIndividualProfileBasicInfo individualProfileBasicInfo) {
		this.individualProfileBasicInfo = individualProfileBasicInfo;
	}
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nJsonProfile: " + getProfile());
		strbldr.append("\n\nIndividual Basic Info: " + getIndividualProfileBasicInfo());
		strbldr.append("\n==============================================================");
		return strbldr.toString();
	}
}
