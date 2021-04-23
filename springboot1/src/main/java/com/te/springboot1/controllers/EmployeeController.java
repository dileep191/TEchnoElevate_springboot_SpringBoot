package com.te.springboot1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.springboot1.beans.EmployeeBean;
import com.te.springboot1.beans.EmployeeResponse;
import com.te.springboot1.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@GetMapping("/")
	public String getFirstPage() {
		return "TECHNOELEVATE";
	}
	
	@GetMapping(path = "/getemp",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmployeeResponse getEmp(int id) {
		EmployeeResponse response = new EmployeeResponse();
		EmployeeBean bean = service.getEmployee(id);
		if(bean!=null) {
			response.setStatuscode(200);
			response.setMsg("success");
			response.setDescription("details found for id :" + id);
			response.setBean(bean);
		}
		else {
			response.setStatuscode(404);
			response.setMsg("error");
			response.setDescription("details not found for id :" + id);
		}
		return response;
	}//end of getemp
	
	@GetMapping(path = "/getalldata",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmployeeResponse getalldata() {
		EmployeeResponse response = new EmployeeResponse();
		List<EmployeeBean> beans = service.getAllData();
		if(beans!=null) {
			response.setStatuscode(200);
			response.setMsg("success");
			response.setDescription("employee details found");
			response.setBeans(beans);
		}
		else {
			response.setStatuscode(404);
			response.setMsg("error");
			response.setDescription("no employees found");	
		}
		return response;
	}
	
	@PostMapping(path = "/add",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmployeeResponse addEmp(@RequestBody EmployeeBean bean) {
		EmployeeResponse response = new EmployeeResponse();
		if(service.addEmployee(bean)) {
			response.setStatuscode(200);
			response.setMsg("success");
			response.setDescription("employee added into records");
		}
		else {
			response.setStatuscode(404);
			response.setMsg("error");
			response.setDescription("problem occured");
		}
		return response;
	}
	
	@PutMapping(path = "/delete/{emp_id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_ATOM_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmployeeResponse delete(@PathVariable(name = "emp_id") int id) {
		EmployeeResponse response = new EmployeeResponse();
		if(service.deleteEmployee(id)) {
			response.setStatuscode(200);
			response.setMsg("success");
			response.setDescription("employee deleted from records");
		}
		else {
			response.setStatuscode(404);
			response.setMsg("error");
			response.setDescription("problem occured");
		}
		return response;
	}
	
	@PutMapping(path = "/update",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public EmployeeResponse updateEmp(@RequestBody EmployeeBean bean) {
		EmployeeResponse response = new EmployeeResponse();
		if(service.updateEmployee(bean)) {
			response.setStatuscode(200);
			response.setMsg("success");
			response.setDescription("employee details updated into records");
		}
		else {
			response.setStatuscode(404);
			response.setMsg("error");
			response.setDescription("problem occured");
		}
		return response;
	}
}
