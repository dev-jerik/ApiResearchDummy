package com.courtesypoint.apiresearch.linkapiconsumer;

import java.util.List;

import com.courtesypoint.apiresearch.ApiResearchConstants;
import com.courtesypoint.apiresearch.json.ConsumerJsonProfile;
import com.courtesypoint.apiresearch.json.ConsumerJsonProfileComplete;

import kong.unirest.GenericType;

//import com.link.json.JsonProfile;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class LinkApiHttpRequest {
	public static List<ConsumerJsonProfileComplete> searchProfile(String accessToken) {
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/search";
	
		List<ConsumerJsonProfileComplete> profiles = Unirest.get(url)
				  .queryString("access_token", accessToken)
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
	
	public static void updateProfile(ConsumerJsonProfileComplete profileCompleteInfo, String accessToken) {
		String url = LinkApiConnector.LINK_API_URL + "/api/profile/update";
		HttpResponse<?> response = Unirest.put(url)
						.queryString("access_token", accessToken)
						.body(profileCompleteInfo)
						.asEmpty();
		if (response.getStatus() == ApiResearchConstants.HTTP_CODE_OK) {
			System.out.println("Successfully updated.");
		} else {
			System.out.println("Encountered error upon updating the profile info.");
		}
	}
	
	public static void updateProfile(String accessToken) {
//		ConsumerJsonProfileComplete profile = getProfile(accessToken);
	}
	
	public static void main(String[] args){		
		String accessToken = null;
		try {
			accessToken = LinkApiConnector.getLinkApiAccessToken("Sample", "1234");
			ConsumerJsonProfile profile = LinkApiHttpRequest.getProfile(accessToken);
			System.out.println(profile);
			
			System.out.println("\nSearch Profiles result:");
			List<ConsumerJsonProfileComplete> profiles = LinkApiHttpRequest.searchProfile(accessToken);
			for (ConsumerJsonProfileComplete jsonProfile : profiles) {
				System.out.println(jsonProfile);
			}		
		} catch (UnirestException e) {
			System.out.println("Connection refused: connect");
		}
	}
}
