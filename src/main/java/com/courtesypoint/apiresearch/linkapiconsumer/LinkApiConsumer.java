package com.courtesypoint.apiresearch.linkapiconsumer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class LinkApiConsumer {
	
	private static String linkApiUrl = "http://localhost:8080/link";
	
	public static String getGoogleAuthorizationUrl(String linkApiAccessToken) throws UnirestException {
		String url = linkApiUrl + "/api/google/getAuthorizationUrl";
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
		String url = linkApiUrl+ "/api/google/getAccesToken";
		String googleAccessToken = null;
        HttpResponse<String> response;
		try {
			response = Unirest.get(url)
					.queryString("access_token", linkApiAccessToken)
					.asString();
			if (response.getStatus() == 200) {
	        	googleAccessToken = response.getBody().toString();
	        }
		} catch (UnirestException e) {
			e.printStackTrace();
		}
        System.out.println("Google Access Token: " + googleAccessToken);
		return googleAccessToken;
	}
	
	public static String getLinkApiAccessToken(String username, String password) throws UnirestException {
		String url = linkApiUrl +"/oauth/token";
		String accessToken = connectWithGrantTypePassword(username, password, "cmsApp", url);
		System.out.println("Link API Access Token: " + accessToken);
		return accessToken;
	}
	
	private static String connectWithGrantTypePassword(String username, String password, String clientId, String url) throws UnirestException {
		HttpResponse<String> response;
	    String clientSecret = "";
	    String auth = clientId + ":" + clientSecret;
	    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
	    String token = null;
        
        response = Unirest.post(url)
                .header("Authorization", "Basic "+authentication)
                .header("Cache-Control", "no-cache")
                .field("grant_type", "password")
                .field("client_id", clientId)
                .field("username", username)
                .field("password", password)
                .asString();
        if (response.getStatus() == 200) {
        	token = JSON.parseObject(response.getBody()).get("access_token").toString();
        }
        
		return token;
	}
	
	public static void main(String[] args){
		String linkApiAccessToken = null;
		try {
			linkApiAccessToken = LinkApiConsumer.getLinkApiAccessToken("Sample", "1234");

			String authorizationUrl = LinkApiConsumer.getGoogleAuthorizationUrl(linkApiAccessToken);
			LinkApiConsumer.getGoogleAccessToken(linkApiAccessToken);
			
			Desktop d = Desktop.getDesktop();
    	    d.browse(new URI(authorizationUrl));
		} catch (UnirestException e) {
			e.printStackTrace();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
}
