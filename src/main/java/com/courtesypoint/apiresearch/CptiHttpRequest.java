package com.courtesypoint.apiresearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Resource Owner Password Credentials Grant Type
// Reference: https://developer.byu.edu/docs/consume-api/use-api/oauth-20/oauth-20-java-sample-code	
public class CptiHttpRequest {
	public static String getCmsResourceCredentials(String userName, String password) {
		Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
		String clientId = "cmsApp";//clientId
	    String clientSecret = "";//client secret
	    String tokenUrl = "http://localhost:8080/link/oauth/token";
	    String auth = clientId + ":" + clientSecret;
	    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
	    
	    String content = "grant_type=password&username=" + userName + "&password=" + password;
	    BufferedReader reader = null;
	    HttpURLConnection connection = null;
	    String returnValue = "";
	    try {
	        URL url = new URL(tokenUrl);
	        connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Authorization", "Basic " + authentication);
//	        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//	        connection.setRequestProperty("Accept", "application/json");
	        PrintStream os = new PrintStream(connection.getOutputStream());
	        os.print(content);
	        os.close();
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line = null;
	        StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        String response = out.toString();
	        System.out.println("Raw Oauth Response: " + response);
	        Matcher matcher = pat.matcher(response);
	        if (matcher.matches() && matcher.groupCount() > 0) {
	            returnValue = matcher.group(1);
	        }
	    } catch (Exception e) {
	        System.out.println("Error : " + e.getMessage());
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e) {
	            }
	        }
	        connection.disconnect();
	    }
	    return returnValue;
	}
	
	public static String getProfile(String bearerToken) {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL("http://localhost:8080/link/api/profile/get");
	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
	        connection.setDoOutput(true);
	        connection.setRequestMethod("GET");
	        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line = null;
	        StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	        String response = out.toString();
	        return response;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	
}
