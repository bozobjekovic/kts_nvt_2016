package tim9.realEstate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tim9.realEstate.model.RealEstate;

/**
 * This interface represents RealEstate repository
 *
 */
public interface RealEstateRepository extends JpaRepository<RealEstate, Long> {

	/**
	 * This method finds real estate with specified address
	 * 
	 * @param address
	 * @return object of real estate
	 */
	RealEstate findByAddress(String address);

	/**
	 * This method finds all real estates with specified address and city
	 * 
	 * @param address
	 * @param city
	 * @return list objects of Real Estate
	 */
	List<RealEstate> findByAddressAndLocation_City(String address, String city);

}
