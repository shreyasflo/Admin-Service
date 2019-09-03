package com.idexcel.adminservice.dto;

import javax.validation.constraints.NotNull;

import com.idexcel.adminservice.entity.Address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LenderDTO{
	
	@NotNull
	private String name;
	
		
	private Address address;
	
	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

	public LenderDTO(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
		
}
