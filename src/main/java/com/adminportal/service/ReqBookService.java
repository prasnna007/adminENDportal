package com.adminportal.service;

import java.util.List;
 

import com.adminportal.entities.ReqBook;
 

public interface ReqBookService {
 
 

	ReqBook findOne(Integer id);

	void remove(ReqBook book);
 
	
	List<ReqBook> findAll();

	ReqBook findByReqUserId(Integer id);

}
