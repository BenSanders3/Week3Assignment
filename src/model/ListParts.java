package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ben Sanders - bsanders3
 * CIS 175 Fall 2021
 */
@Entity
@Table(name="parts")
public class ListParts {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="PART")
	private String part;
	@Column(name="PRICE")
	private String price;
	
	
	public ListParts() {
		super();
	}
	
	
	public ListParts(String part, String price) {
		super();
		this.part = part;
		this.price = price;
	}
	public String returnPartDetails() {
		return this.part + ": " + this.price;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
