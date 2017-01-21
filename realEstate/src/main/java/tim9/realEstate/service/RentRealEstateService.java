package tim9.realEstate.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.RentRealEstate;
import tim9.realEstate.repository.RentRealEstateRepository;

@Service
public class RentRealEstateService {

	@Autowired
	RentRealEstateRepository rentRealEstateRepository;

	/**
	 * This method finds element with specified id and gets it from the
	 * database.
	 * 
	 * @param id
	 *            Element id
	 * @return Element if found, null if doesn't exist
	 */
	public RentRealEstate findOne(Long id) {
		return rentRealEstateRepository.findOne(id);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<RentRealEstate> findAll() {
		return rentRealEstateRepository.findAll();
	}

	/**
	 * This method finds element with specified real estate and gets it from the
	 * database.
	 * 
	 * @param realEstate
	 * @return Element if found, null if doesn't exist
	 */
	public List<RentRealEstate> findByRealEstate(RealEstate realEstate) {
		return rentRealEstateRepository.findByRealEstateAndRentedToAfter(realEstate, new Date());
	}

	/**
	 * This method saves element to the database.
	 * 
	 * @param rentRealEstate
	 *            element to be saved
	 * @return Saved element
	 */
	public void save(RentRealEstate rentRealEstate) {
		rentRealEstateRepository.save(rentRealEstate);
	}

}
