package com.courtesypoint.apiresearch.mapper;

import com.courtesypoint.apiresearch.mixin.JsonIndividualProfileBasicInfoMixin;
import com.courtesypoint.apiresearch.mixin.JsonProfileMixin;
import com.courtesypoint.core.membership.hibernate.domain.ProfileAddress;
import com.courtesypoint.core.membership.hibernate.domain.ProfileContactNumber;
import com.courtesypoint.core.membership.hibernate.domain.ProfileEmail;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.link.json.JsonIndividualProfileBasicInfo;
import com.link.json.JsonProfile;
import com.link.mixin.ProfileAddressMixIn;
import com.link.mixin.ProfileContactNumberMixIn;
import com.link.mixin.ProfileEmailMixIn;

public class JsonProfileCplteObjectMapper{
	public static ObjectMapper getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(JsonProfile.class, JsonProfileMixin.class);
		mapper.addMixIn(JsonIndividualProfileBasicInfo.class, JsonIndividualProfileBasicInfoMixin.class);
		mapper.addMixIn(ProfileAddress.class, ProfileAddressMixIn.class);
		mapper.addMixIn(ProfileContactNumber.class, ProfileContactNumberMixIn.class);
		mapper.addMixIn(ProfileEmail.class, ProfileEmailMixIn.class);
		return mapper;
	}
}
