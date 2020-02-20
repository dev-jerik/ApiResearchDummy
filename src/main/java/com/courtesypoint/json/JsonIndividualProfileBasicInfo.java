package com.courtesypoint.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonIndividualProfileBasicInfo {
	private Integer salutation;
	private String firstName;
	private String middleName;
	private String lastName;
	
	public Integer getSalutation() {
		return salutation;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setSalutation(Integer salutation) {
		this.salutation = salutation;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nSalutation: " + salutation);
		strbldr.append("\nFirst Name: " + firstName);
		strbldr.append("\nMiddle Name: " + middleName);
		strbldr.append("\nLast Name: " + lastName);
		return strbldr.toString();
	}
	
}
