package com.indianrestaurant.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
