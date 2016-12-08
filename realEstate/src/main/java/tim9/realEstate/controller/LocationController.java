package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tim9.realEstate.dto.LocationDTO;
import tim9.realEstate.model.Location;
import tim9.realEstate.service.LocationService;

@Controller
@RequestMapping(value="realEstate/locations")
public class LocationController {
	
	@Autowired
	LocationService locationService;
	
	/**
     * This method gets all Locations from the database
     * and then creates a list of DTO objects.
     * @return      ResponseEntity List with all DTO Locations and HttpStatus OK
     */
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<LocationDTO>> getAllLocations() {
		List<Location> locations = locationService.findAll();
		
		List<LocationDTO> locationDTO = new ArrayList<>();
		for(Location l : locations){
			locationDTO.add(new LocationDTO(l));
		}
		return new ResponseEntity<>(locationDTO, HttpStatus.OK);
	}

	/**
     * This method updates a Location
     * and saves changes to the database.
     * @param		newLocation	a DTO Object
     * @return      ResponseEntity DTO Location and HttpStatus OK,
     * 				else returns null
     */
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<LocationDTO> updateLocation(@RequestBody LocationDTO newLocation){
		Location location = locationService.findOne(newLocation.getId());
		if (location == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		location.setAddress(newLocation.getAddress());
		location.setCity(newLocation.getCity());
		location.setZipCode(newLocation.getZipCode());
		location.setPartOfTheCity(newLocation.getPartOfTheCity());

		location = locationService.save(location);
		return new ResponseEntity<>(newLocation, HttpStatus.OK);
	}
}
