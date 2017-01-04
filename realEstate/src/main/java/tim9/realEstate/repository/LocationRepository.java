package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tim9.realEstate.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
	
	public Location findByCityAndZipCodeAndPartOfTheCity(String city, int zipCode, String partOfTheCity);
	
	public List<Location> findByCity(String city);
	
	@Query("select distinct city from Location")
	public List<String> findDistinctCities();
	
	@Query("select partOfTheCity from Location where partOfTheCity != ''")
	public List<String> findAllPartOfTheCities();

}
