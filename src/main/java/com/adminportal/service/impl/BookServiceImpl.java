package com.adminportal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.entities.Book;
import com.adminportal.repository.BookRepository;
import com.adminportal.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public void remove(Book book) {
		bookRepository.delete(book); 
	}
	

	@Override
	public List<Book> findAll() { 
		return (List<Book>) bookRepository.findAll();
		//bookRepository.
	}

	@Override
	public Book findOne(Integer id) {
		Optional<Book> bookResponse = bookRepository.findById(id);
		Book book = bookResponse.get();
		return book;
	}

}
