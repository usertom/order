package com.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="userInfo",catalog="order")
public class UserInfo  implements java.io.Serializable {
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Excel(name = "名字")
	@Column(name="name")
	private String name;
	
	@Excel(name = "年龄")
	@Column(name="age")
	private String age;
	
	@Excel(name = "地区")
	@Column(name="address")
	private String address;

	@Excel(name = "工资")
	@Column(name="salary")
	private int salary;

	@Column(name="departmentId")
	private Integer departmentId;

	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
