package com.idexcel.adminservice.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.idexcel.adminservice.entity.Lender;

public interface LenderRepository extends MongoRepository<Lender, String> {

	public Lender findByName(String name);
}
