package com.te.springboot1.customexceptions;

public class EmployeeException extends RuntimeException {

	public EmployeeException(String msg) {
		super(msg);
	}
	
}
