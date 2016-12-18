package tim9.realEstate.service;

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
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public RentRealEstate findOne(Long id) {
		return rentRealEstateRepository.findOne(id);
	}
	
	/**
	 * This method finds element with specified real estate
	 *  and gets it from the database.
	 * @param realEstate
	 * @return 		Element if found, null if doesn't exist
	 */
	public RentRealEstate findByRealEstate(RealEstate realEstate) {
		return rentRealEstateRepository.findByRealEstate(realEstate);
	}
	
	/**
	* This method saves element to the database.
	* @param		rentRealEstate element to be saved
	* @return		Saved element
	*/
	public void save(RentRealEstate rentRealEstate) {
		rentRealEstateRepository.save(rentRealEstate);
	}
	
}
