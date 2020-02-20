package com.courtesypoint.apiresearch.linkapiconsumer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.courtesypoint.apiresearch.json.google.JsonGoogleCalendar;
import com.courtesypoint.apiresearch.json.google.JsonGoogleCalendarItem;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;

public class GoogleApiHttpRequest {
	public static List<JsonGoogleCalendar> getCalendars(String googleAccessToken) {
		String url = "https://www.googleapis.com/calendar/v3/users/me/calendarList";
		JsonGoogleCalendar calendar = Unirest.get(url)
				  .queryString("access_token", googleAccessToken)
				  .asObject(JsonGoogleCalendar.class)
				  .getBody();
		
		System.out.println(calendar.getKind());
		for(JsonGoogleCalendarItem item : calendar.getItems()) {
			System.out.println();
			System.out.println("Id: " + item.getId());
			System.out.println("Summary: " + item.getSummary());
			System.out.println("Access Role: " + item.getAccessRole());
		}
		return null;
	}
	
	public static void main(String[] args) {
		try {
			String accessToken = LinkApiConnector.getLinkApiAccessToken("Sample", "1234");	
			String authorizationUrl = LinkApiConnector.getGoogleAuthorizationUrl(accessToken);
			
			String googleAccessToken = LinkApiConnector.getGoogleAccessToken(accessToken);
			
			if (googleAccessToken == null || googleAccessToken.isEmpty()) {
				Desktop d = Desktop.getDesktop();
	    	    d.browse(new URI(authorizationUrl));
			}
    	    
			do {
				googleAccessToken = LinkApiConnector.getGoogleAccessToken(accessToken);
			} while (googleAccessToken == null || googleAccessToken.isEmpty());
    	    
    	    GoogleApiHttpRequest.getCalendars(googleAccessToken);
		} catch (UnirestException | IOException | URISyntaxException e) {
			System.out.println("Connection refused: connect");
		}
	}
}
