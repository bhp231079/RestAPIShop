package com.mvc.exception;

import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mvc.model.CustomErrorDetails;

@ControllerAdvice
public class RestAPIExceptionHandler extends ResponseEntityExceptionHandler {

	private static final long serialVersionUID = 1L;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails("From   MethodArgumentNotValid in GEH",
				new Date(), ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetails, status.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails("From   RequestMethodNotSupported in GEH",
				new Date(), ex.getMessage());

		return new ResponseEntity<Object>(customErrorDetails, status.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler({ObjectNotFoundException.class})
	public  ResponseEntity<Object> handleUserNotFoundException(ObjectNotFoundException ex) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(), new Date(), null);

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public  ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(ex.getMessage(), new Date(), null);

		return new ResponseEntity<Object>(customErrorDetails, HttpStatus.NOT_FOUND);
	}

}
