package com.adminportal.service.impl;

import java.util.List;
import java.util.Optional; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.adminportal.entities.ReqBook;
import com.adminportal.repository.ReqBookRepository;
import com.adminportal.service.ReqBookService;

@Service
public class ReqBookServiceImpl implements ReqBookService {

	@Autowired
	private ReqBookRepository reqbookRepository;
	

	
	
	@Override
	public void remove(ReqBook reqbook) {
		reqbookRepository.delete(reqbook); 
	}
 

	@Override
	public ReqBook findOne(Integer id) {
		Optional<ReqBook> bookResponse = reqbookRepository.findById(id);
		ReqBook reqbook = bookResponse.get(); 
		return reqbook;
	}
	
	@Override
	public ReqBook findByReqUserId(Integer id) {
		Optional<ReqBook> bookResponse = reqbookRepository.findByreqUserid(id);
		ReqBook reqbook = bookResponse.get(); 
		return reqbook;
	}

	@Override
	public List<ReqBook> findAll() { 
		return (List<ReqBook>) reqbookRepository.findAll();
	}


	


	

	 

}
