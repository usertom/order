package com.order.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="food",catalog="order")
public class Food  implements java.io.Serializable {
	@Id//Ö÷¼ü
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="url")
	private String url;
	@Column(name="content")
	private String content;
	@Column(name="cost")
	private double cost;
	@Column(name="day")
	private String day;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}
