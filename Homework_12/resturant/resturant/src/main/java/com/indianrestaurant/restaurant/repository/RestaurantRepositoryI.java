package com.indianrestaurant.restaurant.repository;

import org.springframework.data.repository.CrudRepository;

import com.indianrestaurant.restaurant.model.Restaurant;

public interface RestaurantRepositoryI extends CrudRepository<Restaurant, Integer> {

}
