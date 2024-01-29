package com.indianrestaurant.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Resturant")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column
	private String name;
	@Column
	private Size size;
	

	@Column
	private Type type;
	@Column
	private Integer price;
	@Column
	private boolean chefFavorite;

}
