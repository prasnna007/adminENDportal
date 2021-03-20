package com.adminportal.repository;

import org.springframework.data.repository.CrudRepository;

import com.adminportal.entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

	
}
