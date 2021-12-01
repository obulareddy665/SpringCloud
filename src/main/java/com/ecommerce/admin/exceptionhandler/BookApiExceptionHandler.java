package com.ecommerce.admin.exceptionhandler;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.annotation.HandlesTypes;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ecommerce.admin.ErrorResponse;
import com.ecommerce.admin.model.BookNameNotFoundException;

@ControllerAdvice
public class BookApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	HandlerExceptionResolver in;
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<ErrorResponse> errorMessages=ex.getBindingResult().getFieldErrors().stream().
		map(err->new ErrorResponse(err.getField(),err.getDefaultMessage()))
		.collect(Collectors.toList());
		
		return new ResponseEntity<>(errorMessages, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<?> nosuchElementException(NoSuchElementException exception){
		ErrorResponse error=new ErrorResponse("error", exception.getLocalizedMessage());
		
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(BookNameNotFoundException.class)
	public ResponseEntity<?> bookNameNotFoundException(BookNameNotFoundException exception){
		ErrorResponse error=new ErrorResponse("error", exception.getLocalizedMessage());
		
		return new ResponseEntity<ErrorResponse>(error,HttpStatus.NOT_FOUND);
		
	}

}
