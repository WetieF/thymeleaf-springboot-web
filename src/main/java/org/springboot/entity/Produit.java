package org.springboot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produits")
public class Produit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	@Column(name = "name")
	@Size(min = 3, max = 50)
	@NotEmpty
	private String produitName;
	
	@Column(name = "madeIn")
	@NotEmpty(message = "Please enter the Country place.")
	private String madeIn;
	
	@Column(name = "price")
	@Min(value = 20_0)
    @Max(value = 200_000)
	private double price;

	public Produit() {
		super();
	}

	public Produit(String produitName, String madeIn, double price) {
		super();
		this.produitName = produitName;
		this.madeIn = madeIn;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduitName() {
		return produitName;
	}

	public void setProduitName(String produitName) {
		this.produitName = produitName;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
