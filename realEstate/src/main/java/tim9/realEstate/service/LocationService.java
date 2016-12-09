package tim9.realEstate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tim9.realEstate.model.Location;
import tim9.realEstate.repository.LocationRepository;

@Service
public class LocationService {
	
	@Autowired
	LocationRepository locationRepositroy;
	
	/**
	* This method finds element with specified id and gets it
	* from the database.
	* @param		id  Element id
	* @return		Element if found, null if doesn't exist
	*/
	public Location findOne(Long id) {
		return locationRepositroy.findOne(id);
	}
	
	/**
	* This method finds all elements from specified Table.
	* @return		List of elements
	*/
	public List<Location> findAll() {
		return locationRepositroy.findAll();
	}
	
	/**
	* This method saves element to the database.
	* @param		location element to be saved
	* @return		Saved element
	*/
	public Location save(Location location) {
		return locationRepositroy.save(location);
	}
	
	/**
	* This method removes element from the database.
	* @param		id id of element to be removed
	*/
	public void remove(Long id) {
		locationRepositroy.delete(id);
	}

}
