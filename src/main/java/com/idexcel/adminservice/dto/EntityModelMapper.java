package com.idexcel.adminservice.dto;

import org.modelmapper.ModelMapper;

import com.idexcel.adminservice.entity.Lender;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @ToString @Setter 
public class EntityModelMapper {

	ModelMapper modelMapper = new ModelMapper();
	
	public LenderDTO transformToDTO(Lender lender) {
		LenderDTO lenderDTO = modelMapper.map(lender,LenderDTO.class);
		return lenderDTO;
	}
	
	public Lender transformBackToClass(LenderDTO lenderDTO) {
		Lender lender = modelMapper.map(lenderDTO,Lender.class);
		return lender;
	}

}



