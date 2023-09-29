package com.example;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="category_tb")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int cat_id;
	private String cat_name;
	private int cat_stocks;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Product> product = new ArrayList<Product>();

	public Category(int cat_id, String cat_name, int cat_stocks, List<Product> product) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.cat_stocks = cat_stocks;
		this.product = product;
	}

	public Category() {
		super();
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public int getCat_stocks() {
		return cat_stocks;
	}

	public void setCat_stocks(int cat_stocks) {
		this.cat_stocks = cat_stocks;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Category [cat_id=" + cat_id + ", cat_name=" + cat_name + ", cat_stocks=" + cat_stocks + ", product="
				+ product + "]";
	}
	
	
}
