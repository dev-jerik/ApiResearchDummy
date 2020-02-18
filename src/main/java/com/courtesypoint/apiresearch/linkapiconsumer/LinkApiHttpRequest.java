package com.courtesypoint.apiresearch.linkapiconsumer;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import com.ApiResearchUtil;
import com.courtesypoint.apiresearch.json.JsonProfile;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class LinkApiHttpRequest {
	public static String searchProfile(String accessToken) {
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/search";
		
		HttpResponse<String> response = Unirest.get(url)
				.queryString("access_token", accessToken)
				.asString();
		
        return null;
	}
	
	public static JsonProfile getProfile(String accessToken) {
		String url = LinkApiConnector.LINK_API_URL+ "/api/profile/get";	
		
		HttpResponse<String> response = Unirest.get(url)
					.queryString("access_token", accessToken)
					.asString();
//					.asJson();
//                   .asObject(JsonProfile.class);
		JsonProfile profile = ApiResearchUtil.convertJSONToPOJO(response.getBody(), JsonProfile.class);
		
		System.out.println(profile);
		System.out.println(response.getStatus());
		System.out.println(response.getBody());
		
		return null;
	}
	
	public static DateTimeFormatter getDateTimeFormatter(String pattern) {
		DateTimeFormatter dtf = new DateTimeFormatterBuilder().appendPattern(pattern)
	            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
	            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
	            .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
	            .toFormatter();
		
		return dtf;
	}
	
	public static void main(String[] args){
//		com.fasterxml.jackson.databind.ObjectMapper mapper 
//	      = new com.fasterxml.jackson.databind.ObjectMapper();
//		mapper.registerModule(new JavaTimeModule());
//		Unirest.config().setObjectMapper(new ObjectMapper() {
//			 com.fasterxml.jackson.databind.ObjectMapper mapper 
//		      = new com.fasterxml.jackson.databind.ObjectMapper();
//			@Override
//			public String writeValue(Object value) {
//				try {
//					return mapper.writeValueAsString(value);
//				} catch (JsonProcessingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}
//			
//			@Override
//			public <T> T readValue(String value, Class<T> valueType) {
//				try {
//					return mapper.readValue(value, valueType);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}
//		});
		
		String accessToken = null;
		try {

			accessToken = LinkApiConnector.getLinkApiAccessToken("Sample", "1234");
			
			JsonProfile jsonProfile = LinkApiHttpRequest.getProfile(accessToken);
			System.out.println(jsonProfile);
			
		} catch (UnirestException e) {
			System.out.println("Connection refused: connect");
		}
	}
}
