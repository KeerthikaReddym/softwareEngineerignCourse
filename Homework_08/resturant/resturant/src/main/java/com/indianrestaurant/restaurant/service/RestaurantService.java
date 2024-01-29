package com.indianrestaurant.restaurant.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indianrestaurant.restaurant.model.Restaurant;
import com.indianrestaurant.restaurant.repository.RestaurantRepositoryI;

/**
 * The `ResturantService` class provides the business logic and data
 * manipulation operations for restaurant menu items. It implements the
 * `ResturantServiceI` interface and interacts with the underlying data through
 * the `ResturantRepositoryI`.
 *
 * @author Keerthika
 */
@Service
public class RestaurantService implements RestaurantServiceI {

	@Autowired
	RestaurantRepositoryI restaurantRepositoryI;

	/**
	 * This method is used to return a list of all the menu present in the database
	 * 
	 * @return list of resturant table
	 */
	@Override
	public List<Restaurant> getMenu() {

		return (List<Restaurant>) restaurantRepositoryI.findAll();
	}

	/**
	 * This method is used to return one record of the menu based on the user
	 * request id
	 * 
	 * @param id The unique identifier of the menu item.
	 * @return resturant data
	 */
	@Override
	public Optional<Restaurant> getMenuById(Integer id) {
		Optional<Restaurant> restu = restaurantRepositoryI.findById(id);
		return restu;
	}

	/**
	 * This method is used to return a list of menu of mentioned type from the user
	 * 
	 * @param Type
	 * @return list<resturant>
	 */
	@Override
	public List<Restaurant> getMenyByType(String Type) {
		List<Restaurant> menu = new ArrayList<Restaurant>();
		List<Restaurant> resturantMenu = getMenu();
		// added loop to add all the records which have the type taken from user, if it
		// matches, then add it to a list and return it.
		for (Restaurant restaurant : resturantMenu) {
			if (Type.equals(restaurant.getType().toString())) {
				menu.add(restaurant);
			}
		}
		return menu;
	}

	/**
	 * This method is used to save one menu item into the database
	 * 
	 * @param resturant
	 * @return resturant data
	 */
	@Override
	public Restaurant save(Restaurant resturant) {

		if (!checkRecordIfAlreadyPresent(resturant))
		 return restaurantRepositoryI.save(resturant);
		else
			return null;
	}

	/**
	 * This method is used to save many records into the database
	 * 
	 * @param resturant
	 * @return list of all saved menu
	 */
	@Override
	public List<Restaurant> SaveMany(List<Restaurant> resturant) {
		// restaurantRepositoryI.saveAll(resturant);
		List<Restaurant> uniqueList = checkRecordsIfAlreadyPresent(resturant);
		if (uniqueList.isEmpty())
			return null;
		else
			restaurantRepositoryI.saveAll(uniqueList);

		return (List<Restaurant>) restaurantRepositoryI.findAll();
	}

	/**
	 * This method is used to update data based on id
	 * 
	 * @param id
	 * @param resturant
	 * @return updated record
	 */
	@Transactional
	@Override
	public Restaurant update(Integer id, Restaurant restaurant) {
		// added condition to check if id exist or not.
		// resturant.setId(id);
		Restaurant existingRestaurant = restaurantRepositoryI.findById(id).orElse(null);
		if (existingRestaurant != null) {
			if (restaurant.getName() != null) {
				existingRestaurant.setName(restaurant.getName());
			}
			if (restaurant.getPrice() != null) {
				existingRestaurant.setPrice(restaurant.getPrice());
			}
			if (restaurant.getSize() != null) {
				existingRestaurant.setSize(restaurant.getSize());
			}
			if (restaurant.getType() != null) {
				existingRestaurant.setType(restaurant.getType());
			}
			existingRestaurant = restaurantRepositoryI.save(existingRestaurant);
		}
		return existingRestaurant;

	}

	/**
	 * This method is used to get the menu which matches the name with the user
	 * request
	 * 
	 * @param name
	 * @return List<Resturant>
	 */
	@Override
	public List<Restaurant> findByName(String name) {
		List<Restaurant> menu = new ArrayList<Restaurant>();
		List<Restaurant> resturantMenu = getMenu();
		// added loop to add all the records which have the name taken from user, if it
		// matches, then add it to a list and return it.
		for (Restaurant restaurant : resturantMenu) {
			if (name.equals(restaurant.getName().toString())) {
				menu.add(restaurant);
			}
		}
		return menu;
	}

	/**
	 * This method is used to get the menu which is below or equal to the price sent
	 * in the request
	 * 
	 * @param price
	 * @return List<Restaurant>
	 */
	@Override
	public List<Restaurant> filterByPrice(int price) {
		List<Restaurant> menu = new ArrayList<Restaurant>();
		List<Restaurant> resturantMenu = getMenu();
		// added loop to add all the menu items which are less than or equal to the
		// price mentioned by the user and return it
		for (Restaurant restaurant : resturantMenu) {
			if (restaurant.getPrice() <= price) {
				menu.add(restaurant);
			}
		}
		return menu;

	}

	/**
	 * This method is used to get the chef favorite menu
	 * 
	 * @return List<Restaurant>
	 */
	@Override
	public List<Restaurant> filterByChefFavorite() {
		List<Restaurant> menu = new ArrayList<Restaurant>();
		List<Restaurant> resturantMenu = getMenu();
		// added loop to add all the records which have the chef favorite field as true
		for (Restaurant restaurant : resturantMenu) {
			if (restaurant.isChefFavorite()) {
				menu.add(restaurant);
			}
		}
		return menu;

	}

	/**
	 * This method is used to delete a based on id
	 * 
	 * @param id The unique identifier of the menu item.
	 * @return true when it deletes a record or else false
	 */
	@Transactional
	@Override
	public Boolean deleteById(int id) {

		restaurantRepositoryI.deleteById(id);
		return restaurantRepositoryI.existsById(id);

	}

	/**
	 * This method is used to delete all the records in the database
	 * 
	 * @param resturant
	 * @return true when it deletes a all records in the database or it returns
	 *         false
	 */
	@Transactional
	@Override
	public void deleteAll() {
		restaurantRepositoryI.deleteAll();
	}

	/**
	 * This method is used to return count of records present in database
	 * 
	 * @return count of total number of records in the database
	 */
	@Override
	public long getCount() {

		return restaurantRepositoryI.count();
	}

	private boolean checkRecordIfAlreadyPresent(Restaurant resturant) {
		List<Restaurant> menu = (List<Restaurant>) restaurantRepositoryI.findAll();
		for (Restaurant singleMenu : menu) {
			if (singleMenu.getName().equals(resturant.getName()) && singleMenu.getSize().equals(resturant.getSize())) {
				return true;
			}
		}
		return false;
	}

	

	private List<Restaurant> checkRecordsIfAlreadyPresent(List<Restaurant> resturant) {
		List<Restaurant> uniqueList = new ArrayList<Restaurant>();
		uniqueList.addAll(resturant);
		List<Restaurant> menu = (List<Restaurant>) restaurantRepositoryI.findAll();
		if (menu.isEmpty())
			return resturant;
		for (Restaurant userInput : resturant) {
			for (Restaurant singleMenu : menu) {
				if (singleMenu.getName().equals(userInput.getName())
						&& singleMenu.getSize().equals(userInput.getSize())) {
					uniqueList.remove(userInput);
				}
			}
		}
		return uniqueList;
	}

}
