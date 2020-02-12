package com.courtesypoint.apiresearch;

import java.io.IOException;
import java.util.Base64;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Oauth {
    public static void main( String[] args ) throws IOException, UnirestException {
    	String token = Oauth.getCmsResourceGrantPassword();
    }
    
    private static String getCmsResourceGrantPassword() {
    	String token = null; //CptiHttpRequest.getCmsResourceCredentials("Sample", "1234");
		String clientId = "91673408389-cd1djp1kbbkkm01i1c5sqcc4tiso7mp2.apps.googleusercontent.com";//clientId
	    String clientSecret = "bF19myL6EwWjdeFeIcrwDjQn";//client secret
	    String auth = clientId + ":" + clientSecret;
	    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
       
        HttpResponse<String> response;
        try {
            String url = "https://accounts.google.com/o/oauth2/token";
            response = Unirest.post(url)
                    .header("authorization", "Basic "+authentication)
                    .header("cache-control", "no-cache")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .field("grant_type", "authorization_code")
                    .field("client_id", clientId)
//                    .field("state", "5ca75bd30")
                    .field("redirect_uri", "http://localhost:8080")
                    .field("code", "4/wQENNdJVO6Fl6_p6npmmfGJ_4S-VQ_Kjt7mQZA_RVKDihhJV26mz49aowAij2U3lXIaiU4daYSKFck_wzy_ujj0")
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