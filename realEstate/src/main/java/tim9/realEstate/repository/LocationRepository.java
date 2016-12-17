package tim9.realEstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long>{
	
	public Location findByCityAndZipCodeAndPartOfTheCity(String city, int zipCode, String partOfTheCity);

}
