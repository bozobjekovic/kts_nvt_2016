package tim9.realEstate.controller;

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
	
	@RequestMapping(value="/all", method = RequestMethod.GET)
	public ResponseEntity<List<Location>> getAllLocations() {
		List<Location> students = locationService.findAll();
		//convert students to DTOs
		/*List<StudentDTO> studentsDTO = new ArrayList<>();
		for (Student s : students) {
			studentsDTO.add(new StudentDTO(s));
		}*/
		return new ResponseEntity<>(students, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<LocationDTO> saveLocation(@RequestBody LocationDTO locationDTO){
		Location location = new Location();
		location.setId(locationDTO.getId());
		location.setAddress(locationDTO.getAddress());
		location.setCity(locationDTO.getCity());
		location.setZipCode(locationDTO.getZipCode());
		location.setPartOfTheCity(locationDTO.getPartOfTheCity());
		
		location = locationService.save(location);
		return new ResponseEntity<>(new LocationDTO(location), HttpStatus.CREATED);	
	}
}
