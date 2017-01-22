package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tim9.realEstate.model.Location;

/**
 * This interface represents Location repository
 *
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

	/**
	 * This method finds location with specified city, zip code and part of the
	 * cities
	 * 
	 * @param city
	 * @param zipCode
	 * @param partOfTheCity
	 * @return object of Location
	 */
	public Location findByCityAndZipCodeAndPartOfTheCity(String city, int zipCode, String partOfTheCity);

	/**
	 * This method finds all locations with specified city
	 * 
	 * @param city
	 * @return list objects of Location
	 */
	public List<Location> findByCity(String city);

	/**
	 * This method finds all cities
	 * 
	 * @return list of names
	 */
	@Query("select distinct city from Location")
	public List<String> findDistinctCities();

	/**
	 * This method finds all part of the cities
	 * 
	 * @return list of names
	 */
	@Query("select partOfTheCity from Location where partOfTheCity != ''")
	public List<String> findAllPartOfTheCities();

}
