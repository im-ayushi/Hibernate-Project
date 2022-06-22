package com.nagarro.assignmenthibernate.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

@Entity
@Table(name="tshirt")
public class Tshirt {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO);
	@Column(name="Id")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	
	@Column(name="Color")
	@ColumnTransformer(read = "UPPER(Color)")
	@Enumerated(EnumType.STRING)
	private Color color;
	
	@Column(name="Size")
	@Enumerated(EnumType.STRING)
	private Size size;
	
	@Column(name="Gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name="Availability")
	private String availability;

	@Column(name="Price")
	private double price;
	
	@Column(name="Rating")
	private double rating;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

}
