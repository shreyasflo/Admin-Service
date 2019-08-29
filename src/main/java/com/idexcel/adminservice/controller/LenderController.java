package com.idexcel.adminservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.dto.LendersPatchDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderServiceImpl;

@RestController
@RequestMapping("/api/lenders")
public class LenderController {

	@Autowired
	private LenderServiceImpl lenderServiceImpl;
	
	@PostMapping()
	public ResponseEntity<Object> addLender(@RequestBody LenderDTO lenderDTO) {
		String lenderId = lenderServiceImpl.createLender(lenderDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{lenderId}").buildAndExpand(lenderId).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping()
	public List<Lender> getAllLenders(){
		return this.lenderServiceImpl.getAllLenders();
		
	}
	@GetMapping("/{lenderId}")
	public Lender getLenderById(@PathVariable String lenderId) {
		Lender lender =  this.lenderServiceImpl.getLenderById(lenderId);
		return lender;
	}
	@PutMapping("/{lenderId}")
	public void updateLender(@PathVariable String lenderId,@RequestBody Lender lender) {
		this.lenderServiceImpl.updateLender(lender,lenderId);
	}
	@DeleteMapping("/{lenderId}")
	public void deleteLenderById(@PathVariable String lenderId) {
		this.lenderServiceImpl.deleteLenderById(lenderId);
	}
	
	@PatchMapping("/{lenderId}/status")
	public void updateStatus(@RequestBody LendersPatchDTO lendersPatchDTO,@PathVariable String lenderId) {
		this.lenderServiceImpl.updateStatus(lendersPatchDTO, lenderId);
	}	
	@RequestMapping(value="/{lenderId}", method=RequestMethod.HEAD)
	public ResponseEntity<String> returnHeader(@PathVariable String lenderId){
		HttpHeaders responseHeader = new HttpHeaders();
		responseHeader.set("Admin-Service-Header","Contains the Lender Information");
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Header Information of Admin Service", responseHeader,HttpStatus.OK);
		
		if(lenderServiceImpl.checkLender(lenderId)) 
			return responseEntity;
		return responseEntity;
	}
}
