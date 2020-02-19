package com.courtesypoint.apiresearch.json;

import java.util.LinkedHashMap;
import java.util.Map;

import com.courtesypoint.core.membership.MembershipConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.link.json.JsonProfile;
import com.link.json.JsonProfileComplete;

public class ConsumerJsonProfileComplete extends JsonProfileComplete{
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nId: " + getProfile().getId());
		strbldr.append("\nCreate Date: " + getProfile().getCreateDate());
		strbldr.append("\nProfile number: " + getProfile().getProfileNumber());
		if (getCorporateProfileBasicInfo() != null) {
			strbldr.append("\nFullname: " + getCorporateProfileBasicInfo().getFirstName());
			strbldr.append("\nCorprate Name: " + getCorporateProfileBasicInfo().getCompanyName());
		}
		if (getIndividualProfileBasicInfo() != null) {
			strbldr.append("\nFullname: " + getIndividualProfileBasicInfo().getFirstName());

			strbldr.append("\nGender: " + getIndividualProfileBasicInfo().getGender());
		}
		if (getAddresses() != null && !getAddresses().isEmpty()) {
			strbldr.append("\nAddress: " + getAddresses().get(0).getAddress());
		}
		strbldr.append("\nDateCstmFld1: " + getProfile().getDateCstmFld01());
	
		return strbldr.toString();
	}
	
	
	public Map<String, Object> convertJsonProfileCplteToMap() throws JsonProcessingException {
		Map<String, Object> map = new LinkedHashMap<>();
		JsonProfile profile = getProfile();
		map.put("profile", profile);
		
		if(profile.getCategory().equals(MembershipConstants.PROFILE_CATEGORY_INDIVIDUAL)) {
			map.put("individualProfileBasicInfo", getIndividualProfileBasicInfo());
		} else if(profile.getCategory().equals(MembershipConstants.PROFILE_CATEGORY_CORPORATE)) {
			map.put("corporateProfileBasicInfo", getCorporateProfileBasicInfo());
		}
		
		map.put("addresses", getAddresses());
		map.put("contactNumbers", getContactNumbers());
		map.put("emails", getEmails());
		
		return map;
	}
}
