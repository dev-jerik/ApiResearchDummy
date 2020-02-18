package com.courtesypoint.apiresearch.json;

import java.time.LocalDateTime;

public class JsonProfile {
	private Long id;
	private int version;
	private LocalDateTime createDate;
	private Long createdBy;
//	private LocalDateTime dateOfBirth;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
//	public LocalDateTime getDateOfBirth() {
//		return dateOfBirth;
//	}
//	public void setDateOfBirth(LocalDateTime dateOfBirth) {
//		this.dateOfBirth = dateOfBirth;
//	}
	
	@Override
	public String toString() {
		StringBuilder strbldr = new StringBuilder();
		strbldr.append("\nId:" + id);
		strbldr.append("\nVersion: " + version);
		strbldr.append("\nCreate Date: " + createDate);
		strbldr.append("\nCreated By: " + createdBy);
		
		return strbldr.toString();
	}
}