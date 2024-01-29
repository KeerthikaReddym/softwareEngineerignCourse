package com.indianrestaurant.restaurant.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indianrestaurant.restaurant.model.Restaurant;
import com.indianrestaurant.restaurant.service.RestaurantServiceI;

/**
 * The `RestaurantController` class is responsible for managing and handling
 * HTTP requests related to restaurant menu items. It provides endpoints for
 * retrieving, adding, updating, and deleting menu items from the restaurant's
 * database.
 *
 * This controller interacts with the `RestaurantServiceI` to perform the
 * underlying business logic for menu item operations. It utilizes Spring's
 * RESTful web framework to define and map HTTP endpoints to specific methods.
 * 
 * @author Keerthika
 *
 */
@RestController
@RequestMapping("/Restaurant")
public class RestaurantController {

	@Autowired
	RestaurantServiceI restaurantServiceI;

	/**
	 * This method is used to return a list of all the menu present in the database
	 * 
	 * @return list of restaurant table
	 */
	@GetMapping
	public ResponseEntity<List<Restaurant>> findAll() {
		return !restaurantServiceI.getMenu().isEmpty()
				? new ResponseEntity<>(restaurantServiceI.getMenu(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * This method is used to return one record of the menu based on the user
	 * request id
	 * 
	 * @param id The unique identifier of the menu item.
	 * @return restaurant data
	 */
	@GetMapping({ "{id}" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<Optional<Restaurant>> findById(@PathVariable Integer id) {
		return restaurantServiceI.getMenuById(id).isPresent()
				? new ResponseEntity<>(restaurantServiceI.getMenuById(id), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to return a list of menu of mentioned type from the user
	 * 
	 * @param Type
	 * @return list<restaurant>
	 */
	@GetMapping({ "/type" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> findByType(@RequestHeader String type) {
		return !restaurantServiceI.getMenyByType(type).isEmpty()
				? new ResponseEntity<>(restaurantServiceI.getMenyByType(type), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to return a list of menu of mentioned name from the user
	 * 
	 * @param Name
	 * @return list<restaurant>
	 */
	@GetMapping("/getByName/{name}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> findByName(@PathVariable String name) {
		return !restaurantServiceI.findByName(name).isEmpty()
				? new ResponseEntity<>(restaurantServiceI.findByName(name), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to return a list of menu of food less than or equal to
	 * the price
	 * 
	 * @param Price
	 * @return list<restaurant>
	 */
	@GetMapping("filter/{price}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> filterByPrice(@PathVariable int price) {
		return !restaurantServiceI.filterByPrice(price).isEmpty()
				? new ResponseEntity<>(restaurantServiceI.filterByPrice(price), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to return a list of menu which is chef favorite
	 * @return list<restaurant>
	 */
	@GetMapping("filter/chefFavorite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<List<Restaurant>> filterByChefFavorite() {
		return !restaurantServiceI.filterByChefFavorite().isEmpty()
				? new ResponseEntity<>(restaurantServiceI.filterByChefFavorite(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * This method is used to return count of records present in database
	 * 
	 * @return count of total number of records in the database
	 */
	@GetMapping("/Count")
	public ResponseEntity<Long> getCount() {
		return restaurantServiceI.getCount() > 0 ? new ResponseEntity<>(restaurantServiceI.getCount(), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * This method is used to save one menu item into the database
	 * 
	 * @param restaurant
	 * @return restaurant data
	 */
	@PostMapping()
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<String> save(@RequestBody Restaurant restaurant) {

		return restaurantServiceI.save(restaurant) != null
				? new ResponseEntity<>("Successfully added the record", HttpStatus.OK)
				: new ResponseEntity<String>("The request is not ", HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to save many records into the database
	 * 
	 * @param restaurant
	 * @return list of all saved menu
	 */
	@PostMapping("/AddMany")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<String> saveMany(@RequestBody List<Restaurant> restaurant) {

		return restaurantServiceI.SaveMany(restaurant) != null
				? new ResponseEntity<String>("Successfully added all the records", HttpStatus.OK)
				: new ResponseEntity<String>("Please check your request", HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to update data based on id
	 * 
	 * @param id
	 * @param resturant
	 * @return updated record
	 */
	@PutMapping({ "/{id}" })
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody Restaurant resturant) {
		return restaurantServiceI.update(id, resturant) != null
				? new ResponseEntity<String>("successfully updated the record", HttpStatus.OK)
				: new ResponseEntity<String>("The record you are trying to update is not found",
						HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method is used to delete a based on id
	 * 
	 * @param id The unique identifier of the menu item.
	 * @return true when it deletes a record or else false
	 */
	@DeleteMapping({ "/{id}" })
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		if (!restaurantServiceI.getMenuById(id).isPresent()) {
			return new ResponseEntity<String>("No data in Data base with this Id to delete", HttpStatus.NO_CONTENT);
		}
		return restaurantServiceI.deleteById(id)
				? new ResponseEntity<String>("The record you are trying to delete is not found", HttpStatus.BAD_REQUEST)
				: new ResponseEntity<String>("Successfully deleted record", HttpStatus.OK);
	}

	/**
	 * This method is used to delete all the records in the database
	 * 
	 * @param restaurant
	 * @return true when it deletes a all records in the database or it returns
	 *         false
	 */
	@DeleteMapping()
	public ResponseEntity<String> delete() {
		if (restaurantServiceI.getMenu().isEmpty()) {
			return new ResponseEntity<String>("No data in Data base to delete", HttpStatus.NO_CONTENT);
		}
		restaurantServiceI.deleteAll();
		return restaurantServiceI.getCount() > 0
				? new ResponseEntity<String>("Unable to delete records", HttpStatus.NO_CONTENT)
				: new ResponseEntity<String>("Successfully deleted all record", HttpStatus.OK);

	}

}
