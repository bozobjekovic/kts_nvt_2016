package tim9.realEstate.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tim9.realEstate.dto.AdvertismentCreateDTO;
import tim9.realEstate.dto.AdvertismentDTO;
import tim9.realEstate.model.Advertisment;
import tim9.realEstate.model.Location;
import tim9.realEstate.model.RealEstate;
import tim9.realEstate.model.Verifier;
import tim9.realEstate.service.AdvertismentService;

@RestController
@RequestMapping(value="realEstate/advertisments")
public class AdvertismentController {

    @Autowired
    AdvertismentService advertismentService;
    
    /**
     * This method gets all Advertisements from the database
     * and then creates a list of DTO objects.
     * @return      ResponseEntity List with all DTO Advertisements and HttpStatus OK
     */
    @RequestMapping(value="/all", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> getAllAdvertisment() {
        List<Advertisment> advertisements = advertismentService.findAll();
        
        List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
        return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
    }
    
    /**
     * This method gets all Advertisements from the database
     * with specified page number and page size,
     * and then creates a list of DTO objects.
     * @param		page	Spring object
     * @return      ResponseEntity List with all DTO Advertisements and HttpStatus OK
     */
    @RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AdvertismentDTO>> getAdvertisementPage(Pageable page) {
		Page<Advertisment> advertisements = advertismentService.findAll(page);
		
		List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
		return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
	}
    
    /**
     * This method gets Advertisement with specified ID.
     * @param		id	an id of Advertisement
     * @return      ResponseEntity List with DTO Advertisement and HttpStatus if OK,
     * 				else null
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AdvertismentDTO> getAdvertisment(@PathVariable Long id){
    	Advertisment advertisment = advertismentService.findOne(id);
		if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new AdvertismentDTO(advertisment), HttpStatus.OK);
	}
    
    /**
     * This method creates new Advertisement and Real estate
     * and saves them to the database.
     * @param		advertismentDTO		a DTO Object
     * @return      ResponseEntity DTO Advertisement and HttpStatus CREATED if OK,
     * 				else null
     */
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<AdvertismentCreateDTO> saveAdvertisment(@RequestBody AdvertismentCreateDTO advertismentDTO){
    	Advertisment advertisment = new Advertisment();
    	advertisment.setPublicationDate(new Date());
		advertisment.setActiveUntil(advertismentDTO.getActiveUntil());
		advertisment.setPurpose(advertismentDTO.getPurpose());
		advertisment.setPhoneNumber(advertismentDTO.getPhoneNumber());
		if(advertismentService.findByPhoneNumber(advertisment.getPhoneNumber()) != null){
			return null;
		}
		
		RealEstate realEstate = new RealEstate();
		realEstate.setName(advertismentDTO.getName());
		realEstate.setLocation(new Location(advertismentDTO.getLocation()));
		realEstate.setPrice(advertismentDTO.getPrice());
		realEstate.setLandSize(advertismentDTO.getLandSize());
		realEstate.setTechEquipment(advertismentDTO.getTechEquipment());
		realEstate.setImage(advertismentDTO.getImage());
		realEstate.setNumOfBathRooms(advertismentDTO.getNumOfBathRooms());
		realEstate.setNumOfBedRooms(advertismentDTO.getNumOfBedRooms());
		realEstate.setNumOfFlors(advertismentDTO.getNumOfFlors());
		realEstate.setBuildYear(advertismentDTO.getBuildYear());
		realEstate.setCategory(advertismentDTO.getCategory());
		realEstate.setType(advertismentDTO.getType());
		
		advertisment.setRealEstate(realEstate);
		
		advertisment = advertismentService.save(advertisment);
		
		return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, realEstate), HttpStatus.CREATED);
    }
    
    /**
     * This method gets all Advertisements with a 
     * specified purpose.
     * @param		purpose	Advertisement's purpose
     * @return      ResponseEntity List of DTO Advertisements and HttpStatus OK
     */
    @RequestMapping(value="/purpose/{purpose}", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> getAdvertismentsByPurpose(@PathVariable String purpose, Pageable page){
    	Page<Advertisment> advertisements = advertismentService.findByPurpose(purpose, page);

    	List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
    	return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
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
    @RequestMapping(value="/rate", method = RequestMethod.PUT)
    public ResponseEntity<AdvertismentCreateDTO> rateAdvertisment(@RequestParam Long id, @RequestParam int rate){
    	Advertisment advertisment = advertismentService.findOne(id);
    	if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	advertisment.setNumberOfRates(advertisment.getNumberOfRates() + 1);
    	advertisment.setRate((advertisment.getRate()*advertisment.getNumberOfRates() + rate) / advertisment.getNumberOfRates());
    	advertismentService.save(advertisment);

		return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, advertisment.getRealEstate()), HttpStatus.OK);
    }
    
    /**
     * This method verifies an Advertisement
     * and sets it's verifier.
     * @param		idAdvertisment	Advertisement's id
     * @param		verifier	who verified it
     * @return      ResponseEntity status OK, else NOT_FOUND status
     */
    @RequestMapping(value="/verification", method = RequestMethod.PUT)
    public ResponseEntity<Void> verifyAdvertisment(@RequestParam Long idAdvertisment, @RequestParam Verifier verifier){
    	Advertisment advertisment = advertismentService.findOne(idAdvertisment);
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	System.out.println(authentication.getCredentials());
    	
    	if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	advertisment.setVerifier(verifier);
    	advertismentService.save(advertisment);
    	return new ResponseEntity<>(HttpStatus.OK);
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
    	Advertisment advertisment = advertismentService.findOne(idAdvertisment);
    	if(advertisment == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	advertisment.setActiveUntil(date);
    	advertismentService.save(advertisment);
    	return new ResponseEntity<>(new AdvertismentCreateDTO(advertisment, advertisment.getRealEstate()), HttpStatus.OK);
    }
    
    /**
     * This method gets top 3 rated Advertisements.
     * @return      ResponseEntity DTO Advertisement and HttpStatus OK
     */
    @RequestMapping(value="/popular", method = RequestMethod.GET)
    public ResponseEntity<List<AdvertismentDTO>> getPopularAdvertisements(){
    	PageRequest request = new PageRequest(0, 3);
    	List<Advertisment> advertisements = advertismentService.orderByRate(request);
    	
    	List<AdvertismentDTO> advertsDTO = new ArrayList<>();
		for (Advertisment a : advertisements) {
			advertsDTO.add(new AdvertismentDTO(a));
		}
    	return new ResponseEntity<>(advertsDTO, HttpStatus.OK);
    }
    
    /**
     * This method deletes advertisement with given id
     * @param id
     * @return HttpStatus OK if exists else HttpStatus NOT_FOUND
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deteleAdvertisement(@PathVariable Long id) {
    	Advertisment advertisement = advertismentService.findOne(id);
    	if (advertisement != null) {
			advertismentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    }

}
