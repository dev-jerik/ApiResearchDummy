package com.courtesypoint.apiresearch.linkapiconsumer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class LinkApiConnector {
	
	public static final String LINK_API_URL = "http://localhost:8080/link";
	
	public static String getGoogleAuthorizationUrl(String linkApiAccessToken) throws UnirestException {
		String url = LINK_API_URL + "/api/google/getAuthorizationUrl";
		String authorizationUrl = null;
        HttpResponse<String> response = Unirest.get(url)
        		.queryString("access_token", linkApiAccessToken)
        		.asString();
        if (response.getStatus() == 200) {
        	authorizationUrl = response.getBody().toString();
        }
		return authorizationUrl;
	}
	
	public static String getGoogleAccessToken(String linkApiAccessToken) {
		String url = LINK_API_URL+ "/api/google/getAccesToken";
		String googleAccessToken = null;
		try {
			googleAccessToken = Unirest.get(url)
					.queryString("access_token", linkApiAccessToken)
					.asString()
					.mapBody(String::valueOf);
		} catch (UnirestException e) {
			e.printStackTrace();
		}
        System.out.println("Google Access Token: " + googleAccessToken);
		return googleAccessToken;
	}
	
	public static String getLinkApiAccessToken(String username, String password) throws UnirestException {
		String url = LINK_API_URL +"/oauth/token";
		String accessToken = connectWithGrantTypePassword(username, password, "cmsApp", "", url);
		System.out.println("Link API Access Token: " + accessToken);
		return accessToken;
	}
	
	private static String connectWithGrantTypePassword(String username, String password, String clientId, String clientSecret, String url) throws UnirestException {
		HttpResponse<JsonNode> response;
	    String token = null;
        
        response = Unirest.post(url)
                .header("Cache-Control", "no-cache")
                .basicAuth(clientId, clientSecret)
                .field("grant_type", "password")
                .field("client_id", clientId)
                .field("username", username)
                .field("password", password)
                .asJson();
        
        if (response.getStatus() == 200) {
        	token = (String) response.getBody().getObject().get("access_token");
        }
        
		return token;
	}
	
	
	//-------------------------------------------------  --------------------------//
	
	
	public static void main(String[] args){
		String linkApiAccessToken = null;
		try {
			linkApiAccessToken = LinkApiConnector.getLinkApiAccessToken("Sample", "1234");

			String authorizationUrl = LinkApiConnector.getGoogleAuthorizationUrl(linkApiAccessToken);
			LinkApiConnector.getGoogleAccessToken(linkApiAccessToken);
			
			Desktop d = Desktop.getDesktop();
    	    d.browse(new URI(authorizationUrl));
		} catch (UnirestException e) {
			e.printStackTrace();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
}
