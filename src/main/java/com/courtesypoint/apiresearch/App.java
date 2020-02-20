package com.courtesypoint.apiresearch;

import java.util.List;

import com.courtesypoint.json.JsonProfileComplete;

public class App {
	
    public static void main( String[] args ) {
    	// Use for log4j
//    	BasicConfigurator.configure();
    	
    	LinkApiOperation operation = new LinkApiOperation();
//    	System.out.println(operation.getProfile());
    	
    	String searchProfileNumber = "PR-1055";
    	System.out.println("List of Profiles");
    	List<JsonProfileComplete> profiles = operation.searchProfiles(searchProfileNumber);
    	for(JsonProfileComplete profile : profiles) {
    		System.out.println(profile);
    	}
    	operation.updateProfile(searchProfileNumber);
    }
 
}