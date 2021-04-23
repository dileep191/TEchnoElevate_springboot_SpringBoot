package com.te.springboot1.beans;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("employee_response")
@XmlRootElement(name = "empployee_response")
@Data
public class EmployeeResponse implements Serializable {

	private int statuscode;
	
	private String msg;
	
	private String description;
	
	@JsonProperty("employee_info")
	private EmployeeBean bean;
	
	@JsonProperty("employees details")
	private List<EmployeeBean> beans;
	
	
}
