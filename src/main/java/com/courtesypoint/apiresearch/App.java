package com.courtesypoint.apiresearch;

import java.io.IOException;
import java.util.Base64;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException, UnirestException {
    	String token = App.getCmsResourceGrantPassword();
    	
    	String getProfile = "http://localhost:8080/link/api/profile/get";
    	HttpResponse<String> response = getResponse(getProfile, token);
    	System.out.println(response.getBody());
    	String searchProfile = "http://localhost:8080/link/api/profile/search";
    	response = getResponse(searchProfile, token);
    	System.out.println(response.getBody());
    }
    
    private static HttpResponse<String> getResponse(String url, String token) throws UnirestException {
    	HttpResponse<String> response = Unirest.get(url)
    			.header("Authorization", "Bearer " + token)
    			.asString();
    	return response;
    }
	
    private static String getCmsResourceGrantPassword() {
    	String token = null; //CptiHttpRequest.getCmsResourceCredentials("Sample", "1234");
		String clientId = "cmsApp";//clientId
	    String clientSecret = "";//client secret
	    String auth = clientId + ":" + clientSecret;
	    String authentication = Base64.getEncoder().encodeToString(auth.getBytes());
       
        HttpResponse<JsonNode> response;
        try {
            String url = "http://localhost:8080/link/oauth/token";
            response = Unirest.post(url)
                    .header("authorization", "Basic "+authentication)
                    .header("cache-control", "no-cache")
                    .field("grant_type", "password")
                    .field("username", "Sample")
                    .field("password", "1234")
                    .asJson();
            if (response.getStatus() >= 200 && response.getStatus() < 400) {
            	JSONObject jsonObject = response.getBody().getObject();
            	token = jsonObject.get("access_token").toString();
            	System.out.println("access_token: " + token);
            	System.out.println("token_type: " + jsonObject.get("token_type"));
            	System.out.println("refresh_token: " + jsonObject.get("refresh_token"));
            	System.out.println("expires_in: " + jsonObject.get("expires_in"));
            	System.out.println("scope: " + jsonObject.get("scope"));
            } else {
            	System.out.println("username or password failed");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return token;
    }
}


//
//private static String getRedmineResourceCredentials(String userName, String password) {
//	Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");
//	String clientId = "38eba4610307518150794019e79d3f8a543c0e2e";//clientId
//    String clientSecret = "";//client secret
//    String tokenUrl = "http://161.202.176.21/redmine/projects/raffles-cms-membership.json";
//    String auth = clientId + ":" + clientSecret;
//    String authentication = "amdiZWx0cmFuOlN0cjNuZ3RoUmVkbWluZQ==";//Base64.getEncoder().encodeToString(auth.getBytes());
//    
//    String content = "grant_type=password&username=" + userName + "&password=" + password;
//    BufferedReader reader = null;
//    HttpURLConnection connection = null;
//    String returnValue = "";
//    try {
//        URL url = new URL(tokenUrl);
//        connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setDoOutput(true);
//        connection.setRequestProperty("Authorization", "Basic " + authentication);
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        connection.setRequestProperty("Accept", "application/json");
//        PrintStream os = new PrintStream(connection.getOutputStream());
//        os.print(content);
//        os.close();
//        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String line = null;
//        StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
//        while ((line = reader.readLine()) != null) {
//            out.append(line);
//        }
//        String response = out.toString();
//        Matcher matcher = pat.matcher(response);
//        if (matcher.matches() && matcher.groupCount() > 0) {
//            returnValue = matcher.group(1);
//        }
//    } catch (Exception e) {
//        System.out.println("Error : " + e.getMessage());
//    } finally {
//        if (reader != null) {
//            try {
//                reader.close();
//            } catch (IOException e) {
//            }
//        }
//        connection.disconnect();
//    }
//    return returnValue;
//}
//
//
//public static void MyGETRequest() throws IOException {
//    URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/1");
//    String readLine = null;
//    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
//    conection.setRequestMethod("GET");
//    conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
//    int responseCode = conection.getResponseCode();
//    if (responseCode == HttpURLConnection.HTTP_OK) {
//        BufferedReader in = new BufferedReader(
//            new InputStreamReader(conection.getInputStream()));
//        StringBuffer response = new StringBuffer();
//        while ((readLine = in .readLine()) != null) {
//            response.append(readLine);
//        } in .close();
//        // print result
//        System.out.println("JSON String Result " + response.toString());
//        //GetAndPost.POSTRequest(response.toString());
//    } else {
//        System.out.println("GET NOT WORKED");
//    }
//}
