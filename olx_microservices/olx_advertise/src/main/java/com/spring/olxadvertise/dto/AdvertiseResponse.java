package com.spring.olxadvertise.dto;

import java.util.Date;

public class AdvertiseResponse {
	
	
	private int id;
	private double price;
	private String title;
	private String description;
	private String category;
	private String status;
	private java.util.Date createddate;
	private java.util.Date modifieddate;
	
	public AdvertiseResponse()
	{
		
	}
	
	public AdvertiseResponse(int id, double price, String title, String description, String category, String status,
			Date createddate, Date modifieddate) {
		super();
		this.id = id;
		this.price = price;
		this.title = title;
		this.description = description;
		this.category = category;
		this.status = status;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public java.util.Date getCreateddate() {
		return createddate;
	}
	public void setCreateddate(java.util.Date createddate) {
		this.createddate = createddate;
	}
	public java.util.Date getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(java.util.Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	

	@Override
	public String toString() {
		return "AdvertiseResponse [id=" + id + ", price=" + price + ", title=" + title + ", description=" + description
				+ ", category=" + category + ", status=" + status + ", createddate=" + createddate + ", modifieddate="
				+ modifieddate + "]";
	}
	

}
