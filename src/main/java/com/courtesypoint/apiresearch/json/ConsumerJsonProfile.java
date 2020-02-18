package com.courtesypoint.apiresearch.json;

import com.link.json.JsonProfile;

public class ConsumerJsonProfile extends JsonProfile{
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nId: " + getId());
		strbldr.append("\nVersion: " + getVersion());
		strbldr.append("\nCreate Date: " +getCreateDate());
		strbldr.append("\nProfile Number: " + getProfileNumber());
		strbldr.append("\nFullname: " + getFullName());
		strbldr.append("\nCategory: " + getCategory());
		strbldr.append("\nLevel: " + getLevel());
		strbldr.append("\nDate of Birth: " + getDateOfBirth());
		return strbldr.toString();
	}
}
