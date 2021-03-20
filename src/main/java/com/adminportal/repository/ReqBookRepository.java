package com.adminportal.repository;
 
import java.util.Optional;
 
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adminportal.entities.ReqBook;
  

 
 @Repository
public interface ReqBookRepository extends CrudRepository<ReqBook, Integer> {

	Optional<ReqBook> findByreqUserid(int id);
	
	 
}
