package tim9.realEstate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Location;
import tim9.realEstate.repository.LocationRepository;

/**
 * This class represents LocationService
 *
 */
@Service
public class LocationService {

	@Autowired
	LocationRepository locationRepositroy;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public Location findOne(Long id) {
		return locationRepositroy.findOne(id);
	}

	/**
	 * This method finds element with specified city, zipCode and partOfTheCity
	 * 
	 * @param city
	 *            city
	 * @param zipCode
	 *            zipCode
	 * @param partOfTheCity
	 *            partOfTheCity
	 * @return Element if found, null if doesn't exist
	 */
	public Location findByCityAndZipCodeAndPartOfTheCity(String city, int zipCode, String partOfTheCity) {
		return locationRepositroy.findByCityAndZipCodeAndPartOfTheCity(city, zipCode, partOfTheCity);
	}

	/**
	 * This method finds all part of the city
	 * 
	 * @return List of elements
	 */
	public List<String> getAllPartOfTheCities() {
		return locationRepositroy.findAllPartOfTheCities();
	}

	/**
	 * This method finds all cities
	 * 
	 * @return List of cities
	 */
	public List<String> getAllCities() {
		return locationRepositroy.findDistinctCities();
	}

	/**
	 * This method finds all part of the cities that belongs to specified city
	 * 
	 * @param city
	 * @return List of String
	 */
	public List<String> getAllPartOfTheCitiesByCity(String city) {
		List<String> partOfTheCities = new ArrayList<>();
		List<Location> locations = locationRepositroy.findByCity(city);

		for (int i = 0; i < locations.size(); i++) {
			partOfTheCities.add(locations.get(i).getPartOfTheCity());
		}

		return partOfTheCities;
	}

	/**
	 * This method finds all elements from specified Table.
	 * 
	 * @return List of elements
	 */
	public List<Location> findAll() {
		return locationRepositroy.findAll();
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param location
	 *            element to be saved
	 * @return Saved element
	 */
	public Location save(Location location) {
		return locationRepositroy.save(location);
	}

	/**
	 * This method removes element from the database.
	 * 
	 * @param id
	 *            id of element to be removed
	 */
	public void remove(Long id) {
		locationRepositroy.delete(id);
	}

}
