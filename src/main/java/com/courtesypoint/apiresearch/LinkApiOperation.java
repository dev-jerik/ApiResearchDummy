package com.courtesypoint.apiresearch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

import com.courtesypoint.json.JsonProfile;
import com.courtesypoint.json.JsonProfileComplete;

public class LinkApiOperation {
	public static final String LINK_API_URL = "http://localhost:8080/link";
	private static OAuth2RestOperations restTemplate;
	
	public LinkApiOperation() {
		restTemplate = getRestTemplate();
		
		System.out.println("Operation: " + LinkApiOperation.class.getSimpleName());
		System.out.println("Access Token: " + restTemplate.getAccessToken());
	}
	
	public JsonProfile getProfile() {
		String url = LINK_API_URL + "/api/profile/get";
//    	ResponseEntity<String> strResponse = restTemplate.getForEntity(url, String.class);
//    	System.out.println(strResponse);

    	ResponseEntity<JsonProfile> response = restTemplate.getForEntity(url, JsonProfile.class);
    	return response.getBody();
	}
	
	public void updateProfile(String profileNumber) {
		String url = LINK_API_URL + "/api/profile/update";
    	List<JsonProfileComplete> profiles = searchProfiles(profileNumber);
    	JsonProfileComplete profile = profiles.get(0);
    	
    	profile.getIndividualProfileBasicInfo().setFirstName("Profile-"+LocalDateTime.now());
    	restTemplate.put(url, profile);
    	System.out.println(profile);
	}
	
	public List<JsonProfileComplete> searchProfiles(String profileNumber) {
		String url = LINK_API_URL + "/api/profile/search?profileNumber="+profileNumber;
    	List<JsonProfileComplete> profiles = exchangeAsList(url, new ParameterizedTypeReference<List<JsonProfileComplete>>() {});
		return profiles;
	}
	
	private <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
	    return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
	}
	
	private ResourceOwnerPasswordResourceDetails getOwnerPasswordResourceDetails() {
        String tokenUrl = LINK_API_URL + "/oauth/token";

    	ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId("cmsApp");
        resource.setGrantType("password");

        resource.setUsername("Sample");
        resource.setPassword("1234");
        
        return resource;
    }
	
	private OAuth2RestOperations getRestTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(getOwnerPasswordResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }
}
