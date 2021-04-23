package com.te.springboot1.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;

@Entity
@Data
@Table(name = "employee_info")
@JsonRootName("employee-info")
@JsonPropertyOrder({"id","name"})
@XmlRootElement(name = "employee-info")
public class EmployeeBean {

	@Id
	@JsonProperty("emp-id")
	private int id;
	
	@Column
	private String name;
	
	@Column(name = "dob")
	private Date birthdate;
	
	@Column
	private String password;
	
}
