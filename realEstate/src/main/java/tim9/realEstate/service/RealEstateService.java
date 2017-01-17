package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.RealEstate;
import tim9.realEstate.repository.RealEstateRepository;

@Service
public class RealEstateService {
	
	@Autowired
	RealEstateRepository realEstateRepository;
	
	/**
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public RealEstate findOne(Long id) {
		return realEstateRepository.findOne(id);
	}
	
	/**
	* This method finds element with specified address and gets it
	* from the database.
	* @param		address  Element address
	* @return		Element if found, null if doesn't exist
	*/
	public RealEstate findByAddress(String address) {
		return realEstateRepository.findByAddress(address);
	}
	
	public List<RealEstate> findByAddressAndCity(String address, String city) {
		return realEstateRepository.findByAddressAndLocation_City(address, city);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<RealEstate> findAll() {
		return realEstateRepository.findAll();
	}
	
	/**
	* This method saves element to the database.
	* @param		realEstate element to be saved
	* @return		Saved element
	*/
	public RealEstate save(RealEstate realEstate) {
		return realEstateRepository.save(realEstate);
	}
	
	/**
	* This method removes element from the database.
	* @param		id id of element to be removed
	*/
	public void remove(Long id) {
		realEstateRepository.delete(id);
	}

}
