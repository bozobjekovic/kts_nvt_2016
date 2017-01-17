package tim9.realEstate.controller;


import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.AdvertismentCreateDTO;
import tim9.realEstate.dto.CompanyDTO;
import tim9.realEstate.dto.UserDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Location;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.User;
import tim9.realEstate.security.UserUtils;
import tim9.realEstate.service.AdvertismentService;
import tim9.realEstate.service.LocationService;

/**
 * This class represents controller for Advertisement
 * and manages with all Advertisement functionalities.
 */
@RestController
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentController {

    @Autowired
    AdvertismentService advertismentService;
    
    @Autowired
    LocationService locationService;
    
    @Autowired
    UserUtils userUtils;
    
    /**
     * This method gets Advertisement with specified ID.
     * @param		id	an id of Advertisement
     * @return      ResponseEntity List with DTO Advertisement and HttpStatus if OK,
     * 				else null
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AdvertismentCreateDTO> getAdvertisment(@PathVariable Long id){
    	if(id == null){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Advertisment advertisment = advertismentService.findOne(id);
		if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, advertisment.getRealEstate()), HttpStatus.OK);
	}
    
    /**
     * This method gets Advertisement publisher.
     * @param		id	an id of Advertisement
     * @return      ResponseEntity List with DTO User and HttpStatus if OK,
     * 				else null
     */
    @RequestMapping(value="/publisher/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> getPublisher(@PathVariable Long id){
    	if(id == null){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Advertisment advertisment = advertismentService.findOne(id);
		if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new UserDTO(advertisment.getPublisher()), HttpStatus.OK);
	}
    
    /**
     * This method gets Advertisement publishers company.
     * @param		id	an id of Advertisement
     * @return      ResponseEntity Company DTO and HttpStatus if OK,
     * 				else null
     */
    @RequestMapping(value="/company/{id}", method=RequestMethod.GET)
	public ResponseEntity<CompanyDTO> getPublishersCompany(@PathVariable Long id){
    	if(id == null){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Advertisment advertisment = advertismentService.findOne(id);
		if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(advertisment.getPublisher().getCompany() == null){
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(new CompanyDTO(advertisment.getPublisher().getCompany()), HttpStatus.OK);
	}
    
    /**
     * This method creates new Advertisement and Real estate
     * and saves them to the database.
     * @param		advertismentDTO		a DTO Object
     * @return      ResponseEntity DTO Advertisement and HttpStatus CREATED if OK,
     * 				else null
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AdvertismentCreateDTO> saveAdvertisment(@RequestBody AdvertismentCreateDTO advertismentDTO, ServletRequest request){
    	User user = (User)userUtils.getLoggedUser(request);
    	
    	if (user == null) {
    		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
    	
    	if(!checkInput(advertismentDTO)){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Advertisment advertisment = new Advertisment();
    	advertisment.setName(advertismentDTO.getName());
    	advertisment.setPrice(advertismentDTO.getPrice());
    	advertisment.setPublicationDate(new Date());
    	
    	if (advertismentDTO.getImages().size() != 0) {
    		advertisment.setBackgroundImage(advertismentDTO.getImages().get(0));
    		advertisment.setImages(advertismentDTO.getImages());
		}
    	
		advertisment.setActiveUntil(advertismentDTO.getActiveUntil());
		advertisment.setPurpose(advertismentDTO.getPurpose());
		advertisment.setPhoneNumber(advertismentDTO.getPhoneNumber());
		
		if(advertismentService.findByPhoneNumber(advertisment.getPhoneNumber()) != null){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		advertisment.setPublisher(user);
		RealEstate realEstate = new RealEstate();
		
		Location location = locationService.findByCityAndZipCodeAndPartOfTheCity(
				advertismentDTO.getLocation().getCity(), advertismentDTO.getLocation().getZipCode(),
				advertismentDTO.getLocation().getPartOfTheCity());
		if (location == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		realEstate.setLocation(location);
		realEstate.setAddress(advertismentDTO.getAddress());
		realEstate.setLandSize(advertismentDTO.getLandSize());
		realEstate.setTechEquipment(advertismentDTO.getTechEquipment());
		realEstate.setNumOfBathRooms(advertismentDTO.getNumOfBathRooms());
		realEstate.setNumOfBedRooms(advertismentDTO.getNumOfBedRooms());
		realEstate.setNumOfFlors(advertismentDTO.getNumOfFlors());
		realEstate.setBuildYear(advertismentDTO.getBuildYear());
		realEstate.setCategory(advertismentDTO.getCategory());
		realEstate.setType(advertismentDTO.getType());

		advertisment.setRealEstate(realEstate);

		advertisment = advertismentService.save(advertisment);
		
		return new ResponseEntity<>(advertismentDTO, HttpStatus.CREATED);
    }
    
    /**
     * This method updates Advertisement and Real estate
     * and saves them to the database.
     * @param		advertismentDTO		a DTO Object
     * @return      ResponseEntity DTO Advertisement and HttpStatus CREATED if OK,
     * 				else null
     */
    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<AdvertismentCreateDTO> updateAdvertisment(@RequestBody AdvertismentCreateDTO advertismentDTO, ServletRequest request){
    	User user = (User)userUtils.getLoggedUser(request);
    	
    	if (user == null) {
    		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
    	
    	if(!checkInput(advertismentDTO)){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Advertisment advertisment = advertismentService.findOne(advertismentDTO.getAdvertismentId());
    	advertisment.setModificationDate(new Date());
    	advertisment.setName(advertismentDTO.getName());
    	advertisment.setPrice(advertismentDTO.getPrice());
    	
    	if (advertismentDTO.getImages().size() != 0) {
    		advertisment.setBackgroundImage(advertismentDTO.getImages().get(0));
    		advertisment.setImages(advertismentDTO.getImages());
		}
    	
		advertisment.setActiveUntil(advertismentDTO.getActiveUntil());
		advertisment.setPurpose(advertismentDTO.getPurpose());
		advertisment.setPhoneNumber(advertismentDTO.getPhoneNumber());
		
		if(advertismentService.findByPhoneNumber(advertisment.getPhoneNumber()) != null){
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		RealEstate realEstate = advertisment.getRealEstate();
		
		Location location = locationService.findByCityAndZipCodeAndPartOfTheCity(
				advertismentDTO.getLocation().getCity(), advertismentDTO.getLocation().getZipCode(),
				advertismentDTO.getLocation().getPartOfTheCity());
		if (location == null) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		realEstate.setLocation(location);
		realEstate.setAddress(advertismentDTO.getAddress());
		realEstate.setLandSize(advertismentDTO.getLandSize());
		realEstate.setTechEquipment(advertismentDTO.getTechEquipment());
		realEstate.setNumOfBathRooms(advertismentDTO.getNumOfBathRooms());
		realEstate.setNumOfBedRooms(advertismentDTO.getNumOfBedRooms());
		realEstate.setNumOfFlors(advertismentDTO.getNumOfFlors());
		realEstate.setBuildYear(advertismentDTO.getBuildYear());
		realEstate.setCategory(advertismentDTO.getCategory());
		realEstate.setType(advertismentDTO.getType());

		advertisment.setRealEstate(realEstate);

		advertisment = advertismentService.save(advertisment);
		
		return new ResponseEntity<>(advertismentDTO, HttpStatus.OK);
    }
    
    /**
     * This method checks if all input fields are valid.
     * @param		advertismentDTO	Advertisement DTO
     * @return      true if OK, else false
     */
    private boolean checkInput(AdvertismentCreateDTO advertismentDTO) {
    	if(advertismentDTO.getName() == null || advertismentDTO.getName().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getAddress() == null || advertismentDTO.getAddress().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getLocation().getCity() == null || advertismentDTO.getLocation().getCity().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getHeatingType() == null || advertismentDTO.getHeatingType().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getCategory() == null || advertismentDTO.getCategory().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getPurpose() == null || advertismentDTO.getPurpose().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getPhoneNumber() == null || advertismentDTO.getPhoneNumber().equals("")){
    		return false;
    	}
    	else if(advertismentDTO.getType() == null || advertismentDTO.getType().equals("")){
    		return false;
    	}
    	return true;		
	}
    
    /**
     * This method sets a new rate for an Advertisement.
     * It gets a given rate as a parameter and then calculates
     * the new average rate and saves it in the database.
     * @param		id	Advertisement's id
     * @param		rate	given rate
     * @return      ResponseEntity DTO Advertisement and HttpStatus OK if OK,
     * 				else NOT_FOUND status
     */
    @RequestMapping(value="advertisment/{id}/rate/{rate}", method = RequestMethod.PUT)
    public ResponseEntity<AdvertismentCreateDTO> rateAdvertisment(@PathVariable Long id, @PathVariable int rate){
    	if(id == null || rate < 1 || rate > 5){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	Advertisment advertisment = advertismentService.findOne(id);
    	if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	advertisment.setNumberOfRates(advertisment.getNumberOfRates() + 1);
    	advertisment.setRate(round(
    			((advertisment.getRate()*(advertisment.getNumberOfRates()-1)) + rate) / advertisment.getNumberOfRates(), 2));
    	advertismentService.save(advertisment);
		return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, advertisment.getRealEstate()), HttpStatus.OK);
    }
    
    /**
     * This method prolongs an Advertisement
     * until given Date.
     * @param		idAdvertisment	Advertisement's id
     * @param		date	date until it's valid
     * @return      ResponseEntity DTO Advertisement and HttpStatus OK if OK,
     * 				else NOT_FOUND status
     */
    @RequestMapping(value="/prolong", method = RequestMethod.PUT)
    public ResponseEntity<AdvertismentCreateDTO> prolongAdvertisment(@RequestParam Long idAdvertisment, @RequestParam Date date){
    	if(idAdvertisment == null || date == null){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Advertisment advertisment = advertismentService.findOne(idAdvertisment);
    	
    	if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	if (date.before(new Date())) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    	advertisment.setActiveUntil(date);
    	advertismentService.save(advertisment);
    	
    	return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, advertisment.getRealEstate()), HttpStatus.OK);
    }
    
    /**
     * This method deletes advertisement with given id
     * @param id
     * @return HttpStatus OK if exists else HttpStatus NOT_FOUND
     */
    @RequestMapping(value="/delete/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> deteleAdvertisement(@PathVariable Long id) {
    	if(id == null){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    	
    	Advertisment advertisement = advertismentService.findOne(id);
    	
    	if (advertisement == null) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
    	if (advertisement.isDeleted()) {
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    	
    	advertisement.setDeleted(true);
		advertismentService.save(advertisement);
		return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * This method rounds double value on two 
     * decimals
     * @param value
     * @param places
     * @return double value with two decimals
     */
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
