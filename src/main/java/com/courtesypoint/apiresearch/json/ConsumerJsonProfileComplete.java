package com.courtesypoint.apiresearch.json;

import com.link.json.JsonProfileComplete;

public class ConsumerJsonProfileComplete extends JsonProfileComplete{
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nId: " + getProfile().getId());
		strbldr.append("\nProfile number: " + getProfile().getProfileNumber());
		strbldr.append("\nFullname: " + getProfile().getFullName());
		if (getCorporateProfileBasicInfo() != null) {
			strbldr.append("\nCorprate Name: " + getCorporateProfileBasicInfo().getCompanyName());
		}
		if (getIndividualProfileBasicInfo() != null) {
			strbldr.append("\nGender: " + getIndividualProfileBasicInfo().getGender());
		}
		if (getAddresses() != null && !getAddresses().isEmpty()) {
			strbldr.append("\nAddress: " + getAddresses().get(0).getAddress());
		}
		return strbldr.toString();
	}
}
