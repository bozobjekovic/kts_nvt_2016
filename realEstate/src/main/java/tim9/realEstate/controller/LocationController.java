package tim9.realEstate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tim9.realEstate.dto.LocationDTO;
import tim9.realEstate.model.Location;
import tim9.realEstate.service.LocationService;

/**
 * This class represents controller for Location
 * and manages with all Location functionalities.
 */
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
	
	@RequestMapping(value="/city", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllCities() {
		return new ResponseEntity<>(locationService.getAllCities(), HttpStatus.OK);
	}

	@RequestMapping(value="/partOfTheCities", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllPartOfTheCities() {
		return new ResponseEntity<>(locationService.getAllPartOfTheCities(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/city/partOfTheCity", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getAllpartOfTheCity(@RequestParam String city) {
		return new ResponseEntity<>(locationService.getAllPartOfTheCitiesByCity(city), HttpStatus.OK);
	}
	
}
