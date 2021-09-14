package com.NewBean;

import java.io.Serializable;


public class ProductBean implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	
    
	int code;
	String name;
	String description;
	double price;
	String category;
	String photo;
	
	


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public ProductBean() {
		code = -1;
		name = "";
		description = "";
		category="";
		photo="";
	}

	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public double getPrice() {
		
		return price;	
	}
	
	
	
	
	
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String categotry) {
		this.category = categotry;
	}
	
	


	

	
		
	
}
