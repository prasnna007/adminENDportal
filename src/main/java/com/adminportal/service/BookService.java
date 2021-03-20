package com.adminportal.service;

import java.util.List;

import com.adminportal.entities.Book;
 

public interface BookService {

	Book save(Book book);

	List<Book> findAll();

	Book findOne(Integer id);

	void remove(Book book);
}
