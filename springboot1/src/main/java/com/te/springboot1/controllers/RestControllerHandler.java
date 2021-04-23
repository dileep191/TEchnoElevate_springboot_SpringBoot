package com.te.springboot1.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.springboot1.beans.EmployeeResponse;
import com.te.springboot1.customexceptions.EmployeeException;

@RestControllerAdvice
public class RestControllerHandler {

	@ExceptionHandler(EmployeeException.class)
	public EmployeeResponse employeeExp(EmployeeException exception) {
		EmployeeResponse response = new EmployeeResponse();
		response.setStatuscode(500);
		response.setMsg(exception.getMessage());
		return response;
	}
}
