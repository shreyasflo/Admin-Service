package com.idexcel.adminservice.service;

import java.util.List;

import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.dto.LendersPatchDTO;
import com.idexcel.adminservice.entity.Lender;

public interface LenderService {

	public String createLender(LenderDTO lenderDTO);
	public Lender getLenderById(String lenderId);
	public List<Lender> getAllLenders();
	public void updateLender(Lender lender, String lenderId);
	public void deleteLenderById(String lenderId);
	void updateStatus(LendersPatchDTO lendersPatchDTO, String lenderId);
}
