package com.courtesypoint.apiresearch.linkapiconsumer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.courtesypoint.apiresearch.ApiResearchConstants;
import com.courtesypoint.apiresearch.json.ConsumerJsonProfile;
import com.courtesypoint.apiresearch.json.ConsumerJsonProfileComplete;
import com.courtesypoint.apiresearch.mapper.JsonProfileCplteObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kong.unirest.GenericType;

//import com.link.json.JsonProfile;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class LinkApiHttpRequest {
	public static List<ConsumerJsonProfileComplete> searchProfile(String accessToken, String searchProfileNumber) {
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/search";
	
		List<ConsumerJsonProfileComplete> profiles = Unirest.get(url)
				  .queryString("access_token", accessToken)
				  .queryString("profileNumber", searchProfileNumber)
				  .asObject(new GenericType<List<ConsumerJsonProfileComplete>>(){})
				  .getBody();
		
		return profiles;
	}
	
	public static ConsumerJsonProfile getProfile(String accessToken) {
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/get";	
		
		HttpResponse<ConsumerJsonProfile> response = Unirest.get(url)
					.queryString("access_token", accessToken)
                   .asObject(ConsumerJsonProfile.class);
		
		if (response.getStatus() == ApiResearchConstants.HTTP_CODE_OK) {
			return response.getBody();
		}
		return null;
	}
	
	
	public static void updateProfile(String accessToken, String profileNumber) {
		ConsumerJsonProfileComplete jsonProfileComplete = searchProfile(accessToken, profileNumber).get(0);
		jsonProfileComplete.getIndividualProfileBasicInfo().setFirstName("Profile-"+LocalDateTime.now().getMinute());
		
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/update";	

		try {
			ObjectMapper mapper = JsonProfileCplteObjectMapper.getObjectMapper();
			Map<String, Object> jsonMap = jsonProfileComplete.convertJsonProfileCplteToMap();
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
			Unirest.put(url)
					.header("Content-Type", "application/json")
					.queryString("access_token", accessToken)
					.body(json)
					.asEmpty();
				
			System.out.println(jsonProfileComplete);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){		
		String accessToken = null;
		try {
			accessToken = LinkApiConnector.getLinkApiAccessToken("Sample", "1234");
//			ConsumerJsonProfile profile = LinkApiHttpRequest.getProfile(accessToken);
//			System.out.println(profile);
			
//			System.out.println("\nSearch Profiles result:");
			String profileNumber = "M00001-00-00";
			List<ConsumerJsonProfileComplete> profiles = LinkApiHttpRequest.searchProfile(accessToken, profileNumber);
			for (ConsumerJsonProfileComplete jsonProfile : profiles) {
				System.out.println(jsonProfile);
			}
			
			LinkApiHttpRequest.updateProfile(accessToken, profileNumber);
					
		} catch (UnirestException e) {
			System.out.println("Connection refused: connect");
		}
	}
}
