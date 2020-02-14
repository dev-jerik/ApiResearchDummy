package com.courtesypoint.apiresearch;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Oauth {
    public static void main( String[] args ) throws IOException, UnirestException {
    	
//    	HttpResponse<String> response = Unirest.get("https://accounts.google.com/o/oauth2/auth?client_id=91673408389-cd1djp1kbbkkm01i1c5sqcc4tiso7mp2.apps.googleusercontent.com&response_type=code&state=5ca75bd30&redirect_uri=http://localhost:8080&scope=https://www.googleapis.com/auth/calendar")
//    			.asString();
    	Desktop d = Desktop.getDesktop();
    	try {
    	    d.browse(new URI("https://accounts.google.com/o/oauth2/auth?client_id=91673408389-cd1djp1kbbkkm01i1c5sqcc4tiso7mp2.apps.googleusercontent.com&response_type=code&state=5ca75bd30&redirect_uri=http://localhost:8080&scope=https://www.googleapis.com/auth/calendar"));
    	} catch (IOException | URISyntaxException e2) {
    	    e2.printStackTrace();
    	}
    	String token = Oauth.getAccessToken("authorizationcode");
    }
    
    private static String getAccessToken(String code) {
    	String token = null; //CptiHttpRequest.getCmsResourceCredentials("Sample", "1234");
		String clientId = "91673408389-cd1djp1kbbkkm01i1c5sqcc4tiso7mp2.apps.googleusercontent.com";//clientId
	    String clientSecret = "bF19myL6EwWjdeFeIcrwDjQn";//client secret
	    String auth = clientId + ":" + clientSecret;
	    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
       
        HttpResponse<String> response;
        try {
            String url = "https://accounts.google.com/o/oauth2/token";
            response = Unirest.post(url)
                    .header("Authorization", "Basic "+authentication)
                    .header("Cache-Control", "no-cache")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("grant_type", "authorization_code")
                    .field("client_id", clientId)
//                    .field("state", "5ca75bd30")
                    .field("redirect_uri", "http://localhost:8080")
                    .field("code", code)
                    .asString();
            if (response.getStatus() >= 200 && response.getStatus() < 400) {
            	token = JSON.parseObject(response.getBody()).get("access_token").toString();
            	System.out.println("access_token: " + token);
            	System.out.println("token_type: " + JSON.parseObject(response.getBody()).get("token_type"));
            	System.out.println("refresh_token: " + JSON.parseObject(response.getBody()).get("refresh_token"));
            	System.out.println("expires_in: " + JSON.parseObject(response.getBody()).get("expires_in"));
            	System.out.println("scope: " + JSON.parseObject(response.getBody()).get("scope"));
            } else {
            	System.out.println(response.getStatus());
            	System.out.println(response.getBody());
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return token;
    }
}