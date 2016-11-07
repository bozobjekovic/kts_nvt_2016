package tim9.realEstate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.model.Location;
import tim9.realEstate.service.LocationService;

@Controller
@RequestMapping(value="realEstate/locations")
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> getAllLocations() {
		List<Location> students = locationService.findAll();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Location> saveLocation(Location location){
		location = locationService.save(location);
		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Location> updateLocation(Location newLocation){
		Location location = locationService.findOne(newLocation.getId());
		if (location == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		location.setAddress(newLocation.getAddress());
		location.setCity(newLocation.getCity());
		location.setZipCode(newLocation.getZipCode());
		location.setPartOfTheCity(newLocation.getPartOfTheCity());

		location = locationService.save(location);
		return new ResponseEntity<>(location, HttpStatus.OK);
	}
}
