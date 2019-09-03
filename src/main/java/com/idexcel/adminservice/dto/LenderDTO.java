package com.idexcel.adminservice.dto;

import javax.validation.constraints.NotNull;

import com.idexcel.adminservice.entity.Address;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class LenderDTO{
	
	@NotNull
	private String name;
	
	@NotNull
	private Address address;
	
	

	public LenderDTO(@NotNull String name, @NotNull Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}
	
	public Address getAddress() {
		return address;
	}

}
	
		

