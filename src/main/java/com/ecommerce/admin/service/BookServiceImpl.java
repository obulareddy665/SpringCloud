package com.ecommerce.admin.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.dao.BookDao;
import com.ecommerce.admin.model.Book;
import com.ecommerce.admin.model.BookNameNotFoundException;



@Service
public class BookServiceImpl  implements BookService{

	@Autowired
	private BookDao bookDao;
	@Override
	public Book save(Book book) {
	return bookDao.save(book);
		}
	
	public List<Book>findAll() {
	return	bookDao.findAll();
	}
	
	public void deleteById(Integer id) {
		bookDao.deleteById(id);
	}
	
	public Book findById(Integer id) {
		Book book=bookDao.findById(id).get();
		if(book.getTitle()==null) 
			throw new BookNameNotFoundException("Book Not Found With id");
		
	return bookDao.findById(id).get();
	}
	
	public Book update(Book book) {
		return bookDao.save(book);
		
	}

}
