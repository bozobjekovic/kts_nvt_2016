package tim9.realEstate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;

/**
 * This interface represents RentRealEstate repository
 *
 */
public interface RentRealEstateRepository extends JpaRepository<RentRealEstate, Long> {

	/**
	 * This method finds all rented real estates with specified real estate and
	 * rented date to
	 * 
	 * @param realEstate
	 * @param dateRentedTo
	 * @return list objects of RentRealEstate
	 */
	List<RentRealEstate> findByRealEstateAndRentedToAfter(RealEstate realEstate, Date dateRentedTo);

}
