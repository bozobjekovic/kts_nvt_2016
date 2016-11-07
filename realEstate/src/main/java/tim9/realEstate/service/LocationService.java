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
	
	public Location findOne(Long id) {
		return locationRepositroy.findOne(id);
	}

	public List<Location> findAll() {
		return locationRepositroy.findAll();
	}

	public Location save(Location location) {
		return locationRepositroy.save(location);
	}

	public void remove(Long id) {
		locationRepositroy.delete(id);
	}

}
