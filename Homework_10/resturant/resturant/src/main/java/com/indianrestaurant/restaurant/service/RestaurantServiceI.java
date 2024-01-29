package com.indianrestaurant.restaurant.service;

import java.util.List;
import java.util.Optional;

import com.indianrestaurant.restaurant.model.Restaurant;

/**
 * 
 * @author Keerthika
 *
 */
public interface RestaurantServiceI {

	List<Restaurant> getMenu();

	Optional<Restaurant> getMenuById(Integer id);

	List<Restaurant> getMenyByType(String Type);

	Restaurant save(Restaurant resturant);

	List<Restaurant> SaveMany(List<Restaurant> resturant);

	Restaurant update(Integer id, Restaurant resturant);

	Boolean deleteById(int id);

    void deleteAll();

	long getCount();
	
	List<Restaurant> findByName(String name);
	
	//analysis
	List<Restaurant> filterByPrice(int price);
	//analysis
	List<Restaurant> filterByChefFavorite();

}
