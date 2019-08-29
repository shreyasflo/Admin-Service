package com.idexcel.adminservice.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.idexcel.adminservice.dao.LenderRepository;
import com.idexcel.adminservice.dto.EntityModelMapper;
import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.dto.LendersPatchDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.exception.LenderAlreadyExistsException;
import com.idexcel.adminservice.exception.LenderNotFoundException;

@Service
public class LenderServiceImpl implements LenderService {

	
	@Autowired
	private LenderRepository lenderRepository;
	
	
	EntityModelMapper theModelMapper = new EntityModelMapper();
	//create a new lender
	
	@Override
	public String createLender(LenderDTO lenderDTO) {
		
		String name = lenderDTO.getName();
		
		System.out.println(name);
		
		if(lenderRepository.findByName(name)==null) {
			Lender lender = theModelMapper.transformBackToClass(lenderDTO);
			lender.setStatus("Active");
			lender.setCreatedBy("Shreyas R");
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
			dateFormat.setTimeZone(tz);
			String nowAsISO = dateFormat.format(new Date());
			lender.setCreatedDate(nowAsISO);
			lender.setUpdatedBy("Shreyas R");
			lender.setUpdatedDate(nowAsISO);
			
			lenderRepository.insert(lender);
			return lender.getId();
		}
		else
			throw new LenderAlreadyExistsException("the lender with the name specified already exists "+name);
		
	}

	//get an existing lender by Id
	@Override
	public Lender getLenderById(String lenderId) {
		return this.lenderRepository.findById(lenderId).orElse(null);
	}

	//get all lenders 
	@Override
	public List<Lender> getAllLenders() {
		List<Lender> lenders = this.lenderRepository.findAll(Sort.by(Direction.DESC, "createdDate"));
		if(lenders == null)
			return Collections.emptyList();
		return lenders;
	}

	//update a lender
	@Override
	public void updateLender(Lender lender,String lenderId) {
		this.lenderRepository.save(lender);
	}

	
	//delete a lender 
	@Override
	public void deleteLenderById(String lenderId) {
		this.lenderRepository.deleteById(lenderId);

	}

	//update lender status 
	@Override
	public void updateStatus(LendersPatchDTO lendersPatchDTO, String lenderId) {
		if(lenderRepository.existsById(lenderId)) {
			Lender lender = this.lenderRepository.findById(lenderId).orElse(null);
			lender.setStatus(lendersPatchDTO.getStatus());
			this.lenderRepository.save(lender);
		}
		else
			throw new LenderNotFoundException("The lender with the Id "+lenderId+" does not exist");
		
	}
	public boolean checkLender(String lenderId) {
		if(lenderRepository.existsById(lenderId))
			return true;
		else 
			throw new LenderNotFoundException("The lender with the Id "+lenderId+" does not exist");
	}

}
