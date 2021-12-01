package com.ecommerce.admin.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.admin.model.Book;

public interface BookService  {

	Book save(Book book);
	
	public List<Book>findAll();
	
	public void deleteById(Integer id);
	
	public Book findById(Integer id);
	
	public Book update(Book book);
}
