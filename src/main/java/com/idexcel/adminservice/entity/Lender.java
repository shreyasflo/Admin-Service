package com.idexcel.adminservice.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.ToString;

@Document(collection = "lenders")
public class Lender {
	
	@Id
	private String id;
	private String name;
	private Address address;
	private String status;
	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;
	
	public Lender() {
		super();
	}
	
	public Lender(String name, Address address, String status, String createdBy, String createdDate, String updatedBy,
			String updatedDate) {
		super();
		this.name = name;
		this.address = address;
		this.status = status;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String nowAsISO) {
		this.createdDate = nowAsISO;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String nowAsISO) {
		this.updatedDate = nowAsISO;
	}

	
}